using System;
using System.Threading.Tasks;
using Xamarin.Forms;

namespace MoneyKeeper.Main.ViewModels
{
    using Common;
    using Req;
    using Services;
    using System.Diagnostics;

    class TokenVM : BaseVM
    {
        #region -- Methods --

        /// <summary>
        /// Initialize
        /// </summary>
        public TokenVM()
        {
            Title = "Sign Ins";

            ProceedCmd = new Command(async () => await ExeTokenCmd());
            CancelCmd = new Command(async () => await ExeCancelCmd());
        }

        /// <summary>
        /// Execute sign in command
        /// </summary>
        /// <returns></returns>
        private async Task ExeTokenCmd()
        {
            if (IsBusy) { return; }
            IsBusy = true;

            try
            {
                var main = App.Current.MainPage;
                var t = Code1 + Code2 + Code3 + Code4 + Code5 + Code6;
                var m = new SignInReq()
                {
                    UserName = _userName,
                    Password = _password,
                    ClientKey = _clientKey,
                    SendToken = false,
                    Token = t
                };
                var res = await UserService.SignIn(m);

                if (res.Status == Const.HTTP.STATUS_SUCCESS)
                {
                    App.Jwt = res.Result.Key;
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
                Console.WriteLine(ex.Message);
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
        /// Password
        /// </summary>
        public string ClientKey
        {
            get { return _clientKey; }
            set { SetProperty(ref _clientKey, value); }
        }

        /// <summary>
        /// Code1
        /// </summary>
        public string Code1 { get; set; }

        /// <summary>
        /// Code2
        /// </summary>
        public string Code2 { get; set; }

        /// <summary>
        /// Code3
        /// </summary>
        public string Code3 { get; set; }

        /// <summary>
        /// Code4
        /// </summary>
        public string Code4 { get; set; }

        /// <summary>
        /// Code5
        /// </summary>
        public string Code5 { get; set; }

        /// <summary>
        /// Code6
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
        /// ClientKey
        /// </summary>
        private string _clientKey;

        /// <summary>
        /// UserName
        /// </summary>
        private string _userName;

        /// <summary>
        /// Password
        /// </summary>
        private string _password;

        #endregion
    }
}