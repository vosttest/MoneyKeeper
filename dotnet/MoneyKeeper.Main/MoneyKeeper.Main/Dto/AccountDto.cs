using Newtonsoft.Json;
using System.Collections.Generic;

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

        #endregion
    }

    /// <summary>
    /// Account list
    /// </summary>
    public class AccountList : List<AccountDto>
    {
        #region -- Properties --

        public string Heading { get; set; }

        public List<AccountDto> Accounts => this;

        #endregion
    }

    /// <summary>
    /// Person
    /// </summary>
    public class Person
    {
        #region -- Properties --

        public string FirstName { get; set; }

        public string LastName { get; set; }

        public string DisplayName
        {
            get
            {
                return $"{LastName}, {FirstName}";
            }
        }

        #endregion
    }

    /// <summary>
    /// Person list
    /// </summary>
    public class PersonList : List<Person>
    {
        #region -- Properties --

        public string Heading { get; set; }

        public List<Person> Persons => this;

        #endregion
    }
}