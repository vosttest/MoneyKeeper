using Xamarin.Forms;
using Xamarin.Forms.Xaml;

namespace MoneyKeeper.Token.Views
{
    using Common;

    /// <summary>
    /// Password
    /// </summary>
    [XamlCompilation(XamlCompilationOptions.Compile)]
    public partial class Password : ContentPage
    {
        #region -- Methods --

        /// <summary>
        /// Initialize
        /// </summary>
        public Password()
        {
            InitializeComponent();
        }

        private void Password_TextChanged(object sender, TextChangedEventArgs e)
        {
            var password = e.NewTextValue;

            if (password.Length == 4)
            {
                password = Utils.GenerateSHA256(password);
                var main = App.Current.MainPage;

                if (string.IsNullOrEmpty(App.Password))
                {
                    App.Password = password;
                    Utils.SetVar(Const.Authentication.PIN, App.Password);
                    App.Current.MainPage = new MainPage();
                }
                else if (App.Password == password)
                {
                    App.Current.MainPage = new MainPage();
                }
                else
                {
                    main.DisplayAlert("Error", "Wrong password...", "OK");
                }
            }
        }

        #endregion
    }
}