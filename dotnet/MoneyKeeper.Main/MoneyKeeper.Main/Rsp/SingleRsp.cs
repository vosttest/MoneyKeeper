using Newtonsoft.Json;

namespace MoneyKeeper.Main.Rsp
{
    /// <summary>
    /// Single response
    /// </summary>
    public class SingleRsp : BaseRsp
    {
        #region -- Properties --

        [JsonProperty("result")]

        public object Result;

        #endregion
    }
}