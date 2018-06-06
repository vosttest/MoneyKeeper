using System.Collections.Generic;

namespace MoneyKeeper.Main.ViewModels
{
    class CurrencyVM
    {
        List<string> currency = new List<string>
        {
            "VND",
            "USD",
            "EUR"
        };
        public List<string> Currency => currency;

    }
}
