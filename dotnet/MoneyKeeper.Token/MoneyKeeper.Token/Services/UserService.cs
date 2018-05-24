using Newtonsoft.Json;
using System.Net.Http;
using System.Threading.Tasks;

namespace MoneyKeeper.Token.Services
{
    using Models;
    using Rsp;

    /// <summary>
    /// User service
    /// </summary>
    public class UserService : BaseService<BaseModel>
    {
        #region -- Methods --

        /// <summary>
        /// Initialize
        /// </summary>
        public UserService() { }

        /// <summary>
        /// Verify activation code
        /// </summary>
        /// <param name="code">Activation code</param>
        /// <returns>Return the result</returns>
        public async Task<SingleRsp> VerifyActivation(string code)
        {
            SingleRsp res = null;

            var m = new { keyword = code };
            var data = CreateData(m);

            var client = new HttpClient();
            var rsp = await client.PostAsync(Host + "user/verify-activation", data);

            if (rsp.IsSuccessStatusCode)
            {
                var t = await rsp.Content.ReadAsStringAsync();
                res = JsonConvert.DeserializeObject<SingleRsp>(t);
            }

            return res;
        }

        #endregion
    }
}