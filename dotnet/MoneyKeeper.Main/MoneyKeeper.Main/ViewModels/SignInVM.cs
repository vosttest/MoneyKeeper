using System;
using System.Diagnostics;
using System.Threading.Tasks;
using Xamarin.Forms;

namespace MoneyKeeper.Main.ViewModels
{
    using Req;

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
            Password = "123456";
#endif

            SignInCmd = new Command(async () => await ExeSignInCmd());
        }

        /// <summary>
        /// Execute sign in command
        /// </summary>
        /// <returns></returns>
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
                var res = await UserService.SignIn(m);

                if (res.Status == "success")
                {
                    if (res.Result.Authen)
                    {
                        //TODO
                        await main.DisplayAlert("Information", "Please do not use token...", "OK");
                    }
                    else
                    {
                        App.Jwt = res.Result.Key;
                        Application.Current.Properties["jwt"] = App.Jwt;
                        await Application.Current.SavePropertiesAsync();
                        await main.Navigation.PushModalAsync(new MainPage());
                    }
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