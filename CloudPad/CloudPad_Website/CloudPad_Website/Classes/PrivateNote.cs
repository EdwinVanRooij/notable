using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace CloudPad_Website.Classes
{
    public class PrivateNote : Note
    {
        // Fields


        // Properties


        // Constructors
        public PrivateNote(int id, string subject, string text) : base(id, subject, text)
        {
            
        }

        // Methods
        public override string ToString()
        {
            return Subject + ": " + Text;
        }
    }
}