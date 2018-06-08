using Newtonsoft.Json;
using System.Collections.Generic;

namespace MoneyKeeper.Main.Dto
{
    /// <summary>
    /// Base data transfer object
    /// </summary>
    public class BaseDto<T>
    {
        #region -- Properties --

        [JsonProperty("count")]
        public int Count { get; set; }

        [JsonProperty("data")]
        public List<T> Data { get; set; }

        #endregion
    }
}