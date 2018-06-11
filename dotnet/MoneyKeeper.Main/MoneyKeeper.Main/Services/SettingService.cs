using Newtonsoft.Json;
using System;
using System.Diagnostics;
using System.Threading.Tasks;

namespace MoneyKeeper.Main.Services
{
    using Models;
    using Rsp;

    /// <summary>
    /// Setting service
    /// </summary>
    public class SettingService : BaseService<UserModel>
    {
        #region -- Methods --

        /// <summary>
        /// Initialize
        /// </summary>
        public SettingService() { }

        /// <summary>
        /// Search
        /// </summary>
        /// <returns>Return the result</returns>
        public async Task<SettingRsp> Search()
        {
            SettingRsp res = null;

            try
            {
                var data = CreateData(string.Empty);
                var client = CreateClient();
                var rsp = await client.PostAsync(Host + "setting/search", data);

                if (rsp.IsSuccessStatusCode)
                {
                    var t = await rsp.Content.ReadAsStringAsync();
                    res = JsonConvert.DeserializeObject<SettingRsp>(t);
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