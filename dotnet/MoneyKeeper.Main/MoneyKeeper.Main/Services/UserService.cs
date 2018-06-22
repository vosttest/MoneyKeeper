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
    /// User service
    /// </summary>
    public class UserService : BaseService<UserModel>
    {
        #region -- Methods --

        /// <summary>
        /// Initialize
        /// </summary>
        public UserService() { }

        /// <summary>
        /// Sign in
        /// </summary>
        /// <param name="req">Request</param>
        /// <returns>Return the result</returns>
        public async Task<SignInRsp> SignIn(SignInReq req)
        {
            SignInRsp res = null;

            try
            {
                var data = CreateData(req);
                var client = new HttpClient();
                var rsp = await client.PostAsync(Host + "user/sign-in", data);

                if (rsp.IsSuccessStatusCode)
                {
                    var t = await rsp.Content.ReadAsStringAsync();
                    res = JsonConvert.DeserializeObject<SignInRsp>(t);
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