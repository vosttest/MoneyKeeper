using Newtonsoft.Json;
using System.Collections.Generic;
using System.Net.Http;
using System.Net.Http.Headers;
using System.Threading.Tasks;

namespace MoneyKeeper.Token.Services
{
    /// <summary>
    /// Base service
    /// </summary>
    /// <typeparam name="T">Model class type</typeparam>
    public class BaseService<T> where T : class
    {
        #region -- Methods --

        /// <summary>
        /// Initialize
        /// </summary>
        public BaseService() { }

        /// <summary>
        /// Add
        /// </summary>
        /// <param name="m">Model</param>
        /// <returns>Return the result</returns>
        public virtual Task<bool> AddAsync(T m)
        {
            return Task.FromResult(true);
        }

        /// <summary>
        /// Update
        /// </summary>
        /// <param name="m">Model</param>
        /// <returns>Return the result</returns>
        public virtual Task<bool> UpdateAsync(T m)
        {
            return Task.FromResult(true);
        }

        /// <summary>
        /// Delete
        /// </summary>
        /// <param name="id">Identity</param>
        /// <returns>Return the result</returns>
        public virtual Task<bool> DeleteAsync(string id)
        {
            return Task.FromResult(true);
        }

        /// <summary>
        /// Get data
        /// </summary>
        /// <param name="id">Identity</param>
        /// <returns>Return the result</returns>
        public virtual Task<T> GetAsync(string id)
        {
            T m = null;
            return Task.FromResult(m);
        }

        /// <summary>
        /// Get all data
        /// </summary>
        /// <param name="refresh">Force refresh</param>
        /// <returns>Return the result</returns>
        public virtual Task<IEnumerable<T>> GetAllAsync(bool refresh = false)
        {
            IEnumerable<T> e = null;
            return Task.FromResult(e);
        }

        /// <summary>
        /// Create client
        /// </summary>
        /// <returns>Return the result</returns>
        protected HttpClient CreateClient()
        {
            var res = new HttpClient();
            var t = new AuthenticationHeaderValue("Bearer", "");
            res.DefaultRequestHeaders.Authorization = t;
            return res;
        }

        /// <summary>
        /// Create data
        /// </summary>
        /// <param name="data">Data</param>
        /// <returns>Return the result</returns>
        protected StringContent CreateData(object data)
        {
            var jo = JsonConvert.SerializeObject(data);
            var res = new StringContent(jo);
            var t = new MediaTypeHeaderValue("application/json");
            res.Headers.ContentType = t;
            return res;
        }

        #endregion

        #region -- Constants --

        /// <summary>
        /// Host
        /// </summary>
        protected const string Host = "https://mk-dev.herokuapp.com/";

        /// <summary>
        /// Host authorized
        /// </summary>
        protected const string HostAuthorized = Host + "user/";

        /// <summary>
        /// Host resource
        /// </summary>
        protected const string HostResource = Host + "resx/";

        #endregion
    }
}
