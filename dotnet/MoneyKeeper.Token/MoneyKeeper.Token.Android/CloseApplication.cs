using Android.App;
using MoneyKeeper.Token.Droid;
using Xamarin.Forms;

[assembly: Dependency(typeof(CloseApplication))]
namespace MoneyKeeper.Token.Droid
{
    /// <summary>
    /// Close application
    /// </summary>
    public class CloseApplication : Dependencies.ICloseApplication
    {
        #region -- Methods --

        /// <summary>
        /// Initialize
        /// </summary>
        public void Close()
        {
            var activity = (Activity)Forms.Context;
            activity.FinishAffinity();
        }

        #endregion
    }
}