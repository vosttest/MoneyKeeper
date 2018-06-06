using MoneyKeeper.Main.Common;
using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;
using System.Threading.Tasks;

namespace MoneyKeeper.Main.ViewModels
{
    using Dto;
    using Models;

    /// <summary>
    /// Category view model
    /// </summary>
    public class IncomeVM : BaseVM
    {
        #region -- Methods --

        /// <summary>
        /// Initialize
        /// </summary>
        public IncomeVM()
        {
            Title = "Category";

            _categories = new List<CategoryDto>();

            //ListOfCategory = new ObservableCollection<CategoryModel>
            //{
            //    new CategoryModel
            //    {
            //        Name = "Laptop",
            //        IsVisible = false
            //    },
            //    new CategoryModel
            //    {
            //        Name = "Surface Book"
            //    },
            //    new CategoryModel
            //    {
            //        Name = "Carbon"
            //    }
            //};

            Task.Run(async () => await IncLoadCmd());
        }

        private async Task IncLoadCmd()
        {
            if (IsBusy) { return; }
            IsBusy = true;

            try
            {
                var main = App.Current.MainPage;

                var res = await CategoryService.SearchIncome();


                if (res.Status == Const.HTTP.STATUS_SUCCESS)
                {
                    var parent = res.Result.Parent;
                    var child = res.Result.Child;
                    var l = new List<CategoryDto>();

                    //var lstParent = parent.Select(p => p.Type).ToList();
                    //var lstChild = child.Select(p => p.Type).Distinct().ToList();
                    foreach (var i in parent)
                    {
                        var t2 = child.Where(p => p.ParentId == i.Id).ToList();
                        var o = new CategoryDto
                        {
                            Id = i.Id,
                            Code = i.Code,
                            Text = i.Text,
                            ParentId = i.ParentId,
                            Description = i.Description,
                            Count = i.Count,
                            Icon = i.Icon,
                            Child = t2
                        };

                        l.Add(o);
                    }

                    IncomeCategories = l;
                }
                else
                {
                    await main.DisplayAlert("Error", res.Message, "OK");
                }
            }
            catch (Exception ex)
            {
                Debug.WriteLine(ex);
            }
            finally { IsBusy = false; }
        }
        //public void HideOrShowCategory(CategoryModel categories)
        //{
        //    if (_oldCategory == categories)
        //    {
        //        categories.IsVisible = !categories.IsVisible;
        //        UpdateCategories(categories);
        //    }
        //    else
        //    {
        //        if (_oldCategory != null)
        //        {
        //            categories.IsVisible = false;
        //            UpdateCategories(categories);
        //        }

        //        categories.IsVisible = true;
        //        UpdateCategories(categories);
        //    }

        //    _oldCategory = categories;
        //}

        //private void UpdateCategories(CategoryModel categories)
        //{
        //    var index = ListOfCategory.IndexOf(categories);
        //    ListOfCategory.Remove(categories);
        //    ListOfCategory.Insert(index, categories);
        //}

        #endregion

        #region -- Properties --

        /// <summary>
        /// List of category
        /// </summary>
        //public List<CategoryModel> ListOfCategory { get; set; }
        public List<CategoryDto> IncomeCategories
        {
            get { return _categories; }
            set { SetProperty(ref _categories, value); }
        }

        /// <summary>
        /// Old category
        /// </summary>
        private CategoryModel _oldCategory { get; set; }

        #endregion

        #region -- Fields --

        /// <summary>
        /// Account list
        /// </summary>
        private List<CategoryDto> _categories;

        #endregion
    }
}