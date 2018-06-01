using System;
using System.Collections.Generic;
using System.Diagnostics;
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

            _accounts = new List<AccountDto>();

            Task.Run(async () => await ExeLoadCmd());
        }

        /// <summary>
        /// Execute load command
        /// </summary>
        /// <returns></returns>
        private async Task ExeLoadCmd()
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
                var res = await AccountService.Search(m);

                if (res.Status == Const.HTTP.STATUS_SUCCESS)
                {
                    Accounts = res.Result.Data;
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
        /// List account
        /// </summary>
        public List<AccountDto> Accounts
        {
            get { return _accounts; }
            set { SetProperty(ref _accounts, value); }
        }

        #endregion

        #region -- Fields --

        /// <summary>
        /// List account
        /// </summary>
        private List<AccountDto> _accounts;

        #endregion
    }
}