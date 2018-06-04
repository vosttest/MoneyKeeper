
using Xamarin.Forms;
using Xamarin.Forms.Xaml;

namespace MoneyKeeper.Main.Views.Shared
{
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
        /// TokenPopup
        /// </summary>
        /// <param name="UserName"></param>
        /// <param name="Password"></param>
        /// <param name="ClientKey"></param>
        public TokenPopup(string UserName, string Password, string ClientKey)
        {
            InitializeComponent();
            userName.Text = UserName;
            password.Text = Password;
            clienKey.Text = ClientKey;
            code1.Focus();
        }
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