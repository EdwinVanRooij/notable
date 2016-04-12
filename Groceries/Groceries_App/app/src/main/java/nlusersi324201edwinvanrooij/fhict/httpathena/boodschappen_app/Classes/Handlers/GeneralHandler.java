package nlusersi324201edwinvanrooij.fhict.httpathena.boodschappen_app.Classes.Handlers;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import nlusersi324201edwinvanrooij.fhict.httpathena.boodschappen_app.Classes.AdapterItems.AdapterItemGroceryList;
import nlusersi324201edwinvanrooij.fhict.httpathena.libraryproject.Classes.Config;
import nlusersi324201edwinvanrooij.fhict.httpathena.boodschappen_app.Classes.Physical.GroceryList;
import nlusersi324201edwinvanrooij.fhict.httpathena.boodschappen_app.Classes.Physical.Product;
import nlusersi324201edwinvanrooij.fhict.httpathena.libraryproject.Classes.Handlers.ConvertHandler;
import nlusersi324201edwinvanrooij.fhict.httpathena.libraryproject.Classes.Physical.Account;

/**
 * Created by Edwin on 11/9/2015.
 */
public class GeneralHandler {
    private static final String TAG = "GeneralHandler";

    public static void ClearTopGo(Activity from, Class to, int StartAnimation, int ExitAnimation) {
        Intent i = new Intent(from, to);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                Intent.FLAG_ACTIVITY_CLEAR_TASK |
                Intent.FLAG_ACTIVITY_NEW_TASK);
        Log.d(TAG, "ClearTopGo: cleared top");
        from.startActivity(i);
        from.overridePendingTransition(StartAnimation, ExitAnimation);

        Log.d(TAG, "ClearTopGo: started activity");
    }

    public static void ClearTopGo(Activity from, Class to) {
        Intent i = new Intent(from, to);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                Intent.FLAG_ACTIVITY_CLEAR_TASK |
                Intent.FLAG_ACTIVITY_NEW_TASK);
        Log.d(TAG, "ClearTopGo: cleared top");
        from.startActivity(i);

        Log.d(TAG, "ClearTopGo: started activity");
    }

    public static List<Product> getProductsFromJSON(String json_string) {
//        GeneralHandler.log("notesJSOn is " + json_string);
        ArrayList<Product> products = new ArrayList<>();
        try {
            JSONArray jsonarr = new JSONArray(json_string);
            for (int i = 0; i < jsonarr.length(); i++) {

                JSONObject j = jsonarr.getJSONObject(i);

                int id = j.getInt("id");
                String name = j.getString("name");
                int amount = j.getInt("amount");
                String remark = j.getString("remark");

                products.add(new Product(id, name, amount, remark));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return products;
    }

    public static List<AdapterItemGroceryList> convertListsToAdapterItems(List<GroceryList> notes) {
        List<AdapterItemGroceryList> iNotes = new ArrayList<>();
        for (GroceryList n :
                notes) {
            iNotes.add(new AdapterItemGroceryList(n));
        }
        return iNotes;
    }

    public static List<GroceryList> getGroceryListsFromJSON(String json_string) {
        ArrayList<GroceryList> lists = new ArrayList<>();
        try {
            JSONArray jsonarr = new JSONArray(json_string);
            for (int i = 0; i < jsonarr.length(); i++) {

                JSONObject j = jsonarr.getJSONObject(i);

                int id = j.getInt("id");
                String title = j.getString("title");

                lists.add(new GroceryList(id, title));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return lists;
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

    public static int getVersionBooleanInt(String json_string) {
        int result = -1;

        try {
            JSONArray jsonArray = new JSONArray(json_string);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject j = jsonArray.getJSONObject(i);

                result = j.getInt("number");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }
}
