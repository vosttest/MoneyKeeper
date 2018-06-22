using Newtonsoft.Json;

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
        public class Dto : BaseDto<AccountDto> { }
    }
}