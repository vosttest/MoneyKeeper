using Newtonsoft.Json;

namespace MoneyKeeper.Main.Rsp
{
    /// <summary>
    /// Base response
    /// </summary>
    public class BaseRsp
    {
        #region -- Properties --

        [JsonProperty("status")]
        public string Status;

        [JsonProperty("message")]
        public string Message;

        #endregion
    }
}