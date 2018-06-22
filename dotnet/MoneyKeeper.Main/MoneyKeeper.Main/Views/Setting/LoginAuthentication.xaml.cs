using Xamarin.Forms;
using Xamarin.Forms.Xaml;

namespace MoneyKeeper.Main.Views.Setting
{
    /// <summary>
    /// LoginAuthentication
    /// </summary>
    [XamlCompilation(XamlCompilationOptions.Compile)]
    public partial class LoginAuthentication : ContentView
    {
        #region -- Methods --

        /// <summary>
        /// LoginAuthentication
        /// </summary>
        public LoginAuthentication()
        {
            InitializeComponent();
        }

        private void Switch_Toggled(object sender, ToggledEventArgs e)
        {
            pickerLoginAuthen.IsEnabled = true;
            buttonLoginAuthen.IsVisible = true;
            if (!e.Value)
            {
                pickerLoginAuthen.IsEnabled = false;
                buttonLoginAuthen.IsVisible = false;
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