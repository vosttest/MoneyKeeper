using MoneyKeeper.Main.Views.Setting;
using Xamarin.Forms;
using Xamarin.Forms.Xaml;

namespace MoneyKeeper.Main.Views
{
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

        private void Button_Clicked(object sender, System.EventArgs e)
        {
            var m = new Settings()
            {
                Content = new Reminder()
            };

            Navigation.PushAsync(m);

        }

    }

}