using Newtonsoft.Json;
using System.Collections.Generic;
using System.Linq;

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

        [JsonProperty("code")]
        public string Type { get; set; }

        [JsonProperty("text")]
        public string Text { get; set; }

        [JsonProperty("balance")]
        public double Balance { get; set; }

        [JsonProperty("currency")]
        public string Currency { get; set; }

        public string BalanceCurrency
        {
            get
            {
                return $"{Balance:n0} {Currency}";
            }
        }

        #endregion
    }

    /// <summary>
    /// Account list
    /// </summary>
    public class AccountList : List<AccountDto>
    {
        #region -- Properties --

        public string Heading { get; set; }

        public string Total
        {
            get
            {
                var t = Accounts.Sum(p => p.Balance);
                return $"{t:n0}";
            }
        }

        public List<AccountDto> Accounts => this;

        #endregion
    }
}