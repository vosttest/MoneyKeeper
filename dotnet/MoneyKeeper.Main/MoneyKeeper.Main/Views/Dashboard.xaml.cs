﻿using Xamarin.Forms;
using Xamarin.Forms.Xaml;

namespace MoneyKeeper.Main.Views
{
    /// <summary>
    /// Dashboard
    /// </summary>
    [XamlCompilation(XamlCompilationOptions.Compile)]
    public partial class Dashboard : ContentPage
    {
        #region -- Methods --

        /// <summary>
        /// Initialize
        /// </summary>
        public Dashboard()
        {
            InitializeComponent();
        }

        private void LstAccount_ItemSelected(object sender, SelectedItemChangedEventArgs e)
        {
            //TODO
        }

        #endregion
    }
}