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

            var m1 = new MenuModel { Title = "Dashboard", Icon = "moneyKeeper.png", Target = typeof(Dashboard) };
            var m2 = new MenuModel { Title = "Account", Icon = "moneyKeeper.png", Target = typeof(Accounts) };
            lstMenu.ItemsSource = new List<MenuModel> { m1, m2 };

            Detail = new NavigationPage((Page)Activator.CreateInstance(typeof(SignIn)));
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