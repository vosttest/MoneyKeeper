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
                var user = "9C8F616E0317998A10F3518D6272EA31B3CB9FECAA4C9FAF1A2FEDB6B6C2AA9E";
                var pass = "E3B51E3A5B0035DDEA2D063728B4C79E39E9F259138CB42481AFA41084DB9F0B";
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