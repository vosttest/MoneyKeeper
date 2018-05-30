using Xamarin.Forms;
using Xamarin.Forms.Xaml;

namespace MoneyKeeper.Token.Views
{
    using Dependencies;

    /// <summary>
    /// About
    /// </summary>
    [XamlCompilation(XamlCompilationOptions.Compile)]
    public partial class About : ContentPage
    {
        #region -- Methods --

        /// <summary>
        /// Initialize
        /// </summary>
        public About()
        {
            InitializeComponent();

            var t = DependencyService.Get<IVersionAndBuild>();
            lblVersionNumber.Text = string.Format("Version {0} Build {1}", t.VersionNumber, t.BuildNumber);
        }

        #endregion
    }
}