using System;
using System.Collections.Generic;

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
        /// Type
        /// </summary>
        public Type Type { get; set; }

        /// <summary>
        /// Display
        /// </summary>
        public string Display { get; set; }

        /// <summary>
        /// All
        /// </summary>
        public List<SettingVM> All { set; get; }

        #endregion
    }
}