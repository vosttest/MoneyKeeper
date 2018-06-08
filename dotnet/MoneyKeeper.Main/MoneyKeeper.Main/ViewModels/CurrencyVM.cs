using System.Collections.Generic;

namespace MoneyKeeper.Main.ViewModels
{

    /// <summary>
    /// Currency view model
    /// </summary>
    class CurrencyVM : BaseVM
    {
        #region -- Methods --

        /// <summary>
        /// Initialize
        /// </summary>
        List<string> currency = new List<string>
        {
            "VND",
            "USD",
            "EUR"
        };
        public List<string> Currency => currency;

        #endregion
    }
}