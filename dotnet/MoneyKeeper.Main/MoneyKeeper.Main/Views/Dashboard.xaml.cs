using Microcharts;
using SkiaSharp;
using System.Collections.Generic;
using Xamarin.Forms;
using Xamarin.Forms.Xaml;
using Entry = Microcharts.Entry;

namespace MoneyKeeper.Main.Views
{
    using Dto;
    using Shared;

    /// <summary>
    /// Dashboard
    /// </summary>
    [XamlCompilation(XamlCompilationOptions.Compile)]
    public partial class Dashboard : ContentPage
    {
        #region -- Properties --

        List<Entry> entries = new List<Entry>
        {
            new Entry(212)
            {
                Label = "UWP",
                ValueLabel="212",
                Color = SKColor.Parse("#2c3e50")
            },
            new Entry(248)
            {
                Label = "Android",
                ValueLabel = "248",
                Color = SKColor.Parse("#77d065")
            },
            new Entry(128)
            {
                Label = "iOS",
                ValueLabel = "128",
                Color = SKColor.Parse("#b455b6")
            },
            new Entry(514)
            {
                Label = "Shared",
                ValueLabel = "514",
                Color = SKColor.Parse("#3498db")
            }
        };

        #endregion

        #region -- Methods --

        /// <summary>
        /// Initialize
        /// </summary>
        public Dashboard()
        {
            InitializeComponent();
            ChartAccount.Chart = new DonutChart() { Entries = entries, LabelTextSize = 35 };
        }

        private void LstAccount_ItemSelected(object sender, SelectedItemChangedEventArgs e)
        {
            (sender as ListView).SelectedItem = null;

            if (e.SelectedItem != null)
            {
                var m = e.SelectedItem as AccountDto;
                var x = new AccountPopup();
                var v = new Popup { Content = x };
                Navigation.PushModalAsync(v);
            }
        }

        #endregion
    }
}