using System.Collections.Generic;

namespace MoneyKeeper.Main.ViewModels
{
    using Models;
    using Views.Setting;

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
            Title = "Setting";

            var m1 = new MenuModel { Title = "Reminder", Target = typeof(Reminder) };
            var m2 = new MenuModel { Title = "Currency", Target = typeof(Currency) };
            var m3 = new MenuModel { Title = "Language", Target = typeof(Language) };
            var m4 = new MenuModel { Title = "Lock", Target = typeof(Lock) };
            var m5 = new MenuModel { Title = "Login Authentication", Target = typeof(LoginAuthentication) };
            var m6 = new MenuModel { Title = "Transaction", Target = typeof(Transaction) };

            Data = new List<MenuModel> { m1, m2, m3, m4, m5, m6 };
        }

        #endregion

        #region -- Properties --

        /// <summary>
        /// Data
        /// </summary>
        public List<MenuModel> Data
        {
            get { return _data; }
            set { SetProperty(ref _data, value); }
        }

        #endregion

        #region -- Fields --

        /// <summary>
        /// Data
        /// </summary>
        private List<MenuModel> _data;

        #endregion
    }
}