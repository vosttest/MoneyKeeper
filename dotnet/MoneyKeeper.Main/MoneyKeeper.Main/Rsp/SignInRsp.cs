using Newtonsoft.Json;

namespace MoneyKeeper.Main.Rsp
{
    /// <summary>
    /// Sign in response
    /// </summary>
    public class SignInRsp : BaseRsp
    {
        #region -- Properties --

        [JsonProperty("result")]
        public Dto Result { get; set; }

        #endregion

        /// <summary>
        /// Data transfer object
        /// </summary>
        public class Dto
        {
            #region -- Properties --

            [JsonProperty("authen")]
            public bool Authen { get; set; }

            [JsonProperty("key")]
            public string Key { get; set; }

            #endregion
        }
    }
}