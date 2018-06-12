using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Threading.Tasks;
using Xamarin.Forms;

namespace MoneyKeeper.Main.ViewModels
{
    using Common;
    using Dto;
    using Req;

    /// <summary>
    /// Voucher view model
    /// </summary>
    public class VoucherVM : BaseVM
    {
        #region -- Methods --

        /// <summary>
        /// Initialize
        /// </summary>
        public VoucherVM()
        {
            Title = "Voucher";

            /*Data = new List<VoucherDto>
            {
                new VoucherDto{ StartDate=new DateTime(2018,6,7), TotalExpense=10000,TotalIncome=156000},
                new VoucherDto{ StartDate=new DateTime(2018,6,8), TotalExpense=17000,TotalIncome=650000},
                new VoucherDto{ StartDate=new DateTime(2018,6,9), TotalExpense=19000,TotalIncome=567000}
            };*/

            Task.Run(async () => await LoadData());
        }

        /// <summary>
        /// /Load data
        /// </summary>
        /// <returns>Return the result</returns>
        private async Task LoadData()
        {
            if (IsBusy) { return; }
            IsBusy = true;

            try
            {
                var main = App.Current.MainPage;

                var m = new VoucherSearchReq
                {
                    Keyword = string.Empty,
                    Date = DateTime.Now
                };
                var rsp = await VoucherService.Search(m);

                if (rsp.Status == Const.HTTP.STATUS_SUCCESS)
                {
                    Data = rsp.Result.Data;
                }
                else
                {
                    await main.DisplayAlert("Error", rsp.Message, "OK");
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
        /// Voucher list
        /// </summary>
        public List<VoucherDto> Data
        {
            get { return _data; }
            set { SetProperty(ref _data, value); }
        }

        /// <summary>
        /// Sign in command
        /// </summary>
        public Command SignInCmd { get; set; }

        #endregion

        #region -- Fields --

        /// <summary>
        /// Voucher list
        /// </summary>
        private List<VoucherDto> _data;

        #endregion
    }
}