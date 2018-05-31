using System;

namespace MoneyKeeper.Main.Models
{
    /// <summary>
    /// Menu model
    /// </summary>
    public class MenuModel
    {
        #region -- Properties --

        /// <summary>
        /// Title
        /// </summary>
        public string Title { get; set; }

        /// <summary>
        /// Icon
        /// </summary>
        public string Icon { get; set; }

        /// <summary>
        /// Target
        /// </summary>
        public Type Target { get; set; }

        #endregion
    }
}