using MoneyKeeper.Main.Views;
using System.Collections.ObjectModel;

namespace MoneyKeeper.Main.ViewModels
{
    public class VoucherVM
    {
        public ObservableCollection<VoucherClass> voucher { get; set; }
    }
    public class VoucherClass
    {
        public string Date { get; set; }

        public string StartDate { get; set; }

        public string Expense { get; set; }

        public string Income { get; set; }

        public string Total { get; set; }

        public string Category { get; set; }

        public string Type { get; set; }

        public string Amount { get; set; }

        public string Account { get; set; }

        public string Icon { get; set; }
    }
}