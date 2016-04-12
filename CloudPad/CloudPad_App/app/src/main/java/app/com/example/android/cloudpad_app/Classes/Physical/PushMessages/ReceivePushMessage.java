package app.com.example.android.cloudpad_app.Classes.Physical.PushMessages;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import nlusersi324201edwinvanrooij.fhict.httpathena.libraryproject.Classes.Physical.Account;

/**
 * Created by Edwin on 12/2/2015.
 */
public abstract class ReceivePushMessage {
    //region Fields
    final Context c;
    final JSONObject message;
    private final Account thisAccount;
    private List<Integer> accountIdList;
    //endregion

    //region Properties   }

    //endregion

    //region Constructors

    ReceivePushMessage(Context c, Account a, String message) {
        this.c = c;
        this.thisAccount = a;
        this.message = getJSONObject(message);
    }

    //endregion

    //region Methods
    private JSONObject getJSONObject(String message) {
        try {
            String result = message.replace("\\", "");
            result = "[" + result.substring(1, result.length() - 1) + "]";
            String para = result;
            JSONArray jsonArray = new JSONArray(para);

            return jsonArray.getJSONObject(0);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public abstract void doStuff();

    void setAccountIdList(String accountIdList_string) {
        List<Integer> result = new ArrayList<>();
        String[] split = accountIdList_string.split("\\s+");

        for (String s :
                split) {
            result.add(Integer.valueOf(s));
        }

        accountIdList = result;
    }

    boolean isForMe() {
        for (Integer i :
                accountIdList) {
            if (thisAccount.getId() == i) {
                return true;
            }
        }
        return false;
    }
    //endregion

    //region Enums

    //endregion
}
