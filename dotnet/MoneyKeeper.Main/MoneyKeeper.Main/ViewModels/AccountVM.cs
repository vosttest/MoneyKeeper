using System.Threading.Tasks;
using Xamarin.Forms;

namespace MoneyKeeper.Main.ViewModels
{
    using Views;
    using Views.Account;

    /// <summary>
    /// Account view model
    /// </summary>
    public class AccountVM : BaseVM
    {
        #region -- Methods --

        /// <summary>
        /// Initialize
        /// </summary>
        public AccountVM()
        {
            Title = "Account";

            AccountAddCmd = new Command(async () => await ExeAccountAddCmd());
        }

        /// <summary>
        /// Account Add in command
        /// </summary>
        /// <returns></returns>
        private async Task ExeAccountAddCmd()
        {
            var main = App.Current.MainPage;
            await main.Navigation.PushAsync(new Accounts() { Content = new AccountAdd() });
        }

        #endregion

        #region -- Properties --

        /// <summary>
        /// Account Add command
        /// </summary>
        public Command AccountAddCmd { get; set; }

        #endregion
    }
}