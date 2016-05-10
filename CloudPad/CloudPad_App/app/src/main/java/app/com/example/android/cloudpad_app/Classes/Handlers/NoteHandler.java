package app.com.example.android.cloudpad_app.classes.handlers;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import app.com.example.android.cloudpad_app.classes.physical.Note;
import app.com.example.android.cloudpad_app.classes.physical.PrivateNote;
import app.com.example.android.cloudpad_app.classes.physical.SharedNote;
import nlusersi324201edwinvanrooij.fhict.httpathena.libraryproject.classes.asynctasks.AsyncURLRequest;
import nlusersi324201edwinvanrooij.fhict.httpathena.libraryproject.classes.handlers.ConvertHandler;
import nlusersi324201edwinvanrooij.fhict.httpathena.libraryproject.classes.handlers.DatabaseHandler;
import nlusersi324201edwinvanrooij.fhict.httpathena.libraryproject.classes.interfaces.AsyncResponse;
import nlusersi324201edwinvanrooij.fhict.httpathena.libraryproject.classes.physical.Account;

/**
 * Created by Edwin on 11/23/2015.
 */
public class NoteHandler {
    //region Fields
    private final Context c;
    private Account account;
    //endregion

    //region Properties

    //endregion

    //region Constructors
    public NoteHandler(Context c) {
        this.c = c;
    }
    public NoteHandler(Context c, Account account) {
        this.c = c;
        this.account= account;
    }
    //endregion

    //region Methods
    public void addNote(AsyncResponse a, Note note) {

    }

    public void getPrivateNotes(AsyncResponse a, boolean bool_progressbar) throws UnsupportedEncodingException, MalformedURLException {
        String query ="select n.id, n.subject, n.text, n.last_edited, n.owner_id, (select a2.username from account a2 where a2.id = n.owner_id) as owner from note n, account a, share an where n.id = an.note_id and an.account_id = a.id and n.active = 1 and (select count(*) from share an where an.note_id = n.id) = 1 and a.id = " + account.getId()+ " order by last_edited desc";
        new DatabaseHandler(c, query, AsyncURLRequest.queryType.Select).withProgressBar(bool_progressbar).withAsyncResponse(a).execute();
    }

    public void getSharedNotes(AsyncResponse a, boolean bool_progressbar) throws UnsupportedEncodingException, MalformedURLException {
        String query = "select n.id, n.subject, n.text, n.last_edited, (select a2.username from account a2 where a2.id = n.owner_id) as owner, n.owner_id from note n, account a, share an where n.id = an.note_id and an.account_id = a.id and n.active = 1 and (select count(*) from share an where an.note_id = n.id) > 1 and a.id = " + account.getId() + " order by last_edited desc";
        new DatabaseHandler(c, query, AsyncURLRequest.queryType.Select).withProgressBar(bool_progressbar).withAsyncResponse(a).execute();
    }

    public static List<Account> getAccountsFromJSON(String json_string) throws JSONException {
        ArrayList<Account> accounts = new ArrayList<>();

        JSONArray jsonArray = new JSONArray(json_string);

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject j = jsonArray.getJSONObject(i);

            int id = j.getInt("id");
            String username = j.getString("username");
            String email = j.getString("email");
            String password = j.getString("password");
            Date creation = ConvertHandler.StringToDate(j.getString("creation"));
            boolean blocked = ConvertHandler.IntToBoolean(j.getInt("blocked"));

            accounts.add(new Account(id, username, email, password, creation, blocked));
        }
        return accounts;
    }

    public List<Note> getSharedNotesFromJSON(String json_string) throws JSONException {
        ArrayList<Note> notes = new ArrayList<>();
        JSONArray json_array = new JSONArray(json_string);

        for (int i = 0; i < json_array.length(); i++) {
            JSONObject j = json_array.getJSONObject(i);
            int id = j.getInt("id");
            String subject = j.getString("subject");
            String text = j.getString("text");
            String owner = j.getString("owner");
            int ownerId = j.getInt("owner_id");
            Date last_edited = ConvertHandler.StringToDate(j.getString("last_edited"));

            notes.add(new SharedNote(id, subject, text, ownerId, owner, last_edited));
        }
        return notes;
    }

    public List<Note> getPrivateNotesFromJSON(String json_string) throws JSONException{
        ArrayList<Note> notes = new ArrayList<>();
        JSONArray jsonarr = new JSONArray(json_string);

        for (int i = 0; i < jsonarr.length(); i++) {
            JSONObject j = jsonarr.getJSONObject(i);
            int id = j.getInt("id");
            String subject = j.getString("subject");
            String text = j.getString("text");
            int ownerId = j.getInt("owner_id");

            notes.add(new PrivateNote(id, subject, text, ownerId));
        }

        return notes;
    }
    //endregion
}
