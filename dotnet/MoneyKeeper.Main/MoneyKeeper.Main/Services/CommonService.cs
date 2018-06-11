using Newtonsoft.Json;
using System;
using System.Diagnostics;
using System.Threading.Tasks;

namespace MoneyKeeper.Main.Services
{
    using Models;
    using Req;
    using Rsp;

    /// <summary>
    /// Common service
    /// </summary>
    public class CommonService : BaseService<UserModel>
    {
        #region -- Methods --

        /// <summary>
        /// Initialize
        /// </summary>
        public CommonService() { }

        /// <summary>
        /// Search
        /// </summary>
        /// <param name="req">Request</param>
        /// <returns>Return the result</returns>
        public async Task<CommonRsp> Search(BaseReq req)
        {
            CommonRsp res = null;

            try
            {
                var data = CreateData(req);
                var client = CreateClient();
                var rsp = await client.PostAsync(Host + "common/search", data);

                if (rsp.IsSuccessStatusCode)
                {
                    var t = await rsp.Content.ReadAsStringAsync();
                    res = JsonConvert.DeserializeObject<CommonRsp>(t);
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