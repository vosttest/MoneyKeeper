using Newtonsoft.Json;
using System.Collections.Generic;

namespace MoneyKeeper.Main.Rsp
{
    using Dto;

    /// <summary>
    /// Account response
    /// </summary>
    public class AccountRsp : BaseRsp
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

            [JsonProperty("count")]
            public int Count { get; set; }

            [JsonProperty("data")]
            public List<AccountDto> Data { get; set; }

            #endregion
        }
    }
}