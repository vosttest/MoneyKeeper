using System.IdentityModel.Tokens.Jwt;
using System.Linq;
using System.Security.Cryptography;
using System.Text;
using Xamarin.Forms;

namespace MoneyKeeper.Token.Common
{
    /// <summary>
    /// Utils
    /// </summary>
    public static class Utils
    {
        /// <summary>
        /// Get variable
        /// </summary>
        /// <param name="name">Variable name</param>
        /// <returns>Return the result</returns>
        public static string GetVar(string name)
        {
            var res = string.Empty;

            try
            {
                res = Application.Current.Properties[name].ToString();
            }
            catch { }

            return res;
        }

        /// <summary>
        /// Set variable
        /// </summary>
        /// <param name="name">Variable name</param>
        /// <param name="value">Variable value</param>
        public static void SetVar(string name, string value)
        {
            Application.Current.Properties[name] = value;
            Application.Current.SavePropertiesAsync();
        }

        /// <summary>
        /// Decode JWT
        /// </summary>
        /// <param name="jwt">JSON web token</param>
        /// <param name="type">Type</param>
        /// <returns>Return the result</returns>
        public static string DecodeJwt(string jwt, string type)
        {
            var token = new JwtSecurityToken(jwt);
            var res = token.Claims.First(c => c.Type == type).Value;
            return res;
        }

        /// <summary>
        /// Get token
        /// </summary>
        /// <param name="s">String data</param>
        /// <param name="num">Number of digits will get</param>
        /// <returns>Return the result</returns>
        public static string GetToken(string s, int num)
        {
            var res = string.Empty;
            s = Const.Authentication.TOKEN_KEY1 + s + Const.Authentication.TOKEN_KEY2;
            var hash = GenerateSHA256(s);
            var arr = hash.Split(Const.SpecialChar.Minus);

            if (num > 8 || num < 1)
            {
                num = 8;
            }

            foreach (var i in arr)
            {
                if (num == 0)
                {
                    break;
                }

                foreach (var item in i)
                {
                    if (char.IsDigit(item))
                    {
                        res += item;
                        break;
                    }
                }

                num--;
            }

            return res;
        }

        /// <summary>
        /// Generate SHA-256
        /// </summary>
        /// <param name="s">String data</param>
        /// <returns>Return the result</returns>
        public static string GenerateSHA256(string s)
        {
            var res = string.Empty;

            var sha = SHA256Managed.Create();
            var bytes = Encoding.UTF8.GetBytes(s);
            var hash = sha.ComputeHash(bytes);
            res = GenerateSHA256(hash);

            return res;
        }

        /// <summary>
        /// Generate SHA-256
        /// </summary>
        /// <param name="hash">Array byte</param>
        /// <returns>Return the result</returns>
        private static string GenerateSHA256(byte[] hash)
        {
            var res = new StringBuilder();

            for (int i = 0; i < hash.Length; i++)
            {
                res.Append(hash[i].ToString("X2"));

                if ((i + 1) % 4 == 0 && i < (hash.Length - 1))
                {
                    res.Append(Const.SpecialChar.Minus);
                }
            }

            return res.ToString();
        }
    }
}