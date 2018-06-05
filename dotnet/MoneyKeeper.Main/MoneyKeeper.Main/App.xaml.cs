using Xamarin.Forms;
using Xamarin.Forms.Xaml;

[assembly: XamlCompilation(XamlCompilationOptions.Compile)]
namespace MoneyKeeper.Main
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

        /// <summary>
        /// Initialize
        /// </summary>
        public App()
        {
            InitializeComponent();

#if DEBUG
            Utils.SetVar(Const.Authentication.JWT, string.Empty);
#endif

            Jwt = Utils.GetVar(Const.Authentication.JWT);
            if (string.IsNullOrEmpty(Jwt))
            {
                MainPage = new SignIn();
            }
            else
            {
                MainPage = new MainPage();
            }
        }

        #endregion

        #region -- Properties --

        /// <summary>
        /// JSON web token
        /// </summary>
        public static string Jwt { get; set; }

        #endregion
    }
}