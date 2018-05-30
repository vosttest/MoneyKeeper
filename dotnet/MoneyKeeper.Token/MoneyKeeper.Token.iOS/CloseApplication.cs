using MoneyKeeper.Token.iOS;
using System.Threading;
using Xamarin.Forms;

[assembly: Dependency(typeof(CloseApplication))]
namespace MoneyKeeper.Token.iOS
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
            Thread.CurrentThread.Abort();
        }

        #endregion
    }
}