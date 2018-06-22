using Newtonsoft.Json;
using System;

namespace MoneyKeeper.Main.Req
{
    /// <summary>
    /// Voucher search request
    /// </summary>
    public class VoucherSearchReq
    {
        /// <summary>
        /// Find data in text or description column
        /// </summary>
        [JsonProperty("keyword")]

        public string Keyword { get; set; }

        [JsonProperty("date")]

        public DateTime Date { get; set; }
    }
}