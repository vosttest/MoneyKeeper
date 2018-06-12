using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;
using System.Threading.Tasks;

namespace MoneyKeeper.Main.ViewModels
{
    using Common;
    using Dto;

    /// <summary>
    /// Income view model
    /// </summary>
    public class IncomeVM : BaseVM
    {
        #region -- Methods --

        /// <summary>
        /// Initialize
        /// </summary>
        public IncomeVM()
        {
            Title = "Income";

            Task.Run(async () => await LoadData());
        }

        /// <summary>
        /// Load data
        /// </summary>
        /// <returns>Return the result</returns>
        private async Task LoadData()
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

                    Data = l;
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

        #endregion

        #region -- Properties --

        /// <summary>
        /// Data
        /// </summary>
        public List<CategoryDto> Data
        {
            get { return _data; }
            set { SetProperty(ref _data, value); }
        }

        #endregion

        #region -- Fields --

        /// <summary>
        /// Data
        /// </summary>
        private List<CategoryDto> _data;

        #endregion
    }
}