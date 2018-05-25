using Xamarin.Forms;
using Xamarin.Forms.Xaml;

[assembly: XamlCompilation(XamlCompilationOptions.Compile)]
namespace MoneyKeeper.Token
{
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

            try
            {
                Jwt = Application.Current.Properties["jwt"].ToString();
                if (string.IsNullOrEmpty(Jwt))
                {
                    MainPage = new Activation();
                }
                else
                {
                    MainPage = new MainPage();
                }
            }
            catch
            {
                MainPage = new Activation();
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