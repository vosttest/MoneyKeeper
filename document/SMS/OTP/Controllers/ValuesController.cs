using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
using System.Collections.Generic;
using System.IO.Ports;
using UTL;

namespace OTP.Controllers
{
    [Authorize]
    [Route("api/[controller]")]
    public class ValuesController : Controller
    {
        SerialPort port = new SerialPort();
        SMS objclsSMS = new SMS();
        ShortMessageCollection objShortMessageCollection = new ShortMessageCollection();

        // GET api/values
        [HttpGet]
        public IEnumerable<string> Get()
        {
            string[] ports = SerialPort.GetPortNames();
            var res = "";
            string cboPortName = "COM8";
            int baudRate = 9600;
            int dataBits = 8;
            int readTimeOut = 300;
            int writeTimeOut = 300;

            try
            {
                this.port = objclsSMS.OpenPort(cboPortName, baudRate, dataBits, readTimeOut, writeTimeOut);
                if (this.port != null)
                {
                    res = "Modem is connected at PORT " + cboPortName;
                }
                else
                {
                    res = "Invalid port settings";
                }
            }
            catch //(Exception ex)
            {
            }

            return ports;
        }

        // GET api/values/5
        [HttpGet("{id}")]
        public string Get(int id)
        {
            return "value";
        }

        // POST api/values
        [HttpPost]
        public void Post([FromBody]string value)
        {
            // For more information on protecting this API from Cross Site Request Forgery (CSRF) attacks, see https://go.microsoft.com/fwlink/?LinkID=717803
        }

        // PUT api/values/5
        [HttpPut("{id}")]
        public void Put(int id, [FromBody]string value)
        {
            // For more information on protecting this API from Cross Site Request Forgery (CSRF) attacks, see https://go.microsoft.com/fwlink/?LinkID=717803
        }

        // DELETE api/values/5
        [HttpDelete("{id}")]
        public void Delete(int id)
        {
            // For more information on protecting this API from Cross Site Request Forgery (CSRF) attacks, see https://go.microsoft.com/fwlink/?LinkID=717803
        }
    }
}