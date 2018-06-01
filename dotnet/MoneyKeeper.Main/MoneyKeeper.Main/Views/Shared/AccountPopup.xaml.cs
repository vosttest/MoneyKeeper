using Xamarin.Forms;
using Xamarin.Forms.Xaml;

namespace MoneyKeeper.Main.Views.Shared
{
    /// <summary>
    /// Account popup
    /// </summary>
    [XamlCompilation(XamlCompilationOptions.Compile)]
    public partial class AccountPopup : ContentView
    {
        #region -- Methods --

        /// <summary>
        /// Initialize
        /// </summary>
        public AccountPopup()
        {
            InitializeComponent();
        }

        #endregion
    }
}