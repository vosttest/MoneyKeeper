using Xamarin.Forms;
using Xamarin.Forms.Xaml;

namespace MoneyKeeper.Token.Views
{
    using Dependencies;

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

        private void Code1_TextChanged(object sender, TextChangedEventArgs e)
        {
            code2.Focus();
        }

        private void Code2_TextChanged(object sender, TextChangedEventArgs e)
        {
            code3.Focus();
        }

        private void Code3_TextChanged(object sender, TextChangedEventArgs e)
        {
            code4.Focus();
        }

        private void Code4_TextChanged(object sender, TextChangedEventArgs e)
        {
            code5.Focus();
        }

        private void Code5_TextChanged(object sender, TextChangedEventArgs e)
        {
            code6.Focus();
        }

        private void Close_Clicked(object sender, System.EventArgs e)
        {
            var t = DependencyService.Get<IHelper>();
            t.Close();
        }

        #endregion
    }
}