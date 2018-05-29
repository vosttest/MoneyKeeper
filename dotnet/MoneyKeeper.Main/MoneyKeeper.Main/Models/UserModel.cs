namespace MoneyKeeper.Main.Models
{
    /// <summary>
    /// User model
    /// </summary>
    public class UserModel : BaseModel
    {
        #region -- Methods --

        /// <summary>
        /// Initialize
        /// </summary>
        public UserModel() { }

        #endregion

        #region -- Properties --

        /// <summary>
        /// User name
        /// </summary>
        public string UserName { get; set; }

        /// <summary>
        /// Password
        /// </summary>
        public string Password { get; set; }


        #endregion
    }
}