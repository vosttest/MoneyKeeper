using Xamarin.Forms;
using Xamarin.Forms.Xaml;

[assembly: XamlCompilation(XamlCompilationOptions.Compile)]
namespace MoneyKeeper.Token
{
    using Common;
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
            // Handle when your app resumes
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
                Password = Utils.GetVar(Const.Authentication.PIN);
                if (string.IsNullOrEmpty(Password))
                {
                    MainPage = new MainPage();
                }
                else
                {
                    MainPage = new Login();
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

        #endregion
    }
}