using Newtonsoft.Json;
using System;

namespace MoneyKeeper.Main.Dto
{
    /// <summary>
    /// Voucher detail DTO
    /// </summary>
    public class VoucherDetailDto
    {
        #region -- Properties --

        [JsonProperty("id")]

        public int? Id { get; set; }

        [JsonProperty("voucherId")]

        public int VoucherId { get; set; }

        [JsonProperty("accountId")]

        public int AccountId { get; set; }

        [JsonProperty("type")]

        public string Type { get; set; }

        [JsonProperty("total")]

        public double Total { get; set; }

        [JsonProperty("description")]

        public string Description { get; set; }

        [JsonProperty("payee")]

        public string Payee { get; set; }

        [JsonProperty("payer")]

        public string Payer { get; set; }

        [JsonProperty("toAccount")]

        public int? ToAccount { get; set; }

        [JsonProperty("userId")]

        public int UserId { get; set; }

        [JsonProperty("amount")]

        public double Amount { get; set; }

        [JsonProperty("categoryText")]

        public String CategoryText { get; set; }

        [JsonProperty("category")]

        public string Category { get; set; }

        [JsonProperty("icon")]

        public string Icon { get; set; }

        [JsonProperty("accountText")]

        public string AccountText { get; set; }

        #endregion
    }
}