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
    /// Language view model
    /// </summary>
    public class LanguageVM : BaseVM
    {
        #region -- Methods --

        /// <summary>
        /// Initialize
        /// </summary>
        public LanguageVM()
        {
            Title = "Language";

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
                var m = new BaseReq { Keyword = Const.Common.CODE_LANGUAGE };

                var rsp = await CommonService.Search(m);

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
        /// Data
        /// </summary>
        public List<CommonDto> Data
        {
            get { return _data; }
            set { SetProperty(ref _data, value); }
        }

        #endregion

        #region -- Fields --

        /// <summary>
        /// Data
        /// </summary>
        private List<CommonDto> _data;

        #endregion
    }
}