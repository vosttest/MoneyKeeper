using Newtonsoft.Json;
using System;
using System.Collections.Generic;

namespace MoneyKeeper.Main.Dto
{
    /// <summary>
    /// Voucher DTO
    /// </summary>
    public class VoucherDto
    {
        #region -- Properties --

        [JsonProperty("startDate")]

        public DateTime StartDate { get; set; }

        [JsonProperty("voucherDetail")]

        public List<VoucherDetailDto> VoucherDetail { get; set; }

        [JsonProperty("totalExpense")]

        public double TotalExpense { get; set; }

        [JsonProperty("totalIncome")]

        public double TotalIncome { get; set; }

        #endregion
    }
}