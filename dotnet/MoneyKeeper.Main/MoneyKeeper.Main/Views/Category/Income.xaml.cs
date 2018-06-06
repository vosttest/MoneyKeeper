using MoneyKeeper.Main.Models;
using MoneyKeeper.Main.ViewModels;

using Xamarin.Forms;
using Xamarin.Forms.Xaml;

namespace MoneyKeeper.Main.Views.Category
{
    [XamlCompilation(XamlCompilationOptions.Compile)]
    public partial class Income : ContentPage
    {
        public Income()
        {
            InitializeComponent();
        }

        private void ListView_ItemTapped(object sender, ItemTappedEventArgs e)
        {
            var vm = BindingContext as ExpenseVM;

            var categories = e.Item as CategoryModel;

            //vm.HideOrShowCategory(categories);
        }
    }
}