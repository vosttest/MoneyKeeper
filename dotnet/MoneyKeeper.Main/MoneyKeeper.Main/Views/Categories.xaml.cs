using Xamarin.Forms;
using Xamarin.Forms.Xaml;

namespace MoneyKeeper.Main.Views
{
    /// <summary>
    /// Categories
    /// </summary>
    [XamlCompilation(XamlCompilationOptions.Compile)]
    public partial class Categories : TabbedPage
    {
        //public ObservableCollection<ParentListClass> ParentItems { get; set; }

        //public ObservableCollection<ChildListClass> ChildItems { get; set; }
        /// <summary>
        /// Initialize
        /// </summary>
        public Categories()
        {
            InitializeComponent();
            //ParentItems = new ObservableCollection<ParentListClass>();
            //ParentItems = new ObservableCollection<ParentListClass>();
            //ParentItems.Add(new ParentListClass
            //{
            //    ParentIcon = "dashboard.png",
            //    ParentTitle = "Lend"
            //});
            //ParentItems.Add(new ParentListClass
            //{
            //    ParentIcon = "account.png",
            //    ParentTitle = "Repayment"
            //});
            //ParentItems.Add(new ParentListClass
            //{
            //    ParentIcon = "report.png",
            //    ParentTitle = "Food And Dining"
            //});
            //ParentItems.Add(new ParentListClass
            //{
            //    ParentIcon = "dashboard.png",
            //    ParentTitle = "Lend"
            //});
            //ParentListClass.ItemsSource = ParentItems;
        }
    }
}