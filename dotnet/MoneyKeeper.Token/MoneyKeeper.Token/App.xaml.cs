using Newtonsoft.Json;
using System;
using Xamarin.Forms;
using Xamarin.Forms.Xaml;

[assembly: XamlCompilation(XamlCompilationOptions.Compile)]
namespace MoneyKeeper.Token
{
    using Common;
    using Dto;
    using Views;

    /// <summary>
    /// App
    /// </summary>
    public partial class App : Application
    {
        #region -- Overrides --

        protected override void OnStart()
        {
            // Handle when your app starts
        }

        protected override void OnSleep()
        {
            // Handle when your app sleeps
        }

        protected override void OnResume()
        {
            if (string.IsNullOrEmpty(Password))
            {
                MainPage = new MainPage();
            }
            else
            {
                MainPage = new Password();
            }
        }

        #endregion

        #region -- Methods --

        public App()
        {
            InitializeComponent();

            Jwt = Utils.GetVar(Const.Authentication.JWT);
            if (string.IsNullOrEmpty(Jwt))
            {
                MainPage = new Activation();
            }
            else
            {
                var t = Utils.DecodeJwt(Jwt, Const.Authentication.PAYLOAD_NAME);
                var x = JsonConvert.DeserializeObject<PayloadDto>(t);
                UUID = x.Uuid;

                Password = Utils.GetVar(Const.Authentication.PIN);
                if (string.IsNullOrEmpty(Password))
                {
                    MainPage = new MainPage();
                }
                else
                {
                    MainPage = new Password();
                }
            }
        }

        #endregion

        #region -- Properties --

        /// <summary>
        /// JSON web token
        /// </summary>
        public static string Jwt { get; set; }

        /// <summary>
        /// Password
        /// </summary>
        public static string Password { get; set; }

        /// <summary>
        /// Universally unique identifier
        /// </summary>
        public static Guid UUID { get; set; }

        #endregion
    }
}