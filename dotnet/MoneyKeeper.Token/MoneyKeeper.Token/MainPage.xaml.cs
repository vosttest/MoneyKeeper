using System;
using System.Collections.Generic;
using Xamarin.Forms;

namespace MoneyKeeper.Token
{
    using Common;
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

            setting.Add(new SettingVM() { Display = "eMK information" });
            setting.Add(new SettingVM() { Display = "Password" });
            setting.Add(new SettingVM() { Display = "Language" });
            setting.Add(new SettingVM() { Display = "Time Asynchronization" });
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

        #endregion
    }
}