using System;
using System.Diagnostics;
using System.Threading.Tasks;
using Xamarin.Forms;

namespace MoneyKeeper.Main.ViewModels
{
    using Common;
    using Req;
    using Views;
    using Views.Shared;

    /// <summary>
    /// Sign in view model
    /// </summary>
    public class SignInVM : BaseVM
    {
        #region -- Methods --

        /// <summary>
        /// Initialize
        /// </summary>
        public SignInVM()
        {
            Title = "Sign In";

#if DEBUG
            UserName = "vost.test";
            Password = "Qwerty123!";
#endif

            SignInCmd = new Command(async () => await ExeSignInCmd());
        }

        /// <summary>
        /// Execute sign in command
        /// </summary>
        /// <returns>Return the result</returns>
        private async Task ExeSignInCmd()
        {
            if (IsBusy) { return; }
            IsBusy = true;

            try
            {
                var main = App.Current.MainPage;
                var m = new SignInReq()
                {
                    UserName = UserName,
                    Password = Password,
                    ClientKey = string.Empty,
                    SendToken = true,
                    Token = string.Empty
                };
                var rsp = await UserService.SignIn(m);

                if (rsp.Status == Const.HTTP.STATUS_SUCCESS)
                {
                    if (rsp.Result.Authen)
                    {
                        var key = rsp.Result.Key;
                        var t = new Popup()
                        {
                            Content = new TokenPopup(UserName, Password, key)
                        };
                        await main.Navigation.PushModalAsync(t);
                    }
                    else
                    {
                        App.Jwt = rsp.Result.Key;
                        Utils.SetVar(Const.Authentication.JWT, App.Jwt);

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
        /// User name
        /// </summary>
        public string UserName { get; set; }

        /// <summary>
        /// Password
        /// </summary>
        public string Password { get; set; }

        /// <summary>
        /// Sign in command
        /// </summary>
        public Command SignInCmd { get; set; }

        #endregion
    }
}