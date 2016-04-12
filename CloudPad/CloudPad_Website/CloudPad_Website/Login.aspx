<%@ Page Title="" Language="C#" MasterPageFile="~/MasterPages/LoginLogoutRegister.Master" AutoEventWireup="true" CodeBehind="Login.aspx.cs" Inherits="CloudPad_Website.Login" %>


<asp:Content ContentPlaceHolderID="LoginLogoutRegisterContent" runat="server">
    <h2 class="content-head is-center">Login</h2>
    <div class="pure-g is-center">
        <div class="l-box-lrg pure-u-1 pure-u-md-2-5">
            <form id="form1" runat="server" class="pure-form pure-form-stacked">
                <fieldset>
                    <asp:Label ID="lbUsername" runat="server" Type="text" Text="Gebruikersnaam" CssClass="label register-head" />
                    <asp:TextBox ID="tbUsername" runat="server" CssClass="textbox" type="text" placeholder="Voorbeeld123" />

                    <asp:Label ID="lbPassword" runat="server" Type="password" Text="Wachtwoord" CssClass="label register-head" />
                    <asp:TextBox ID="tbPassword" runat="server" CssClass="textbox" type="password" placeholder="*********" />

                    <asp:Button ID="btnLogin" runat="server" Text="Login" CssClass="pure-button" OnClick="btnLogin_Click" />

                </fieldset>
            </form>
        </div>
    </div>
</asp:Content>
