using System.Collections.Generic;

namespace MoneyKeeper.Main.ViewModels
{

    /// <summary>
    /// Language view model
    /// </summary>
    class LanguageVM : BaseVM
    {
        #region -- Methods --

        /// <summary>
        /// Initialize
        /// </summary>
        List<string> language = new List<string>
        {
            "Tiếng Việt - Việt Nam",
            "English - United States",
            "English - United Kingdom"
        };

        public List<string> Language => language;

        #endregion
    }
}