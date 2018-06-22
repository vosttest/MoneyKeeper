using Newtonsoft.Json;

namespace MoneyKeeper.Main.Dto
{
    /// <summary>
    /// Setting DTO
    /// </summary>
    public class SettingDto
    {
        #region -- Properties --

        [JsonProperty("code")]

        public string Code { get; set; }

        [JsonProperty("value")]

        public string Value { get; set; }

        [JsonProperty("text")]

        public string Text { get; set; }

        #endregion
    }
}