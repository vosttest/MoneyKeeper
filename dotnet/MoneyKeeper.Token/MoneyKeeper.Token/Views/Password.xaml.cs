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

        #endregion

        #region -- Events --

        /// <summary>
        /// Password text changed
        /// </summary>
        /// <param name="sender">Sender</param>
        /// <param name="e">Event</param>
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

                    main = new MainPage();
                }
                else if (App.Password == password)
                {
                    main = new MainPage();
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