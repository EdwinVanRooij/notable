<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="Default.aspx.cs" Inherits="CloudPad_Website.Default" MasterPageFile="MasterPages/LoginLogoutRegister.Master" %>

<asp:Content runat="server" ContentPlaceHolderID="LoginLogoutRegisterContent">
    <h2 class="content-head is-center">Registreren</h2>
    <div class="pure-g is-center">
        <div class="l-box-lrg pure-u-1 pure-u-md-2-5">
            <form id="form1" runat="server" class="pure-form pure-form-stacked">
                <fieldset>
                    <asp:Label ID="lbUsername" runat="server" Type="text" Text="Gebruikersnaam" CssClass="label register-head" />
                    <asp:TextBox ID="tbUsername" runat="server" CssClass="textbox" type="text" placeholder="Voorbeeld123" />

                    <asp:Label ID="lbEmail" runat="server" Type="email" Text="Email" CssClass="label register-head" />
                    <asp:TextBox ID="tbEmail" runat="server" CssClass="textbox pure-input-1" type="email" placeholder="jouw@email.com" />

                    <asp:Label ID="lbPassword" runat="server" Type="password" Text="Wachtwoord" CssClass="label register-head" />
                    <asp:TextBox ID="tbPassword" runat="server" CssClass="textbox" type="password" placeholder="*********" />

                    <asp:Button ID="btnRegister" runat="server" Text="Registreer" CssClass="pure-button" OnClick="btnRegister_Click" />

                    <asp:Label ID="linkLogin" CssClass="hyperlink" runat="server"><a href="Login.aspx">Ik heb al een account</a></asp:Label>

                </fieldset>
            </form>
        </div>
    </div>
</asp:Content>
