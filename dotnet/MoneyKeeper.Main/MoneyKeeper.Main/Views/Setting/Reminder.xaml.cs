using Xamarin.Forms;
using Xamarin.Forms.Xaml;

namespace MoneyKeeper.Main.Views.Setting
{
    /// <summary>
    /// Reminder
    /// </summary>
    [XamlCompilation(XamlCompilationOptions.Compile)]
    public partial class Reminder : ContentView
    {
        #region -- Methods --

        /// <summary>
        /// Initialize
        /// </summary>
        public Reminder()
        {
            InitializeComponent();
        }

        private void Switch_Toggled(object sender, ToggledEventArgs e)
        {
            pickerReminder.IsVisible = true;
            buttonReminder.IsVisible = true;
            if (!e.Value)
            {
                pickerReminder.IsVisible = false;
                buttonReminder.IsVisible = false;
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