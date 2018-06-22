using System;
using Xamarin.Forms;
using Xamarin.Forms.Xaml;

namespace MoneyKeeper.Main.Views
{
    using Models;

    /// <summary>
    /// Settings
    /// </summary>
    [XamlCompilation(XamlCompilationOptions.Compile)]
    public partial class Settings : ContentPage
    {
        #region -- Methods --

        /// <summary>
        /// Initialize
        /// </summary>
        public Settings()
        {
            InitializeComponent();
        }

        #endregion

        #region -- Events --

        /// <summary>
        /// LstSetting item selected
        /// </summary>
        /// <param name="sender">Sender</param>
        /// <param name="e">Event</param>
        private void LstSetting_ItemSelected(object sender, SelectedItemChangedEventArgs e)
        {
            (sender as ListView).SelectedItem = null;

            if (e.SelectedItem != null)
            {
                var m = e.SelectedItem as MenuModel;
                var x = (ContentView)Activator.CreateInstance(m.Target);
                var v = new Popup { Content = x };
                Navigation.PushModalAsync(v);
            }
        }

        #endregion
    }
}