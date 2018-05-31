using Android.Content.PM;
using Java.Lang;
using MoneyKeeper.Main.Droid;
using Xamarin.Forms;

[assembly: Dependency(typeof(Helper))]
namespace MoneyKeeper.Main.Droid
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
        public Helper()
        {
            var t = Android.App.Application.Context;
            _info = t.PackageManager.GetPackageInfo(t.PackageName, 0);
        }

        /// <summary>
        /// Close application
        /// </summary>
        public void Close()
        {
            JavaSystem.Exit(0);
        }

        #endregion

        #region -- Properties --

        #endregion

        /// <summary>
        /// Get version number
        /// </summary>
        public string VersionNumber
        {
            get
            {
                return _info.VersionName;
            }
        }

        /// <summary>
        /// Get build number
        /// </summary>
        public string BuildNumber
        {
            get
            {
                return _info.VersionCode.ToString();
            }
        }

        #region -- Fields --

        /// <summary>
        /// Package information
        /// </summary>
        private PackageInfo _info;

        #endregion
    }
}