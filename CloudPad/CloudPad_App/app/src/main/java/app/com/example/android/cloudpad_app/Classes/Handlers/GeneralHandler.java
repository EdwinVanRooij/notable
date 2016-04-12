package app.com.example.android.cloudpad_app.Classes.Handlers;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;

import com.mikepenz.fastadapter.items.AbstractItem;

import java.util.ArrayList;
import java.util.List;

import app.com.example.android.cloudpad_app.Classes.AdapterItems.AdapterItemAccount;
import app.com.example.android.cloudpad_app.Classes.AdapterItems.AdapterItemInfoAccount;
import app.com.example.android.cloudpad_app.Classes.AdapterItems.AdapterItemNote;
import app.com.example.android.cloudpad_app.Classes.Physical.Notes.Note;
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

    public static List<AdapterItemNote> convertNotesToAdapterItems(List<Note> notes) {
        List<AdapterItemNote> iNotes = new ArrayList<>();
        for (Note n :
                notes) {
            iNotes.add(new AdapterItemNote(n));
        }
        return iNotes;
    }

    public static List<AbstractItem> convertInfoAccountsToAdapterItems(List<Account> accounts) {
        List<AbstractItem> items = new ArrayList<>();
        for (Account i :
                accounts) {
            items.add(new AdapterItemInfoAccount(i));
        }
        return items;
    }

    public static List<AbstractItem> convertAccountsToAdapterItems(List<Account> accounts) {
        List<AbstractItem> items = new ArrayList<>();
        for (Account i :
                accounts) {
            items.add(new AdapterItemAccount(i));
        }
        return items;
    }
}
