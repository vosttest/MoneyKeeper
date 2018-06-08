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
    /// Voucher service
    /// </summary>
    public class VoucherService : BaseService<UserModel>
    {
        #region -- Methods --

        /// <summary>
        /// Initialize
        /// </summary>
        public VoucherService() { }


        /// <summary>
        /// Search
        /// </summary>
        /// <param name="req">Request</param>
        /// <returns>Return the result</returns>
        public async Task<VoucherRsp> Search(VoucherSearchReq req)
        {
            VoucherRsp res = null;

            try
            {
                var data = CreateData(req);
                var client = CreateClient();
                var rsp = await client.PostAsync(Host + "voucher/search", data);

                if (rsp.IsSuccessStatusCode)
                {
                    var t = await rsp.Content.ReadAsStringAsync();
                    res = JsonConvert.DeserializeObject<VoucherRsp>(t);
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