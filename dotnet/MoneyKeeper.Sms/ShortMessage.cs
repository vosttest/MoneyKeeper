using System.Collections.Generic;

namespace MoneyKeeper.Sms
{
    /// <summary>
    /// Short message
    /// </summary>
    public class ShortMessage
    {
        public string Index { get; set; }

        public string Status { get; set; }

        public string Sender { get; set; }

        public string Alphabet { get; set; }

        public string Sent { get; set; }

        public string Message { get; set; }
    }

    /// <summary>
    /// ShortMessage collection
    /// </summary>
    public class ShortMessageCollection : List<ShortMessage> { }
}