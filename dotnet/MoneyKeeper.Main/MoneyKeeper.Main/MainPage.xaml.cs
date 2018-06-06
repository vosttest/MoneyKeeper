using System;
using System.Collections.Generic;
using Xamarin.Forms;

namespace MoneyKeeper.Main
{
    using Common;
    using Models;
    using Views;

    /// <summary>
    /// Main page
    /// </summary>
    public partial class MainPage : MasterDetailPage
    {
        #region -- Methods --

        /// <summary>
        /// Initialize
        /// </summary>
        public MainPage()
        {
            InitializeComponent();

            var m1 = new MenuModel { Title = "Dashboard", Icon = "dashboard.png", Target = typeof(Dashboard) };
            var m2 = new MenuModel { Title = "Account", Icon = "account.png", Target = typeof(Accounts) };
            var m3 = new MenuModel { Title = "Reports", Icon = "report.png", Target = typeof(Reports) };
            var m4 = new MenuModel { Title = "Vouchers", Icon = "voucher.png", Target = typeof(Vouchers) };
            var m5 = new MenuModel { Title = "Categories", Icon = "category.png", Target = typeof(Categories) };
            var m6 = new MenuModel { Title = "Settings", Icon = "setting.png", Target = typeof(Settings) };
            var m7 = new MenuModel { Title = "About", Icon = "about.png", Target = typeof(About) };
            var m8 = new MenuModel { Title = "Sign Out", Icon = "signout.png" };
            lstMenu.ItemsSource = new List<MenuModel> { m1, m2, m3, m4, m5, m6, m7, m8 };

            var v = (Page)Activator.CreateInstance(typeof(Dashboard));
            Detail = new NavigationPage(v);
        }

        private async void LstMenu_ItemSelected(object sender, SelectedItemChangedEventArgs e)
        {
            var m = e.SelectedItem as MenuModel;
            if (m.Title == "Sign Out")
            {
                var ok = await DisplayAlert("Confirm", "Do you want to sign out ?", "Yes", "No");
                if (ok)
                {
                    Utils.SetVar(Const.Authentication.JWT, string.Empty);
                    await Navigation.PushModalAsync(new SignIn());
                }
            }
            else
            {
                var v = (Page)Activator.CreateInstance(m.Target);
                Detail = new NavigationPage(v);
            }

            IsPresented = false;
        }

        #endregion
    }
}