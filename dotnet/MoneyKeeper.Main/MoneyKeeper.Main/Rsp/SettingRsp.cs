using Newtonsoft.Json;

namespace MoneyKeeper.Main.Rsp
{
    using Dto;

    /// <summary>
    /// Setting response
    /// </summary>
    public class SettingRsp : BaseRsp
    {
        #region -- Properties --

        [JsonProperty("result")]
        public Dto Result { get; set; }

        #endregion

        /// <summary>
        /// Data transfer object
        /// </summary>
        public class Dto : BaseDto<SettingDto> { }
    }
}