using Xamarin.Forms;

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

        /// <summary>
        /// Initialize
        /// </summary>
        public App()
        {
            InitializeComponent();
            try
            {
                var jwt = Application.Current.Properties["jwt"].ToString();
                if (string.IsNullOrEmpty(jwt))
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
    }
}