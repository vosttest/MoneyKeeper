using System;
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
        #region-- Methods --

        /// <summary>
        /// Initialize
        /// </summary>
        public Language()
        {
            InitializeComponent();
        }

        #endregion

        #region -- Events --

        /// <summary>
        /// Picker currency selected index changed
        /// </summary>
        /// <param name="sender">Sender</param>
        /// <param name="e">Event</param>
        private void Pic_SelectedIndexChanged(object sender, EventArgs e)
        {
            var name = pic.Items[pic.SelectedIndex];
            btnSave.IsVisible = true;
        }

        /// <summary>
        /// Save clicked
        /// </summary>
        /// <param name="sender">Sender</param>
        /// <param name="e">Event</param>
        private void Save_Clicked(object sender, EventArgs e)
        {
            var main = App.Current.MainPage;
            main.DisplayAlert("Message", "Unavailable Function!", "OK");
        }

        #endregion
    }
}