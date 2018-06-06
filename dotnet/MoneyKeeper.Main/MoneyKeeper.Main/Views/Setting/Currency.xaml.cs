using MoneyKeeper.Main.ViewModels;
using Xamarin.Forms;
using Xamarin.Forms.Xaml;

namespace MoneyKeeper.Main.Views.Setting
{
    /// <summary>
    /// Currency
    /// </summary>
    [XamlCompilation(XamlCompilationOptions.Compile)]
    public partial class Currency : ContentView
    {

        CurrencyVM vm;

        #region -- Methods --

        /// <summary>
        /// Initialize
        /// </summary>
        public Currency()
        {
            InitializeComponent();
            this.BindingContext = vm = new CurrencyVM();
        }

        private void pickerCurrency_SelectedIndexChanged(object sender, System.EventArgs e)
        {
            var name = pickerCurrency.Items[pickerCurrency.SelectedIndex];
            buttonCurrency.IsVisible = true;
        }

        private void Save_Clicked(object sender, System.EventArgs e)
        {
            var main = App.Current.MainPage;
            main.DisplayAlert("Message", "Unavailable Function!", "OK");
        }

        #endregion
    }
}