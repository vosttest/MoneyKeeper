using Newtonsoft.Json;
using System;

namespace MoneyKeeper.Token.Dto
{
    /// <summary>
    /// Payload DTO
    /// </summary>
    public class PayloadDto
    {
        #region -- Properties --

        [JsonProperty("id")]

        public int Id { get; set; }

        [JsonProperty("userName")]

        public String UserName { get; set; }

        [JsonProperty("firstName")]

        public String FirstName { get; set; }

        [JsonProperty("lastName")]

        public String LastName { get; set; }

        [JsonProperty("accountNo")]

        public String AccountNo { get; set; }

        [JsonProperty("email")]

        public String Email { get; set; }

        [JsonProperty("contactNo")]

        public String ContactNo { get; set; }

        [JsonProperty("remarks")]

        public String Remarks { get; set; }

        [JsonProperty("uuid")]

        public Guid Uuid { get; set; }

        #endregion
    }
}