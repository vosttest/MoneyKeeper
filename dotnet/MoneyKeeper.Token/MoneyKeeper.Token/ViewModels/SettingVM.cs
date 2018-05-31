namespace MoneyKeeper.Token.ViewModels
{
    public class SettingVM : BaseVM
    {
        #region -- Methods --

        /// <summary>
        /// Setting view model
        /// </summary>
        public SettingVM()
        {
            Title = "Setting";
        }

        #endregion

        #region -- Properties --

        /// <summary>
        /// Display name
        /// </summary>
        public string Display { get; set; }

        #endregion
    }
}