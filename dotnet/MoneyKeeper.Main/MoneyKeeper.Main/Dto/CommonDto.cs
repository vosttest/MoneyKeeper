using Newtonsoft.Json;

namespace MoneyKeeper.Main.Dto
{
    /// <summary>
    /// Common DTO
    /// </summary>
    public class CommonDto
    {
        #region -- Properties --

        [JsonProperty("type")]

        public string Type { get; set; }

        [JsonProperty("value")]

        public string Value { get; set; }

        [JsonProperty("text")]

        public string Text { get; set; }

        #endregion
    }
}