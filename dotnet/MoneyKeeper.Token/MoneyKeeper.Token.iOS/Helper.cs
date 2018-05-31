using Foundation;
using MoneyKeeper.Token.iOS;
using System.Threading;
using Xamarin.Forms;

[assembly: Dependency(typeof(Helper))]
namespace MoneyKeeper.Token.iOS
{
    /// <summary>
    /// Helper
    /// </summary>
    public class Helper : Dependencies.IHelper
    {
        #region -- Methods --

        /// <summary>
        /// Initialize
        /// </summary>
        public Helper() { }

        /// <summary>
        /// Close application
        /// </summary>
        public void Close()
        {
            Thread.CurrentThread.Abort();
        }

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