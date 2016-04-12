package app.com.example.android.cloudpad_app.Classes.Physical.PushMessages;

import java.util.ArrayList;
import java.util.List;

import nlusersi324201edwinvanrooij.fhict.httpathena.libraryproject.Classes.Physical.Account;

/**
 * Created by Edwin on 12/3/2015.
 */
public abstract class SendPushMessage {
    //region Fields
    private final String channel;
    private final String event;
    String message;
    final List<Integer> accountIdList;
    //endregion

    //region Properties

    public String getChannel() {
        return channel;
    }

    public String getEvent() {
        return event;
    }

    public String getMessage() {
        return message;
    }

    //endregion

    //region Constructors
    SendPushMessage(String channel, String event, List<Integer> accountIdList) {
        this.channel = channel;
        this.event = event;
        this.accountIdList = accountIdList;
    }
    //endregion

    public static List<Integer> AccountsToIntList(List<Account> accounts) {
        List<Integer> result = new ArrayList<>();

        for (Account a :
                accounts) {
            result.add(a.getId());
        }

        return result;
    }

    String AccountIdListToString(List<Integer> account_id_list) {
        StringBuilder result = new StringBuilder();
        for (Integer i :
                account_id_list) {
            result.append(i);
            result.append(" ");
        }
        return result.toString().trim();
    }
    //endregion

    //region Enums

    //endregion
}
