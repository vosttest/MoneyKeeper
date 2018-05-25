namespace MoneyKeeper.Api.Req
{
    /// <summary>
    /// SMS request
    /// </summary>
    public class SMSReq
    {
        #region -- Properties --

        public string Phone { get; set; }

        public string Message { get; set; }

        public string User { get; set; }

        public string Pass { get; set; }

        #endregion
    }
}