using System;
using CloudPad_Website.Classes;

namespace CloudPad_Website
{
    public partial class Home : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            int id = 4;
            
            //foreach (PrivateNote note in new Database().getAllPrivateNotes(id))
            //{
            //    libPrivate.Items.Add(note.ToString());
            //}
            
            foreach (PrivateNote note in new Database().getAllSharedNotes(id))
            {
                tbShared.Text += "\r\n" + note.ToString();
            }
        }
    }
}