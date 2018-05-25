using System;
using System.IO.Ports;
using System.Text;
using System.Text.RegularExpressions;
using System.Threading;

namespace MoneyKeeper.Sms
{
    /// <summary>
    /// SMS
    /// </summary>
    public class SMS
    {
        #region -- Port --

        /// <summary>
        /// Open port
        /// </summary>
        /// <param name="portName">Port name</param>
        /// <param name="baudRate">Baud rate</param>
        /// <param name="dataBits">Data bits</param>
        /// <param name="rTimeout">Read timeout</param>
        /// <param name="wTimeout">Write timeout</param>
        /// <returns>Return the SerialPort</returns>
        public SerialPort OpenPort(string portName, int baudRate = 9600,
            int dataBits = 8, int rTimeout = 300, int wTimeout = 300)
        {
            _receiveNow = new AutoResetEvent(false);
            var res = new SerialPort();

            try
            {
                res.PortName = portName;                 // COM1
                res.BaudRate = baudRate;                 // 9600
                res.DataBits = dataBits;                 // 8
                res.StopBits = StopBits.One;             // 1
                res.Parity = Parity.None;                // None
                res.ReadTimeout = rTimeout;              // 300
                res.WriteTimeout = wTimeout;             // 300
                res.Encoding = Encoding.GetEncoding("iso-8859-1");
                res.DataReceived += new SerialDataReceivedEventHandler(Port_DataReceived);
                res.Open();
                res.DtrEnable = true;
                res.RtsEnable = true;
            }
            catch (Exception ex)
            {
                throw ex;
            }

            return res;
        }

        /// <summary>
        /// Close port
        /// </summary>
        /// <param name="port">The SerialPort</param>
        public void ClosePort(SerialPort port)
        {
            try
            {
                port.Close();
                port.DataReceived -= new SerialDataReceivedEventHandler(Port_DataReceived);
                port = null;
            }
            catch (Exception ex)
            {
                throw ex;
            }
        }

        /// <summary>
        /// Execute AT command
        /// </summary>
        /// <param name="port">The SerialPort</param>
        /// <param name="command">AT command</param>
        /// <param name="timeout">Response timeout</param>
        /// <param name="error">Error message</param>
        /// <returns>Return the result</returns>
        public string ExecCommand(SerialPort port, string command, int timeout, string error)
        {
            try
            {
                port.DiscardOutBuffer();
                port.DiscardInBuffer();
                _receiveNow.Reset();
                port.Write(command + "\r");

                var res = ReadResponse(port, timeout);
                if ((res.Length == 0) || ((!res.EndsWith("\r\n> ")) && (!res.EndsWith("\r\nOK\r\n"))))
                {
                    throw new ApplicationException("No success message was received.");
                }

                return res;
            }
            catch (Exception ex)
            {
                throw ex;
            }
        }

        /// <summary>
        /// Read response
        /// </summary>
        /// <param name="port">The SerialPort</param>
        /// <param name="timeout">Timeout</param>
        /// <returns>Return the result</returns>
        public string ReadResponse(SerialPort port, int timeout)
        {
            var res = string.Empty;

            try
            {
                do
                {
                    if (_receiveNow.WaitOne(timeout, false))
                    {
                        string t = port.ReadExisting();
                        res += t;
                    }
                    else
                    {
                        if (res.Length > 0)
                        {
                            throw new ApplicationException("Response received is incomplete.");
                        }
                        else
                        {
                            throw new ApplicationException("No data received from phone.");
                        }
                    }
                }
                while (!res.EndsWith("\r\nOK\r\n") && !res.EndsWith("\r\n> ") && !res.EndsWith("\r\nERROR\r\n"));
            }
            catch (Exception ex)
            {
                throw ex;
            }

            return res;
        }

        #endregion

        #region -- SMS --

        /// <summary>
        /// Count SMS messages
        /// </summary>
        /// <param name="port">The SerialPort</param>
        /// <returns>Return the result</returns>
        public int CountSMS(SerialPort port)
        {
            var res = 0;

            try
            {
                #region -- Execute command --
                var recievedData = ExecCommand(port, "AT", 300, "No phone connected at ");
                recievedData = ExecCommand(port, "AT+CMGF=1", 300, "Failed to set message format.");
                var command = "AT+CPMS?";
                recievedData = ExecCommand(port, command, 1000, "Failed to count SMS message");
                #endregion

                if ((recievedData.Length >= 45) && (recievedData.StartsWith("AT+CPMS?")))
                {
                    #region -- Parsing SMS --
                    var arr = recievedData.Split(',');
                    var t1 = arr[0];
                    var t2 = arr[1];
                    #endregion

                    // Count total number of SMS in SIM
                    res = Convert.ToInt32(t2);
                }
                else if (recievedData.Contains("ERROR"))
                {
                    var recievedError = recievedData;
                    recievedError = recievedError.Trim();
                    recievedData = "Following error occured while counting the message" + recievedError;
                }

                return res;
            }
            catch (Exception ex)
            {
                throw ex;
            }
        }

