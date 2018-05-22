using Microsoft.AspNetCore;
using Microsoft.AspNetCore.Hosting;

namespace MoneyKeeper.OTP
{
    using UTL;

    public class Program
    {
        public static void Main(string[] args)
        {
            _port = SMS.DetectPort();
            BuildWebHost(args).Run();
        }

        public static IWebHost BuildWebHost(string[] args) =>
            WebHost.CreateDefaultBuilder(args)
                .UseStartup<Startup>()
                .Build();

        public static string _port;
    }
}