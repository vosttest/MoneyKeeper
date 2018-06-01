using Newtonsoft.Json;

namespace MoneyKeeper.Main.Req
{
    /// <summary>
    /// Base request
    /// </summary>
    public class BaseReq
    {
        #region -- Properties --

        [JsonProperty("keyword")]

        public object Keyword { get; set; }

        [JsonProperty("isOptional")]
        public bool? IsOptional { get; set; }

        #endregion
    }
}