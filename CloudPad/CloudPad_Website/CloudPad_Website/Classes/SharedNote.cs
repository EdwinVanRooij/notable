using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace CloudPad_Website.Classes
{
    public class SharedNote : Note
    {
        // Fields


        // Properties
        public string Owner { get; set; }
        public DateTime LastEdited { get; set; }

        // Constructors
        public SharedNote(int id, string subject, string text, string owner, DateTime lastEdited) : base(id, subject, text)
        {
            Owner = owner;
            LastEdited = lastEdited;
        }

        // Methods
        public override string ToString()
        {
            return Subject + ": " + Text + ", ~" + Owner;
        }
    }
}