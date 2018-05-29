using System.Security.Cryptography;
using System.Text;

namespace MoneyKeeper.Token.Common
{
    /// <summary>
    /// Utils
    /// </summary>
    public static class Utils
    {
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