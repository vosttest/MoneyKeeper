using Newtonsoft.Json;

namespace MoneyKeeper.Main.Rsp
{
    using Dto;

    /// <summary>
    /// Voucher response
    /// </summary>
    public class VoucherRsp : BaseRsp
    {
        #region -- Properties --

        [JsonProperty("result")]
        public Dto Result { get; set; }

        #endregion

        /// <summary>
        /// Data transfer object
        /// </summary>
        public class Dto : BaseDto<VoucherDto> { }
    }
}