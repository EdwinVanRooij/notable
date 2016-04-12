<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="Home.aspx.cs" Inherits="CloudPad_Website.Home" MasterPageFile="~/MasterPages/Home.Master" %>

<asp:Content ContentPlaceHolderID="NotesContainer" runat="server">
    <form runat="server">
        <asp:Label ID="lbPrivate" runat="server" Text="Privé"></asp:Label>
        <br />
        <asp:ListBox ID="libPrivate" runat="server"></asp:ListBox>
        <br />
        <br />
        <asp:Label ID="lbShared" runat="server" Text="Shared"></asp:Label>
        <br />
        <asp:TextBox ID="tbShared" runat="server" CssClass="TextBox" TextMode="MultiLine" Rows="15"></asp:TextBox>
    
        <br />
        <br />
        <asp:Label ID="lbSubject" runat="server" Text="Onderwerp"></asp:Label>
        <br />
        <asp:TextBox ID="tbSubject" CssClass="TextBox"  runat="server" placeholder="onderwerp"></asp:TextBox>
        <br />
        <br />
        <asp:Label ID="lbText" runat="server" Text="Tekst"></asp:Label>
        <br />
        <asp:TextBox ID="tbText" CssClass="TextBox" runat="server" placeholder="tekst" TextMode="MultiLine" Rows="5"></asp:TextBox>
        <br />
        <br />
        <asp:Button ID="btnDelete" runat="server" Text="Verwijderen" />
        
        <asp:Button ID="btnSave" runat="server" Text="Opslaan" />
    </form>
</asp:Content>