using Xamarin.Forms;
using Xamarin.Forms.Xaml;

namespace MoneyKeeper.Main.Views.Shared
{
    /// <summary>
    /// Token popup
    /// </summary>
    [XamlCompilation(XamlCompilationOptions.Compile)]
    public partial class TokenPopup : ContentView
    {
        #region -- Methods --

        /// <summary>
        /// Initialize
        /// </summary>
        public TokenPopup()
        {
            InitializeComponent();
        }

        /// <summary>
        /// Initialize
        /// </summary>
        /// <param name="userName">User name</param>
        /// <param name="password">password</param>
        /// <param name="clientKey">Client key</param>
        public TokenPopup(string userName, string password, string clientKey) : this()
        {
            this.userName.Text = userName;
            this.password.Text = password;
            this.clientKey.Text = clientKey;

            code1.Focus();
        }

        /// <summary>
        /// Code1 text changed
        /// </summary>
        /// <param name="sender">Sender</param>
        /// <param name="e">Event</param>
        private void Code1_TextChanged(object sender, TextChangedEventArgs e)
        {
            var t = e.NewTextValue;
            if (t.Length > 1)
            {
                code1.Text = t.Substring(1, 1);
                code2.Focus();
            }
            else if (t.Length == 1)
            {
                code2.Focus();
            }
        }

        /// <summary>
        /// Code2 text changed
        /// </summary>
        /// <param name="sender">Sender</param>
        /// <param name="e">Event</param>
        private void Code2_TextChanged(object sender, TextChangedEventArgs e)
        {
            var t = e.NewTextValue;
            if (t.Length > 1)
            {
                code2.Text = t.Substring(1, 1);
                code3.Focus();
            }
            else if (t.Length == 0)
            {
                code1.Focus();
            }
            else
            {
                code3.Focus();
            }
        }

        /// <summary>
        /// Code3 text changed
        /// </summary>
        /// <param name="sender">Sender</param>
        /// <param name="e">Event</param>
        private void Code3_TextChanged(object sender, TextChangedEventArgs e)
        {
            var t = e.NewTextValue;
            if (t.Length > 1)
            {
                code3.Text = t.Substring(1, 1);
                code4.Focus();
            }
            else if (t.Length == 0)
            {
                code2.Focus();
            }
            else
            {
                code4.Focus();
            }
        }

        /// <summary>
        /// Code4 text changed
        /// </summary>
        /// <param name="sender">Sender</param>
        /// <param name="e">Event</param>
        private void Code4_TextChanged(object sender, TextChangedEventArgs e)
        {
            var t = e.NewTextValue;
            if (t.Length > 1)
            {
                code4.Text = t.Substring(1, 1);
                code5.Focus();
            }
            else if (t.Length == 0)
            {
                code3.Focus();
            }
            else
            {
                code5.Focus();
            }
        }

        /// <summary>
        /// Code5 text changed
        /// </summary>
        /// <param name="sender">Sender</param>
        /// <param name="e">Event</param>
        private void Code5_TextChanged(object sender, TextChangedEventArgs e)
        {
            var t = e.NewTextValue;
            if (t.Length > 1)
            {
                code5.Text = t.Substring(1, 1);
                code6.Focus();
            }
            else if (t.Length == 0)
            {
                code4.Focus();
            }
            else
            {
                code6.Focus();
            }
        }

        /// <summary>
        /// Code6 text changed
        /// </summary>
        /// <param name="sender">Sender</param>
        /// <param name="e">Event</param>
        private void Code6_TextChanged(object sender, TextChangedEventArgs e)
        {
            var t = e.NewTextValue;
            if (t.Length > 1)
            {
                code6.Text = t.Substring(1, 1);
            }
            else if (t.Length == 0)
            {
                code5.Focus();
            }
        }

        #endregion
    }
}