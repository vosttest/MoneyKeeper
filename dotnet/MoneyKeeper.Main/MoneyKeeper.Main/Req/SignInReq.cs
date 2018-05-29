using Newtonsoft.Json;

namespace MoneyKeeper.Main.Req
{
    /// <summary>
    /// Base request
    /// </summary>
    public class SignInReq
    {
        #region -- Properties --

        [JsonProperty("userName")]
        public string UserName { get; set; }

        [JsonProperty("password")]
        public string Password { get; set; }

        [JsonProperty("clientKey")]
        public string ClientKey { get; set; }

        [JsonProperty("token")]
        public string Token { get; set; }

        [JsonProperty("sendToken")]
        public bool SendToken { get; set; }

        #endregion
    }
}