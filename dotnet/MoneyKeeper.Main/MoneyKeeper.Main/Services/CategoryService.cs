using Newtonsoft.Json;
using System;
using System.Diagnostics;
using System.Threading.Tasks;

namespace MoneyKeeper.Main.Services
{
    using Models;
    using Rsp;

    /// <summary>
    /// Category service
    /// </summary>
    public class CategoryService : BaseService<CategoryModel>
    {
        #region -- Methods --

        /// <summary>
        /// Initialize
        /// </summary>
        public CategoryService() { }

        public async Task<CategoryRsp> SearchExpense()
        {
            CategoryRsp res = null;

            try
            {
                //var data = CreateData(req);
                var client = CreateClient();
                var rsp = await client.GetAsync(Host + "expense/search");

                if (rsp.IsSuccessStatusCode)
                {
                    var t = await rsp.Content.ReadAsStringAsync();
                    res = JsonConvert.DeserializeObject<CategoryRsp>(t);
                }
            }
            catch (Exception ex)
            {
                Debug.WriteLine(ex);
            }

            return res;
        }
        public async Task<CategoryRsp> SearchIncome()
        {
            CategoryRsp res = null;

            try
            {
                //var data = CreateData(req);
                var client = CreateClient();
                var rsp = await client.GetAsync(Host + "income/search");

                if (rsp.IsSuccessStatusCode)
                {
                    var t = await rsp.Content.ReadAsStringAsync();
                    res = JsonConvert.DeserializeObject<CategoryRsp>(t);
                }
            }
            catch (Exception ex)
            {
                Debug.WriteLine(ex);
            }

            return res;
        }

        #endregion
    }
}