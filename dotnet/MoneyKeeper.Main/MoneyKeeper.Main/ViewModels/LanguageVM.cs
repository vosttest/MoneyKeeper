using System.Collections.Generic;

namespace MoneyKeeper.Main.ViewModels
{
    class LanguageVM
    {
        List<string> language = new List<string>
        {
            "Tiếng Việt - Việt Nam",
            "English - United States",
            "English - United Kingdom"
        };

        public List<string> Language => language;
    }
}
