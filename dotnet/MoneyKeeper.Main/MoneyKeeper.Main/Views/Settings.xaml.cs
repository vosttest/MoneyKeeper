using System;
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

            var m3 = new MenuModel { Title = "Language", Target = typeof(Language) };
            var m4 = new MenuModel { Title = "Lock", Target = typeof(Lock) };
            var m5 = new MenuModel { Title = "LoginAuthentication", Target = typeof(LoginAuthentication) };
            var m6 = new MenuModel { Title = "Transaction", Target = typeof(Transaction) };
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

            if (e.SelectedItem != null)
            {
                var m = e.SelectedItem as MenuModel;
                var v = (Page)Activator.CreateInstance(m.Target);
                Navigation.PushAsync(v);
            }
        }

        #endregion
    }
}