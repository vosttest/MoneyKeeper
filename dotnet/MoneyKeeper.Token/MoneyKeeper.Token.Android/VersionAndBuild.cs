using Android.Content.PM;
using MoneyKeeper.Token.Droid;
using Xamarin.Forms;

[assembly: Dependency(typeof(VersionAndBuild))]
namespace MoneyKeeper.Token.Droid
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
        public VersionAndBuild()
        {
            var t = Android.App.Application.Context;
            _info = t.PackageManager.GetPackageInfo(t.PackageName, 0);
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

        #endregion

        #region -- Fields --

        /// <summary>
        /// Package information
        /// </summary>
        private PackageInfo _info;

        #endregion
    }
}