using Newtonsoft.Json;
using System.Collections.Generic;

namespace MoneyKeeper.Main.Dto
{
    /// <summary>
    /// Category DTO
    /// </summary>
    public class CategoryDto
    {
        #region -- Properties --

        [JsonProperty("id")]
        public int Id { get; set; }

        [JsonProperty("code")]
        public string Code { get; set; }

        [JsonProperty("text")]
        public string Text { get; set; }

        [JsonProperty("parentId")]
        public int? ParentId { get; set; }

        [JsonProperty("description")]
        public string Description { get; set; }

        [JsonProperty("count")]
        public int Count { get; set; }

        [JsonProperty("icon")]
        public string Icon { get; set; }

        [JsonProperty("child")]
        public List<CategoryDto> Child { get; set; }

        #endregion
    }
}