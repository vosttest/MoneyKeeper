namespace MoneyKeeper.Token.Dependencies
{
    /// <summary>
    /// Interface helper
    /// </summary>
    public interface IHelper
    {
        #region -- Methods --

        /// <summary>
        /// Close application
        /// </summary>
        void Close();

        #endregion

        #region -- Properties --

        /// <summary>
        /// Get version number
        /// </summary>
        string VersionNumber { get; }

        /// <summary>
        /// Get build number
        /// </summary>
        string BuildNumber { get; }

        #endregion
    }
}