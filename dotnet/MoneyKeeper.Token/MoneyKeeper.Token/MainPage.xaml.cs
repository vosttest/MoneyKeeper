using System;
using System.Collections.Generic;
using Xamarin.Forms;

namespace MoneyKeeper.Token
{
    using Common;
    using Models;
    using Views;

    /// <summary>
    /// Main page
    /// </summary>
    public partial class MainPage : TabbedPage
    {
        #region -- Methods --

        /// <summary>
        /// Initialize
        /// </summary>
        public MainPage()
        {
            InitializeComponent();

            CreatePassword();

            var m1 = new MenuModel { Title = "About eToken MK information", Target = typeof(About) };
            var m2 = new MenuModel { Title = "Password", Target = typeof(Password) };
            var m3 = new MenuModel { Title = "Language", Target = typeof(Language) };
            var m4 = new MenuModel { Title = "Time synchronization", Target = typeof(Synchronization) };
            lstMenu.ItemsSource = new List<MenuModel> { m1, m2, m3, m4 };
        }

        public void CreatePassword()
        {
            Device.StartTimer(new TimeSpan(0, 0, 1), () =>
            {
                var d = DateTime.Now;
                var t = (60 - d.Second);
                lblDate.Text = d.ToString(Const.DateTime.FULL);
                lblSecond.Text = t.ToString() + " s";

                if (t == 60 || string.IsNullOrEmpty(lblToken.Text))
                {
                    var s = d.ToUniversalTime().ToString(Const.DateTime.TOKEN);
                    s += App.UUID;
                    int n = Const.Authentication.TOKEN_NUMBER;
                    lblToken.Text = Utils.GetToken(s, n);
                    progressBar.Progress = d.Second / 60f;
                }

                var f = 1 / 60f;

                if (progressBar.Progress < 1)
                {
                    Device.BeginInvokeOnMainThread(() => progressBar.Progress += f);
                }

                return true;
            });
        }

        private void LstMenu_ItemSelected(object sender, SelectedItemChangedEventArgs e)
        {
            (sender as ListView).SelectedItem = null;

            if (e.SelectedItem != null)
            {
                var m = e.SelectedItem as MenuModel;
                var v = (Page)Activator.CreateInstance(m.Target);
                Navigation.PushModalAsync(v);
            }
        }

        #endregion
    }
}