        /// <summary>
        /// Read SMS messages
        /// </summary>
        /// <param name="port">The SerialPort</param>
        /// <param name="command">AT command</param>
        /// <returns>Return the result</returns>
        public ShortMessageCollection ReadSMS(SerialPort port, string command)
        {
            // Set up the phone and read the messages
            ShortMessageCollection messages = null;

            try
            {
                #region -- Execute command --
                // Check connection
                ExecCommand(port, "AT", 300, "No phone connected");
                // Use message format "Text mode"
                ExecCommand(port, "AT+CMGF=1", 300, "Failed to set message format.");
                // Use character set "PCCP437"
                ExecCommand(port, "AT+CSCS=\"PCCP437\"", 300, "Failed to set character set.");
                // Select SIM storage
                ExecCommand(port, "AT+CPMS=\"SM\"", 300, "Failed to select message storage.");
                // Read the messages
                var input = ExecCommand(port, command, 5000, "Failed to read the messages.");
                #endregion

                messages = ParseMessages(input);
            }
            catch (Exception ex)
            {
                throw ex;
            }

            if (messages != null)
            {
                return messages;
            }
            else
            {
                return null;
            }
        }

        /// <summary>
        /// Send SMS messages
        /// </summary>
        /// <param name="port">The SerialPort</param>
        /// <param name="phone">Phone no</param>
        /// <param name="message">Message</param>
        /// <returns>Return the result</returns>
        public bool SendSMS(SerialPort port, string phone, string message)
        {
            bool res = false;

            try
            {

                var recievedData = ExecCommand(port, "AT", 300, "No phone connected");
                recievedData = ExecCommand(port, "AT+CMGF=1", 300, "Failed to set message format.");
                var command = "AT+CMGS=\"" + phone + "\"";
                recievedData = ExecCommand(port, command, 300, "Failed to accept phoneNo");
                command = message + char.ConvertFromUtf32(26) + "\r";

                recievedData = ExecCommand(port, command, 3000, "Failed to send message"); //3 seconds
                if (recievedData.EndsWith("\r\nOK\r\n"))
                {
                    res = true;
                }
                else if (recievedData.Contains("ERROR"))
                {
                    res = false;
                }

                return res;
            }
            catch (Exception ex)
            {
                throw ex;
            }
        }

        /// <summary>
        /// Delete SMS messages
        /// </summary>
        /// <param name="port">The SerialPort</param>
        /// <param name="command"></param>
        /// <returns>Return the result</returns>
        public bool DeleteSMS(SerialPort port, string command)
        {
            bool isDeleted = false;
            try
            {

                #region -- Execute command --
                var recievedData = ExecCommand(port, "AT", 300, "No phone connected");
                recievedData = ExecCommand(port, "AT+CMGF=1", 300, "Failed to set message format.");
                recievedData = ExecCommand(port, command, 300, "Failed to delete message");
                #endregion

                if (recievedData.EndsWith("\r\nOK\r\n"))
                {
                    isDeleted = true;
                }
                if (recievedData.Contains("ERROR"))
                {
                    isDeleted = false;
                }
                return isDeleted;
            }
            catch (Exception ex)
            {
                throw ex;
            }
        }

        #endregion

        #region -- Event --

        /// <summary>
        /// Auto reset event
        /// </summary>
        private AutoResetEvent _receiveNow;

        /// <summary>
        /// Auto reset event
        /// </summary>
        private static AutoResetEvent _readNow = new AutoResetEvent(false);

        /// <summary>
        /// Receive data from port
        /// </summary>
        /// <param name="sender">Sender</param>
        /// <param name="e">Event</param>
        private void Port_DataReceived(object sender, SerialDataReceivedEventArgs e)
        {
            try
            {
                if (e.EventType == SerialData.Chars)
                {
                    _receiveNow.Set();
                }
            }
            catch (Exception ex)
            {
                throw ex;
            }
        }

        /// <summary>
        /// Data received
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">Event</param>
        private static void DataReceived(object sender, SerialDataReceivedEventArgs e)
        {
            try
            {
                if (e.EventType == SerialData.Chars)
                {
                    _readNow.Set();
                }
            }
            catch (Exception ex)
            {
                throw ex;
            }
        }

        #endregion

        #region -- Helper --

        /// <summary>
        /// Detect port
        /// </summary>
        /// <returns>Return the result</returns>
        public static string DetectPort()
        {
            string res = null;
            var sms = new SMS();

            var ports = SerialPort.GetPortNames();
            foreach (var i in ports)
            {
                var baudRate = 9600;
                var dataBits = 8;
                var readTimeOut = 300;
                var writeTimeOut = 300;

                try
                {
                    var port = sms.OpenPort(i, baudRate, dataBits, readTimeOut, writeTimeOut);
                    if (port != null)
                    {
                        #region -- Execute command --
                        sms.ExecCommand(port, "AT", 3000, "");
                        sms.ExecCommand(port, "AT+CPIN?", 3000, "");
                        #endregion

                        port.Close();
                        res = i;
                    }
                }
                catch { }
            }

            return res;
        }

        /// <summary>
        /// Parse messages
        /// </summary>
        /// <param name="input">Input message</param>
        /// <returns>Return the result</returns>
        private ShortMessageCollection ParseMessages(string input)
        {
            var res = new ShortMessageCollection();

            try
            {
                var r = new Regex(@"\+CMGL: (\d+),""(.+)"",""(.+)"",(.*),""(.+)""\r\n(.+)\r\n");
                var m = r.Match(input);
                while (m.Success)
                {
                    var msg = new ShortMessage
                    {
                        Index = m.Groups[1].Value,
                        Status = m.Groups[2].Value,
                        Sender = m.Groups[3].Value,
                        Alphabet = m.Groups[4].Value,
                        Sent = m.Groups[5].Value,
                        Message = m.Groups[6].Value
                    };
                    res.Add(msg);

                    m = m.NextMatch();
                }
            }
            catch (Exception ex)
            {
                throw ex;
            }

            return res;
        }

        #endregion
    }
}