using MoneyKeeper.Main.Views.Setting;
using Xamarin.Forms;
using Xamarin.Forms.Xaml;

namespace MoneyKeeper.Main.Views
{
    #region -- Methods --

    /// <summary>
    /// Settings
    /// </summary>
    [XamlCompilation(XamlCompilationOptions.Compile)]
    public partial class Settings : ContentPage
    {
        /// <summary>
        /// Initialize
        /// </summary>
        public Settings()
        {
            InitializeComponent();
        }

        private async void Button_Clicked(object sender, System.EventArgs e)
        {
            await Navigation.PushAsync(new Settings() { Content = new Reminder() });
        }

        private async void Button_Clicked_1(object sender, System.EventArgs e)
        {
            await Navigation.PushAsync(new Settings() { Content = new Currency() });
        }

        private async void Button_Clicked_2(object sender, System.EventArgs e)
        {
            await Navigation.PushAsync(new Settings() { Content = new Language() });
        }

        private async void Button_Clicked_3(object sender, System.EventArgs e)
        {
            await Navigation.PushAsync(new Settings() { Content = new LoginAuthentication() });
        }

        private async void Button_Clicked_4(object sender, System.EventArgs e)
        {
            await Navigation.PushAsync(new Settings() { Content = new Transaction() });
        }

        private async void Button_Clicked_5(object sender, System.EventArgs e)
        {
            await Navigation.PushAsync(new Settings() { Content = new Lock() });
        }

        #endregion
    }
}