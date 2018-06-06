using MoneyKeeper.Main.ViewModels;
using Xamarin.Forms;
using Xamarin.Forms.Xaml;

namespace MoneyKeeper.Main.Views.Setting
{
    /// <summary>
    /// Language
    /// </summary>
    [XamlCompilation(XamlCompilationOptions.Compile)]
    public partial class Language : ContentView
    {

        LanguageVM vm;

        #region -- Methods --

        /// <summary>
        /// Initialize
        /// </summary>
        public Language()
        {
            InitializeComponent();
            this.BindingContext = vm = new LanguageVM();
        }

        private void pickerLanguage_SelectedIndexChanged(object sender, System.EventArgs e)
        {
            var name = pickerLanguage.Items[pickerLanguage.SelectedIndex];
            buttonLanguage.IsVisible = true;
        }

        private void Save_Clicked(object sender, System.EventArgs e)
        {
            var main = App.Current.MainPage;
            main.DisplayAlert("Message", "Unavailable Function!", "OK");

        }

        #endregion
    }
}