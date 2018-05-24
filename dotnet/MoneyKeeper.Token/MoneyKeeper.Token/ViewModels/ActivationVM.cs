using System;
using System.Diagnostics;
using System.Threading.Tasks;
using Xamarin.Forms;

namespace MoneyKeeper.Token.ViewModels
{
    /// <summary>
    /// Activation view model
    /// </summary>
    public class ActivationVM : BaseVM
    {
        #region -- Methods --

        /// <summary>
        /// Initialize
        /// </summary>
        public ActivationVM()
        {
            Title = "Activation";

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
                var res = await UserService.VerifyActivation(Code);

                if (res.Status == "success")
                {
                    Application.Current.Properties["jwt"] = res.Result;
                    await Application.Current.SavePropertiesAsync();
                }
                else
                {
                    await main.DisplayAlert("Error", res.Message, "OK");
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
        /// Activation code
        /// </summary>
        public string Code { get; set; }

        /// <summary>
        /// Verify mail command
        /// </summary>
        public Command VerifyCommand { get; set; }

        #endregion
    }
}