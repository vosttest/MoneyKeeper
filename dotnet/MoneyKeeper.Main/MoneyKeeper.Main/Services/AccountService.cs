using Newtonsoft.Json;
using System;
using System.Diagnostics;
using System.Net.Http;
using System.Threading.Tasks;

namespace MoneyKeeper.Main.Services
{
    using Models;
    using Req;
    using Rsp;

    /// <summary>
    /// Account service
    /// </summary>
    public class AccountService : BaseService<UserModel>
    {
        #region -- Methods --

        /// <summary>
        /// Initialize
        /// </summary>
        public AccountService() { }


        public async Task<SingleRsp> Save(BaseReq req)
        {
            SingleRsp res = null;

            try
            {
                var data = CreateData(req);
                var client = new HttpClient();
                var rsp = await client.PostAsync(Host + "account/save", data);

                if (rsp.IsSuccessStatusCode)
                {
                    var t = await rsp.Content.ReadAsStringAsync();
                    res = JsonConvert.DeserializeObject<SingleRsp>(t);
                }
            }
            catch (Exception ex)
            {
                Debug.WriteLine(ex);
            }

            return res;
        }

        public async Task<AccountRsp> Search(BaseReq req)
        {
            AccountRsp res = null;

            try
            {
                var data = CreateData(req);
                var client = CreateClient();
                var rsp = await client.PostAsync(Host + "account/search", data);

                if (rsp.IsSuccessStatusCode)
                {
                    var t = await rsp.Content.ReadAsStringAsync();
                    res = JsonConvert.DeserializeObject<AccountRsp>(t);
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