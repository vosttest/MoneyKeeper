using Xamarin.Forms;
using Xamarin.Forms.Xaml;

namespace MoneyKeeper.Main.Views.Setting
{
    /// <summary>
    /// Transaction
    /// </summary>
    [XamlCompilation(XamlCompilationOptions.Compile)]
    public partial class Transaction : ContentView
    {
        #region -- Methods --

        /// <summary>
        /// Initialize
        /// </summary>
        public Transaction()
        {
            InitializeComponent();
        }

        private void Switch_Toggled(object sender, ToggledEventArgs e)
        {
            pickerTransaction.IsEnabled = true;
            buttonTransaction.IsVisible = true;
            if (!e.Value)
            {
                pickerTransaction.IsEnabled = false;
                buttonTransaction.IsVisible = false;
            }
        }

        private void Save_Clicked(object sender, System.EventArgs e)
        {
            var main = App.Current.MainPage;
            main.DisplayAlert("Message", "Unavailable Function!", "OK");
        }

        #endregion
    }
}