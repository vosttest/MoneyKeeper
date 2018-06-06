using Newtonsoft.Json;
using System.Collections.Generic;

namespace MoneyKeeper.Main.Rsp
{
    using Dto;

    /// <summary>
    /// Category response
    /// </summary>
    public class CategoryRsp : BaseRsp
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

            [JsonProperty("parent")]
            public List<CategoryDto> Parent { get; set; }

            [JsonProperty("child")]
            public List<CategoryDto> Child { get; set; }

            #endregion
        }
    }
}