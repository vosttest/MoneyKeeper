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

            VerifyCmd = new Command(async () => await ExeVerifyCmd());
        }

        /// <summary>
        /// Execute verify command
        /// </summary>
        /// <returns>Return the result</returns>
        private async Task ExeVerifyCmd()
        {
            if (IsBusy) { return; }
            IsBusy = true;

            try
            {
                var main = App.Current.MainPage;
                var res = await UserService.VerifyActiveCode(Code);

                if (res.Status == "success")
                {
                    App.Jwt = res.Result.ToString();
                    Application.Current.Properties["jwt"] = App.Jwt;
                    await Application.Current.SavePropertiesAsync();
                    await main.Navigation.PushModalAsync(new MainPage());
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
        /// Verify command
        /// </summary>
        public Command VerifyCmd { get; set; }

        #endregion
    }
}