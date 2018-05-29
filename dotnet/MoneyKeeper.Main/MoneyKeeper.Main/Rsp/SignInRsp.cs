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
        public SignIn Result { get; set; }

        #endregion

        public class SignIn : BaseRsp
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