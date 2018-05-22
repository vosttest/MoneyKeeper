using Microsoft.AspNetCore.Mvc;
using System;
using System.IO.Ports;

namespace MoneyKeeper.OTP.Controllers
{
    using Models;
    using UTL;

    [Produces("application/json")]
    [Route("api/Sms")]
    public class SmsController : Controller
    {
        SerialPort _port = new SerialPort();
        SMS _sms = new SMS();
        ShortMessageCollection _smc = new ShortMessageCollection();

        [HttpPost]
        public string Post([FromBody]SMSReq req)
        {
            var res = "OK";
            try
            {
                _port = _sms.OpenPort(Program._port);
                _sms.SendSMS(_port, req.Phone, req.Message);
                _port.Close();

            }
            catch (Exception ex)
            {
                res = ex.Message;
            }

            return res;
        }
    }
}