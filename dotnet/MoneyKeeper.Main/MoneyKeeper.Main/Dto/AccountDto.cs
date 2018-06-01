using Newtonsoft.Json;

namespace MoneyKeeper.Main.Dto
{
    /// <summary>
    /// Account DTO
    /// </summary>
    public class AccountDto
    {
        #region -- Properties --

        [JsonProperty("id")]
        public int Id { get; set; }

        [JsonProperty("type")]
        public string Type { get; set; }

        [JsonProperty("text")]
        public string Text { get; set; }

        [JsonProperty("balance")]
        public float? Balance { get; set; }

        #endregion
    }
}