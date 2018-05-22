namespace MoneyKeeper.OTP.Models
{
    public class SMSReq
    {
        public string Phone { get; set; }

        public string Message { get; set; }

        public string User { get; set; }

        public string Pass { get; set; }
    }
}