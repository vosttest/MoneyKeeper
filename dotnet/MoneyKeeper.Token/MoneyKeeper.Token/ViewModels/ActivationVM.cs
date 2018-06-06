using Newtonsoft.Json;
using System;
using System.Diagnostics;
using System.Threading.Tasks;
using Xamarin.Forms;

namespace MoneyKeeper.Token.ViewModels
{
    using Common;
    using Dto;
    using Views;

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
                var code = Code1 + Code2 + Code3 + Code4 + Code5 + Code6;
                var rsp = await UserService.VerifyActiveCode(code);

                if (rsp.Status == Const.HTTP.STATUS_SUCCESS)
                {
                    App.Jwt = rsp.Result.ToString();
                    Utils.SetVar(Const.Authentication.JWT, App.Jwt);

                    var t = Utils.DecodeJwt(App.Jwt, Const.Authentication.PAYLOAD_NAME);
                    var x = JsonConvert.DeserializeObject<PayloadDto>(t);
                    App.UUID = x.Uuid;

                    var ok = await main.DisplayAlert("Confirm", "Do you want to create password?", "Yes", "No");
                    if (ok)
                    {
                        await main.Navigation.PushModalAsync(new Password());
                    }
                    else
                    {
                        await main.Navigation.PushModalAsync(new MainPage());
                    }
                }
                else
                {
                    await main.DisplayAlert("Error", rsp.Message, "OK");
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
        /// Activation code 1
        /// </summary>
        public string Code1 { get; set; }

        /// <summary>
        /// Activation code 2
        /// </summary>
        public string Code2 { get; set; }

        /// <summary>
        /// Activation code 3
        /// </summary>
        public string Code3 { get; set; }

        /// <summary>
        /// Activation code 4
        /// </summary>
        public string Code4 { get; set; }

        /// <summary>
        /// Activation code 5
        /// </summary>
        public string Code5 { get; set; }

        /// <summary>
        /// Activation code 6
        /// </summary>
        public string Code6 { get; set; }

        /// <summary>
        /// Verify command
        /// </summary>
        public Command VerifyCmd { get; set; }

        #endregion
    }
}