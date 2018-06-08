using System.Collections.Generic;

namespace MoneyKeeper.Main.ViewModels
{

    /// <summary>
    /// Settings view model
    /// </summary>
    public class SettingsVM : BaseVM
    {
        #region -- Methods --

        /// <summary>
        /// Initialize
        /// </summary>
        public SettingsVM()
        {
            Title = "Settings";
        }
        List<string> settings = new List<string>
        {
            "Reminder",
            "Currency",
            "Language",
            "Lock",
            "Login Aunthentication",
            "Transaction"
        };
        public List<string> Settings => settings;

        #endregion
    }
}