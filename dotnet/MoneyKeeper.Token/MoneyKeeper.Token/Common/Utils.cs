using System;

namespace MoneyKeeper.Token.Common
{
    /// <summary>
    /// Utils
    /// </summary>
    public static class Utils
    {
        /// <summary>
        /// Get token
        /// </summary>
        /// <param name="d"></param>
        /// <returns>Return the result</returns>
        public static string GetToken(DateTime d)
        {
            var t = d.ToString(Const.DateTime.FULL);

            //TODO
            return string.Empty;
        }
    }
}