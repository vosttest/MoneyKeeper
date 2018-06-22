using Xamarin.Forms;
using Xamarin.Forms.Xaml;

namespace MoneyKeeper.Main.Views.Setting
{
    /// <summary>
    /// Lock
    /// </summary>
    [XamlCompilation(XamlCompilationOptions.Compile)]
    public partial class Lock : ContentView
    {
        #region -- Methods --

        /// <summary>
        /// Initialize
        /// </summary>
        public Lock()
        {
            InitializeComponent();
        }

        private void Switch_Toggled(object sender, ToggledEventArgs e)
        {
            buttonLock.IsVisible = true;
            if (!e.Value)
            {
                buttonLock.IsVisible = false;
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