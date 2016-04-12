using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Mime;
using System.Web;

namespace CloudPad_Website.Classes
{
    public class Note
    {
        // Fields
        
        // Properties
        public int Id { get; set; }
        public string Subject { get; set; }
        public string Text { get; set; }

        // Constructors
        public Note(int id, string subject, string text)
        {
            Id = id;
            Subject = subject;
            Text = text;
        }

        // Methods

    }
}