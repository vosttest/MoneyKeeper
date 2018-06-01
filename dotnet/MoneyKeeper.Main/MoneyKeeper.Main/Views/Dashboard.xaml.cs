using Xamarin.Forms;
using Xamarin.Forms.Xaml;

namespace MoneyKeeper.Main.Views
{
    using Dto;
    using Shared;

    /// <summary>
    /// Dashboard
    /// </summary>
    [XamlCompilation(XamlCompilationOptions.Compile)]
    public partial class Dashboard : ContentPage
    {
        #region -- Methods --

        /// <summary>
        /// Initialize
        /// </summary>
        public Dashboard()
        {
            InitializeComponent();
        }

        private void LstAccount_ItemSelected(object sender, SelectedItemChangedEventArgs e)
        {
            (sender as ListView).SelectedItem = null;

            if (e.SelectedItem != null)
            {
                var m = e.SelectedItem as AccountDto;
                var x = new AccountPopup();
                var v = new Popup { Content = x };
                Navigation.PushModalAsync(v);
            }
        }

        #endregion
    }
}