package nlusersi324201edwinvanrooij.fhict.httpathena.libraryproject.classes.handlers;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.Date;

import nlusersi324201edwinvanrooij.fhict.httpathena.libraryproject.classes.asynctasks.AsyncDataRequest;
import nlusersi324201edwinvanrooij.fhict.httpathena.libraryproject.classes.asynctasks.AsyncURLRequest;
import nlusersi324201edwinvanrooij.fhict.httpathena.libraryproject.classes.interfaces.AsyncResponse;
import nlusersi324201edwinvanrooij.fhict.httpathena.libraryproject.classes.physical.Account;

/**
 * Created by Edwin on 11/23/2015.
 */
public class AccountHandler {
    //region Fields
    private static final String TAG = "AccountHandler";
    private final Context c;
    private Account account;
    //endregion

    //region Properties

    //endregion

    //region Constructors
    public AccountHandler(Context c) {
        this.c = c;
    }
    public AccountHandler(Context c, Account account) {
        this.c = c;
        this.account = account;
    }
    //endregion

    //region Methods
    // We can't pass an account object to the database class since we don't know
    // if the credentials are a valid account yet. So we're checking it in the
    // 'dirty' way, here in the handler instead of the database class.
    public void getAccount(AsyncResponse a, String username, String password) {
        String query = "select *, count(*) as amount from account where username='" + username + "' and password = password('" + password + "') and blocked = 0";
        new AsyncDataRequest(a, c, AsyncURLRequest.queryType.Select, query, true).execute();
    }
    // Similar story here. Read above.
    public void getUsername(AsyncResponse a, String username) {
        String query = "select count(*) boolean from account where username = '" + username + "';";
        new AsyncDataRequest(a, c, AsyncURLRequest.queryType.Select, query, true).execute();
    }
    // We don't have the complete account yet right here because some things
    // are generated in the database, like the ID & creation date.
    // We're handling this the same way as above.
    public void insertAccount(AsyncResponse a, String username, String email, String password) {
        String query = "insert into account(username, email, password) values ('" + username + "', '" + email + "', password('" + password + "'));";
        new AsyncDataRequest(a, c, AsyncURLRequest.queryType.Modify, query, true).execute();
    }

    public void getAccountsExcludingSelf(AsyncResponse a) throws UnsupportedEncodingException, MalformedURLException {
        String query = "select * from account where id <> " + account.getId();
        new DatabaseHandler(c, query, AsyncURLRequest.queryType.Select).withProgressBar().withAsyncResponse(a).execute();
    }

    public boolean booleanFromJSON(String json_string) throws JSONException {
        JSONArray result = new JSONArray(json_string);
        JSONObject json = result.getJSONObject(0);

        return ConvertHandler.IntToBoolean(json.getInt("boolean"));
    }

    public Account getAccountIfExists(String json_string) throws JSONException {
        JSONArray result = new JSONArray(json_string);
        JSONObject json = result.getJSONObject(0);

        boolean user_is_correct = ConvertHandler.IntToBoolean(json.getInt("amount"));
        if (user_is_correct) {
            int id = json.getInt("id");
            String username = json.getString("username");
            String email = json.getString("email");
            String password = json.getString("password");
            Date creation = ConvertHandler.StringToDate(json.getString("creation"));
            boolean blocked = ConvertHandler.IntToBoolean(json.getInt("blocked"));
            return new Account(id, username, email, password, creation, blocked);
        } else {
            Log.d(TAG, "getAccountIfExists: Could not return an account");
            return null;
        }
    }
    //endregion
}