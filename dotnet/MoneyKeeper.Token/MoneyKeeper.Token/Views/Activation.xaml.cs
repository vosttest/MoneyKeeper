using System;
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

        #endregion

        #region -- Events --

        /// <summary>
        /// Code 1 text changed
        /// </summary>
        /// <param name="sender">Sender</param>
        /// <param name="e">Event</param>
        private void Code1_TextChanged(object sender, TextChangedEventArgs e)
        {
            code2.Focus();
        }

        /// <summary>
        /// Code 2 text changed
        /// </summary>
        /// <param name="sender">Sender</param>
        /// <param name="e">Event</param>
        private void Code2_TextChanged(object sender, TextChangedEventArgs e)
        {
            code3.Focus();
        }

        /// <summary>
        /// Code 3 text changed
        /// </summary>
        /// <param name="sender">Sender</param>
        /// <param name="e">Event</param>
        private void Code3_TextChanged(object sender, TextChangedEventArgs e)
        {
            code4.Focus();
        }

        /// <summary>
        /// Code 4 text changed
        /// </summary>
        /// <param name="sender">Sender</param>
        /// <param name="e">Event</param>
        private void Code4_TextChanged(object sender, TextChangedEventArgs e)
        {
            code5.Focus();
        }

        /// <summary>
        /// Code 5 text changed
        /// </summary>
        /// <param name="sender">Sender</param>
        /// <param name="e">Event</param>
        private void Code5_TextChanged(object sender, TextChangedEventArgs e)
        {
            code6.Focus();
        }

        /// <summary>
        /// Close clicked
        /// </summary>
        /// <param name="sender">Sender</param>
        /// <param name="e">Event</param>
        private void Close_Clicked(object sender, EventArgs e)
        {
            var t = DependencyService.Get<IHelper>();
            t.Close();
        }

        #endregion
    }
}