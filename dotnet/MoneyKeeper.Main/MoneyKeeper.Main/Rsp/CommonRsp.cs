using Newtonsoft.Json;

namespace MoneyKeeper.Main.Rsp
{
    using Dto;

    /// <summary>
    /// Common response
    /// </summary>
    public class CommonRsp : BaseRsp
    {
        #region -- Properties --

        [JsonProperty("result")]
        public Dto Result { get; set; }

        #endregion

        /// <summary>
        /// Data transfer object
        /// </summary>
        public class Dto : BaseDto<CommonDto> { }
    }
}