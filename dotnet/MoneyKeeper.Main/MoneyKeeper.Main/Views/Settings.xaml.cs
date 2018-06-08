using System.Collections.Generic;
using Xamarin.Forms;
using Xamarin.Forms.Xaml;

namespace MoneyKeeper.Main.Views
{
    using Models;
    using Views.Setting;

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

            var p1 = new Popup { Content = new Reminder() };
            var m1 = new MenuModel { Title = "Reminder", Target = p1.GetType() };
            var p2 = new Popup { Content = new Currency() };
            var m2 = new MenuModel { Title = "Currency", Target = p2.GetType() };
            var p3 = new Popup { Content = new Language() };
            var m3 = new MenuModel { Title = "Language", Target = p1.GetType() };
            var p4 = new Popup { Content = new Lock() };
            var m4 = new MenuModel { Title = "Lock", Target = p2.GetType() };
            var p5 = new Popup { Content = new LoginAuthentication() };
            var m5 = new MenuModel { Title = "LoginAuthentication", Target = p1.GetType() };
            var p6 = new Popup { Content = new Transaction() };
            var m6 = new MenuModel { Title = "Transaction", Target = p2.GetType() };
            lstMenu.ItemsSource = new List<MenuModel> { m1, m2, m3, m4, m5, m6 };
        }

        /// <summary>
        /// LstMenu item selected
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void LstMenu_ItemSelected(object sender, SelectedItemChangedEventArgs e)
        {
            (sender as ListView).SelectedItem = null;
            var m = e.SelectedItem as MenuModel;
            if (e.SelectedItem != null)

            {
                if (m.Title == "Reminder")
                {
                    var x = new Reminder();
                    var v = new Popup { Content = x };
                    Navigation.PushAsync(v);
                }
                else if (m.Title == "Currency")
                {
                    var x = new Currency();
                    var v = new Popup { Content = x };
                    Navigation.PushAsync(v);
                }
                else if (m.Title == "Language")
                {
                    var x = new Language();
                    var v = new Popup { Content = x };
                    Navigation.PushAsync(v);
                }
                else if (m.Title == "Lock")
                {
                    var x = new Lock();
                    var v = new Popup { Content = x };
                    Navigation.PushAsync(v);
                }
                else if (m.Title == "LoginAuthentication")
                {
                    var x = new LoginAuthentication();
                    var v = new Popup { Content = x };
                    Navigation.PushAsync(v);
                }
                else if (m.Title == "Transaction")
                {
                    var x = new Transaction();
                    var v = new Popup { Content = x };
                    Navigation.PushAsync(v);
                }
            }
        }

        #endregion
    }
}