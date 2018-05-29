using System;
using Xamarin.Forms;

namespace MoneyKeeper.Token
{
    using Common;

    /// <summary>
    /// Main page
    /// </summary>
    public partial class MainPage : ContentPage
    {
        #region -- Methods --

        /// <summary>
        /// Initialize
        /// </summary>
        public MainPage()
        {
            InitializeComponent();

            Device.StartTimer(new TimeSpan(0, 0, 1), () =>
            {
                var d = DateTime.Now;
                var t = (60 - d.Second);
                lblDate.Text = d.ToString(Const.DateTime.FULL);
                lblSecond.Text = t.ToString() + "s";

                if (t == 60 || string.IsNullOrEmpty(lblToken.Text))
                {
                    var s = d.ToUniversalTime().ToString(Const.DateTime.TOKEN);
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