using System.Net.Http;
using System.Threading.Tasks;

namespace MoneyKeeper.Token.Services
{
    using Models;

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
        /// <param name="email">Email</param>
        /// <returns>Return the result</returns>
        public async Task<bool> VerifyActivation(string email)
        {
            var m = new
            {
                keyword = email
            };
            var data = CreateData(m);

            var client = new HttpClient();
            var rsp = await client.PostAsync(Host + "user/verify-activation", data);
            if (rsp.IsSuccessStatusCode)
            {
                return true;
            }

            return false;
        }

        #endregion
    }
}