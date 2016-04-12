using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Windows.Forms;
using MySql.Data.MySqlClient;

namespace CloudPad_Website.Classes
{
    public class Database
    {
        // Fields
        private string _connectionstring;
        private string _server;
        private string _database;
        private string _username;
        private string _password;

        // Properties
        public string Query { get; set; }

        // Constructors
        public Database()
        {
            _server = "athena01.fhict.local";
            _database = "dbi324201";
            _username = "dbi324201";
            _password = "s8SXuJPyVN";
            _connectionstring =  @"Data Source="+_server+"; Database='"+_database+"'; Username="+_username+"; Password='"+_password+"'";
        }

        // Methods
        public List<string> CheckDBCon()
        {
            Query = "SELECT * FROM note";
            List<string> result = new List<string>();
            
            using (MySqlConnection c = new MySqlConnection(_connectionstring))
            {
                c.Open();

                //Create Command
                MySqlCommand cmd = new MySqlCommand(Query, c);
                //Create a data reader and Execute the command
                MySqlDataReader dataReader = cmd.ExecuteReader();

                //Read the data and store them in the list
                while (dataReader.Read())
                {
                    string line = "";
                    string comma = ", ";
                    string dot = ".";

                    line += "Id: " + dataReader.GetValue(0);
                    line += comma;
                    line += "Onderwerp: " + dataReader.GetValue(1);
                    line += comma;
                    line += "Tekst: " + dataReader.GetValue(2);
                    line += comma;

                    // Get the id
                    int id = Convert.ToInt32(dataReader.GetValue(3));
                    line += "Gemaakt door: " + GetUsernameById(id);
                    line += dot;

                    result.Add(line);
                }

                //close Data Reader
                dataReader.Close();
                cmd.Dispose();

                //close Connection
                c.Close();
            }

            return result;
        }

        public List<PrivateNote> getAllPrivateNotes(int p_id)
        {
            Query = "select n.id, n.subject, n.text, n.last_edited, (select a2.username from account a2 where a2.id = n.owner_id) as owner from note n, account a, account_note an where n.id = an.note_id and an.account_id = a.id and n.active = 1 and (select count(*) from account_note an where an.note_id = n.id) = 1 and a.id = " + p_id;
            List<PrivateNote> result = new List<PrivateNote>();

            using (MySqlConnection c = new MySqlConnection(_connectionstring))
            {
                c.Open();

                //Create Command
                MySqlCommand cmd = new MySqlCommand(Query, c);
                //Create a data reader and Execute the command
                MySqlDataReader dataReader = cmd.ExecuteReader();

                //Read the data and store them in the list
                while (dataReader.Read())
                {
                    int id = Convert.ToInt32(dataReader.GetValue(0));
                    string subject = Convert.ToString(dataReader.GetValue(1));
                    string text = Convert.ToString(dataReader.GetValue(2));

                    result.Add(new PrivateNote(id, subject, text));
                }

                //close Data Reader
                dataReader.Close();
                cmd.Dispose();

                //close Connection
                c.Close();
            }

            return result;
        }

        public int checkLoginDetails(string username, string password)
        {
            Query = "select id, count(*) as amount from account where username = '" + username + "' and password = '"+ password + "'";
            int result = 0;

            using (MySqlConnection c = new MySqlConnection(_connectionstring))
            {
                c.Open();

                //Create Command
                MySqlCommand cmd = new MySqlCommand(Query, c);
                //Create a data reader and Execute the command
                MySqlDataReader dataReader = cmd.ExecuteReader();

                //Read the data and store them in the list
                while (dataReader.Read())
                {
                    result = Convert.ToInt32(dataReader.GetValue(0));
                }

                //close Data Reader
                dataReader.Close();
                cmd.Dispose();

                //close Connection
                c.Close();
            }

            return result;
        }

        public List<PrivateNote> getAllSharedNotes(int p_id)
        {
            Query = "select n.id, n.subject, n.text, n.last_edited, (select a2.username from account a2 where a2.id = n.owner_id) as owner from note n, account a, account_note an where n.id = an.note_id and an.account_id = a.id and n.active = 1 and (select count(*) from account_note an where an.note_id = n.id) > 1 and a.id = " + p_id;
            List<PrivateNote> result = new List<PrivateNote>();

            using (MySqlConnection c = new MySqlConnection(_connectionstring))
            {
                c.Open();

                //Create Command
                MySqlCommand cmd = new MySqlCommand(Query, c);
                //Create a data reader and Execute the command
                MySqlDataReader dataReader = cmd.ExecuteReader();

                //Read the data and store them in the list
                while (dataReader.Read())
                {
                    int id = Convert.ToInt32(dataReader.GetValue(0));
                    string subject = Convert.ToString(dataReader.GetValue(1));
                    string text = Convert.ToString(dataReader.GetValue(2));

                    result.Add(new PrivateNote(id, subject, text));
                }

                //close Data Reader
                dataReader.Close();
                cmd.Dispose();

                //close Connection
                c.Close();
            }

            return result;
        }

        public string GetUsernameById(int id)
        {
            Query = "SELECT username FROM account where id = " + id;
            string result = "Could not get username by ID.";

            using (MySqlConnection c = new MySqlConnection(_connectionstring))
            {
                c.Open();

                //Create Command
                MySqlCommand cmd = new MySqlCommand(Query, c);
                //Create a data reader and Execute the command
                MySqlDataReader dataReader = cmd.ExecuteReader();

                //Read the data and store them in the list
                while (dataReader.Read())
                {
                    result = dataReader.GetValue(0).ToString();
                }

                //close Data Reader
                dataReader.Close();
                cmd.Dispose();

                //close Connection
                c.Close();
            }

            return result;
        }

        Dictionary<int, string> DictionaryIdWithUsername()
        {
            Query = "select id, username from account";
            Dictionary<int, string> result = new Dictionary<int, string>();

            using (MySqlConnection c = new MySqlConnection(_connectionstring))
            {
                c.Open();

                //Create Command
                MySqlCommand cmd = new MySqlCommand(Query, c);
                //Create a data reader and Execute the command
                MySqlDataReader dataReader = cmd.ExecuteReader();

                //Read the data and store them in the list
                while (dataReader.Read())
                {
                    int id = Convert.ToInt32(dataReader.GetValue(0));
                    string username = Convert.ToString(dataReader.GetValue(1));

                    result.Add(id, username);
                }

                //close Data Reader
                dataReader.Close();
                cmd.Dispose();

                //close Connection
                c.Close();
            }

            return result;
        }

        private void OpenConnection()
        {
            
        }

        public void CloseConnection()
        {
            
        }
    }
}