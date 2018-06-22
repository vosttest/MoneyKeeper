using Xamarin.Forms;
using Xamarin.Forms.Xaml;

namespace MoneyKeeper.Main.Views
{
    /// <summary>
    /// Accounts
    /// </summary>
    [XamlCompilation(XamlCompilationOptions.Compile)]
    public partial class Accounts : ContentPage
    {
        #region -- Methods --

        /// <summary>
        /// Initialize
        /// </summary>
        public Accounts()
        {
            InitializeComponent();
        }

        void OnSearchBarButtonPressed(object sender, System.EventArgs e)
        {
            //TODO
        }

        #endregion
    }
}