using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace CloudPad_Website.Classes
{
    public class Account
    {
        // Fields
        private int _id;
        private string _username;
        private string _email;
        private string _password;
        private bool _blocked;

        public Account(int id, string username, string email, string password, bool blocked)
        {
            this._id = id;
            this._username = username;
            this._email = email;
            this._password = password;
            this._blocked = blocked;
        }

        // Properties


        // Constructors


        // Methods

    }
}