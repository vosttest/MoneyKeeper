using Newtonsoft.Json;

namespace MoneyKeeper.Token.Rsp
{
    /// <summary>
    /// Base response
    /// </summary>
    public class BaseRsp
    {
        #region -- Properties --

        [JsonProperty("status")]
        public string Status { get; set; }

        [JsonProperty("message")]
        public string Message { get; set; }

        #endregion
    }
}