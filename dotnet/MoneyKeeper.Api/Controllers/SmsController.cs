using Microsoft.AspNetCore.Mvc;
using System;
using System.IO.Ports;

namespace MoneyKeeper.Api.Controllers
{
    using Req;
    using Sms;

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
                var user = "qwuupwck8537";
                var pass = "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";
                if (req.User == user && req.Pass == pass)
                {
                    _port = _sms.OpenPort(Program._port);
                    _sms.SendSMS(_port, req.Phone, req.Message);
                    _port.Close();
                }
                else
                {
                    res = "Wrong user pass";
                }

            }
            catch (Exception ex)
            {
                res = ex.Message;
            }

            return res;
        }
    }
}