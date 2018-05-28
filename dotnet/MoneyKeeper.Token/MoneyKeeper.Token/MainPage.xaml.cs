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
                lblSecond.Text = t.ToString();

                if (t == 1 || string.IsNullOrEmpty(lblToken.Text))
                {
                    lblToken.Text = Utils.GetToken(d, 6);
                }

                return true;
            });
        }

        #endregion
    }
}