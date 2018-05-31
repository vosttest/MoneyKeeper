using System;
using System.Collections.Generic;
using Xamarin.Forms;

namespace MoneyKeeper.Token
{
    using Common;
    using MoneyKeeper.Token.Views;
    using ViewModels;

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

            var setting = new List<SettingVM>();
            var s = new SettingVM();

            setting.Add(new SettingVM() { Type = typeof(About), Display = "eMK information" });
            setting.Add(new SettingVM() { Type = typeof(Login), Display = "Password" });
            setting.Add(new SettingVM() { Type = typeof(ChangeLanguage), Display = "Language" });
            setting.Add(new SettingVM() { Type = typeof(TimeSynchronization), Display = "Time Asynchronization" });
            s.All = setting;
            lstSetting.ItemsSource = setting;
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

        private async void OnListViewItemSelected(object sender, SelectedItemChangedEventArgs e)
        {
            (sender as ListView).SelectedItem = null;

            if (e.SelectedItem != null)
            {
                var pageData = e.SelectedItem as SettingVM;
                Page page = (Page)Activator.CreateInstance(pageData.Type);
                await Navigation.PushAsync(page);
            }
        }

        #endregion
    }
}