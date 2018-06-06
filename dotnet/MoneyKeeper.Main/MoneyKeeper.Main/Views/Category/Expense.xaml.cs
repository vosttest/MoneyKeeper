
using Xamarin.Forms;
using Xamarin.Forms.Xaml;

namespace MoneyKeeper.Main.Views.Category
{
    using Models;
    using ViewModels;

    [XamlCompilation(XamlCompilationOptions.Compile)]
    public partial class Expense : ContentPage
    {
        public Expense()
        {
            InitializeComponent();
        }

        private void ListView_ItemTapped(object sender, ItemTappedEventArgs e)
        {
            var vm = BindingContext as CategoryVM;

            var categories = e.Item as CategoryModel;

            //vm.HideOrShowCategory(categories);
        }
    }
}