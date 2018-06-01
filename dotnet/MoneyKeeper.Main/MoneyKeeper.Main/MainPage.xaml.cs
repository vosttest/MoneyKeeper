using System;
using System.Collections.Generic;
using Xamarin.Forms;

namespace MoneyKeeper.Main
{
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
            lstMenu.ItemsSource = new List<MenuModel> { m1, m2, m3, m4, m5, m6 };

            Detail = new NavigationPage((Page)Activator.CreateInstance(typeof(Dashboard)));
        }

        private void LstMenu_ItemSelected(object sender, SelectedItemChangedEventArgs e)
        {
            var item = (MenuModel)e.SelectedItem;
            Type page = item.Target;

            Detail = new NavigationPage((Page)Activator.CreateInstance(page));
            IsPresented = false;
        }

        #endregion
    }
}