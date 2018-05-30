using Foundation;
using MoneyKeeper.Token.iOS;
using Xamarin.Forms;

[assembly: Dependency(typeof(VersionAndBuild))]
namespace MoneyKeeper.Token.iOS
{
    /// <summary>
    /// Version and build
    /// </summary>
    public class VersionAndBuild : Dependencies.IVersionAndBuild
    {
        #region -- Methods --

        /// <summary>
        /// Initialize
        /// </summary>
        public VersionAndBuild() { }

        #endregion

        #region -- Properties --

        /// <summary>
        /// Get version number
        /// </summary>
        public string VersionNumber
        {
            get
            {
                return NSBundle.MainBundle.ObjectForInfoDictionary("CFBundleShortVersionString").ToString();
            }
        }

        /// <summary>
        /// Get build number
        /// </summary>
        public string BuildNumber
        {
            get
            {
                return NSBundle.MainBundle.ObjectForInfoDictionary("CFBundleVersion").ToString();
            }
        }

        #endregion
    }
}