using System;
using System.Diagnostics;
using System.Threading.Tasks;
using Xamarin.Forms;

namespace MoneyKeeper.Token.ViewModels
{
    /// <summary>
    /// User view model
    /// </summary>
    public class UserVM : BaseVM
    {
        #region -- Methods --

        /// <summary>
        /// Initialize
        /// </summary>
        public UserVM()
        {
            Title = "User";

            VerifyCommand = new Command(async () => await ExecuteVerifyCommand());
        }

        /// <summary>
        /// Execute verify mail command
        /// </summary>
        /// <returns>Return the result</returns>
        private async Task ExecuteVerifyCommand()
        {
            if (IsBusy) { return; }
            IsBusy = true;

            try
            {
                var main = App.Current.MainPage;
                var res = await UserService.VerifyActivation(Email);

                if (res)
                {
                    await main.DisplayAlert("Information", "Verify email success", "OK");
                }
                else
                {
                    await main.DisplayAlert("Error", "Verify email fail", "OK");
                }
            }
            catch (Exception ex)
            {
                Debug.WriteLine(ex);
            }
            finally { IsBusy = false; }
        }

        #endregion

        #region -- Properties --

        /// <summary>
        /// Email
        /// </summary>
        public string Email { get; set; }

        /// <summary>
        /// Verify mail command
        /// </summary>
        public Command VerifyCommand { get; set; }

        #endregion
    }
}