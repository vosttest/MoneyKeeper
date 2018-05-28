using Xamarin.Forms;
using Xamarin.Forms.Xaml;

namespace MoneyKeeper.Token.Views
{
    /// <summary>
    /// Activation
    /// </summary>
    [XamlCompilation(XamlCompilationOptions.Compile)]
    public partial class Activation : ContentPage
    {
        #region -- Methods --

        /// <summary>
        /// Initialize
        /// </summary>
        public Activation()
        {
            InitializeComponent();
            code1.Focus();
        }

        private void code1_TextChanged(object sender, TextChangedEventArgs e)
        {
            code2.Focus();
        }

        private void code2_TextChanged(object sender, TextChangedEventArgs e)
        {
            code3.Focus();
        }

        private void code3_TextChanged(object sender, TextChangedEventArgs e)
        {
            code4.Focus();
        }

        private void code4_TextChanged(object sender, TextChangedEventArgs e)
        {
            code5.Focus();
        }

        #endregion
    }
}