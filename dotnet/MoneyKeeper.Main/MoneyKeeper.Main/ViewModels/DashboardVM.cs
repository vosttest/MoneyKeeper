using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;
using System.Threading.Tasks;

namespace MoneyKeeper.Main.ViewModels
{
    using Common;
    using Dto;
    using Req;

    /// <summary>
    /// Dashboard view model
    /// </summary>
    public class DashboardVM : BaseVM
    {
        #region -- Methods --

        /// <summary>
        /// Initialize
        /// </summary>
        public DashboardVM()
        {
            Title = "Dashboard";

            _accounts = new List<AccountList>();

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
                var m = new BaseReq()
                {
                    Keyword = string.Empty
                };
                var rsp = await AccountService.Search(m);

                if (rsp.Status == Const.HTTP.STATUS_SUCCESS)
                {
                    var x = rsp.Result.Data;
                    var l = new List<AccountList>();

                    var t1 = x.Select(p => p.Type).Distinct().ToList();
                    foreach (var i in t1)
                    {
                        var t2 = x.Where(p => p.Type == i).ToList();
                        var o = new AccountList { Heading = i };
                        o.Accounts.AddRange(t2);
                        l.Add(o);
                    }

                    Accounts = l;
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
        /// Account list
        /// </summary>
        public List<AccountList> Accounts
        {
            get { return _accounts; }
            set { SetProperty(ref _accounts, value); }
        }

        #endregion

        #region -- Fields --

        /// <summary>
        /// Account list
        /// </summary>
        private List<AccountList> _accounts;

        #endregion
    }
}