using MoneyKeeper.Main.ViewModels;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using Xamarin.Forms;
using Xamarin.Forms.Xaml;

namespace MoneyKeeper.Main.Views
{
    /// <summary>
    /// Categories
    /// </summary>
    [XamlCompilation(XamlCompilationOptions.Compile)]
    public partial class Vouchers : ContentPage
    {
        private List<VoucherClass> _voucher;
        ObservableCollection<VoucherClass> voucher = new ObservableCollection<VoucherClass>();
        /// <summary>
        /// Initialize
        /// </summary>
        public Vouchers()
        {
            InitializeComponent();
            voucher.Add(new VoucherClass
            {
                Date = "Wednesday",
                StartDate = "06-Jun-2018",
                Expense = "Expense:",
                Income = "Income",
                Total = "50.000",
                Category = "Lend",
                Type = "Expense",
                Amount = "50.000",
                Account = "Cash",
                Icon = "dashboard.png"
            });
            LstVoucher.ItemsSource = voucher;
        }
    }
}