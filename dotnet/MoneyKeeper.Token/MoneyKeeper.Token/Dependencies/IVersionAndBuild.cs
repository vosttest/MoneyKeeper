namespace MoneyKeeper.Token.Dependencies
{
    /// <summary>
    /// interface version and build
    /// </summary>
    public interface IVersionAndBuild
    {
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