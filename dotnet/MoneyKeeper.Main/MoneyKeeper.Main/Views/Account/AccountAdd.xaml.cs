using Xamarin.Forms;
using Xamarin.Forms.Xaml;

namespace MoneyKeeper.Main.Views.Account
{
    /// <summary>
    /// Account add
    /// </summary>
    [XamlCompilation(XamlCompilationOptions.Compile)]
    public partial class AccountAdd : ContentView
    {
        #region -- Methods --

        /// <summary>
        /// Initialize
        /// </summary>
        public AccountAdd()
        {
            InitializeComponent();
        }

        void OnPickerSelectedIndexChanged(object sender, System.EventArgs e)
        {
            Picker picker = (Picker)sender;
        }

        #endregion
    }
}