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

            _accounts = new List<AccountDto>();
            _list = new List<AccountList>();

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

                    var t1 = Accounts.Select(p => p.Type).Distinct().ToList();
                    foreach (var i in t1)
                    {
                        var t2 = Accounts.Where(p => p.Type == i).ToList();

                        var l = new AccountList { Heading = i };
                        l.Accounts.AddRange(t2);
                        List.Add(l);
                    }

                    #region -- Test data --
                    var l1 = new PersonList {
                        new Person() { FirstName = "Sally", LastName = "Sampson" },
                        new Person() { FirstName = "Taylor", LastName = "Swift" },
                        new Person() { FirstName = "John", LastName = "Smith" }
                    };
                    l1.Heading = "S";

                    var l2 = new PersonList {
                        new Person() { FirstName = "Jane", LastName = "Doe" }
                    };
                    l2.Heading = "D";

                    var l3 = new PersonList {
                        new Person() { FirstName = "Billy", LastName = "Joel" }
                    };
                    l3.Heading = "J";

                    ListOfPeople = new List<PersonList> { l1, l2, l3 };
                    #endregion
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

        /// <summary>
        /// Account list
        /// </summary>
        public List<AccountList> List
        {
            get { return _list; }
            set { SetProperty(ref _list, value); }
        }

        /// <summary>
        /// List of people
        /// </summary>
        public List<PersonList> ListOfPeople
        {
            get { return _listOfPeople; }
            set
            {
                _listOfPeople = value;
                base.OnPropertyChanged();
            }
        }

        #endregion

        #region -- Fields --

        /// <summary>
        /// List account
        /// </summary>
        private List<AccountDto> _accounts;

        /// <summary>
        /// Account list
        /// </summary>
        private List<AccountList> _list;

        /// <summary>
        /// Person list
        /// </summary>
        private List<PersonList> _listOfPeople;

        #endregion
    }
}