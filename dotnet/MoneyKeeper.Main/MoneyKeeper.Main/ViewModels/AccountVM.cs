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

            AddCmd = new Command(async () => await ExeAddCmd());
        }

        /// <summary>
        /// Execute add command
        /// </summary>
        /// <returns>Return the result</returns>
        private async Task ExeAddCmd()
        {
            var main = App.Current.MainPage;

            var v = new Accounts() { Content = new AccountAdd() };
            await main.Navigation.PushAsync(v);
        }

        #endregion

        #region -- Properties --

        /// <summary>
        /// Add command
        /// </summary>
        public Command AddCmd { get; set; }

        #endregion
    }
}