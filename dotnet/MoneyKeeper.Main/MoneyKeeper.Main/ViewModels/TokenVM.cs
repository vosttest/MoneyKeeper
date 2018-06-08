using System;
using System.Diagnostics;
using System.Threading.Tasks;
using Xamarin.Forms;

namespace MoneyKeeper.Main.ViewModels
{
    using Common;
    using Req;
    using Services;

    /// <summary>
    /// Token view model
    /// </summary>
    public class TokenVM : BaseVM
    {
        #region -- Methods --

        /// <summary>
        /// Initialize
        /// </summary>
        public TokenVM()
        {
            Title = "Token";

            ProceedCmd = new Command(async () => await ExeProceedCmd());
            CancelCmd = new Command(async () => await ExeCancelCmd());
        }

        /// <summary>
        /// Execute proceed command
        /// </summary>
        /// <returns>Return the result</returns>
        private async Task ExeProceedCmd()
        {
            if (IsBusy) { return; }
            IsBusy = true;

            try
            {
                var main = App.Current.MainPage;
                var t = Code1 + Code2 + Code3 + Code4 + Code5 + Code6;
                var m = new SignInReq()
                {
                    UserName = UserName,
                    Password = Password,
                    ClientKey = ClientKey,
                    SendToken = false,
                    Token = t
                };
                var rsp = await UserService.SignIn(m);

                if (rsp.Status == Const.HTTP.STATUS_SUCCESS)
                {
                    App.Jwt = rsp.Result.Key;
                    Utils.SetVar(Const.Authentication.JWT, App.Jwt);

                    await main.Navigation.PushModalAsync(new MainPage());
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

        /// <summary>
        /// Execute cancel command
        /// </summary>
        /// <returns>Return the result</returns>
        private async Task ExeCancelCmd()
        {
            if (IsBusy) { return; }
            IsBusy = true;

            try
            {
                var main = App.Current.MainPage;
                await main.Navigation.PopModalAsync();
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
        public string UserName
        {
            get { return _userName; }
            set { SetProperty(ref _userName, value); }
        }

        /// <summary>
        /// Password
        /// </summary>
        public string Password
        {
            get { return _password; }
            set { SetProperty(ref _password, value); }
        }

        /// <summary>
        /// Client key
        /// </summary>
        public string ClientKey
        {
            get { return _clientKey; }
            set { SetProperty(ref _clientKey, value); }
        }

        /// <summary>
        /// Code 1
        /// </summary>
        public string Code1 { get; set; }

        /// <summary>
        /// Code 2
        /// </summary>
        public string Code2 { get; set; }

        /// <summary>
        /// Code 3
        /// </summary>
        public string Code3 { get; set; }

        /// <summary>
        /// Code 4
        /// </summary>
        public string Code4 { get; set; }

        /// <summary>
        /// Code 5
        /// </summary>
        public string Code5 { get; set; }

        /// <summary>
        /// Code 6
        /// </summary>
        public string Code6 { get; set; }

        /// <summary>
        /// Proceed command
        /// </summary>
        public Command ProceedCmd { get; set; }

        /// <summary>
        /// Cancel command
        /// </summary>
        public Command CancelCmd { get; set; }

        #endregion

        #region -- Fields --

        /// <summary>
        /// User name
        /// </summary>
        private string _userName;

        /// <summary>
        /// Password
        /// </summary>
        private string _password;

        /// <summary>
        /// Client key
        /// </summary>
        private string _clientKey;

        #endregion
    }
}