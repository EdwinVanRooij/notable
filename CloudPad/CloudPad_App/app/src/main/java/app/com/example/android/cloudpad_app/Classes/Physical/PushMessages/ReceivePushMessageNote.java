package app.com.example.android.cloudpad_app.Classes.Physical.PushMessages;

import android.content.Context;
import android.util.Log;

import org.json.JSONException;

import app.com.example.android.cloudpad_app.Classes.MyNotification;
import app.com.example.android.cloudpad_app.Classes.Physical.Notes.Note;
import nlusersi324201edwinvanrooij.fhict.httpathena.libraryproject.Classes.Physical.Account;

/**
 * Created by Edwin on 12/2/2015.
 */
public class ReceivePushMessageNote extends ReceivePushMessage {
    //region Fields
    private static final String TAG = "ReceivePushMessageNote";
    private Note note;
    private final PushMessageEnums.NoteEvent event;
    //endregion

    //region Properties

    //endregion

    //region Constructors
    public ReceivePushMessageNote(Context c, Account a, String message, PushMessageEnums.NoteEvent e) {
        super(c, a, message);
        this.event = e;
        Initialize();
    }
    //endregion

    //region Methods
    private void Initialize() {
            try {
                String subject = message.getString(Settings.KEY_SUBJECT);
                String text = message.getString(Settings.KEY_TEXT);
                int owner_id = message.getInt(Settings.KEY_OWNER_ID);
                note = new Note(subject, text, owner_id);

                setAccountIdList(message.getString(Settings.KEY_ACCOUNT_ID_LIST));
            } catch (JSONException e) {
                e.printStackTrace();
            }
    }

    @Override
    public void doStuff() {
        if (!isForMe()) {
            Log.d(TAG, "doStuff: Wasn't for me");
            return;
        }
        String content = "---";
        switch (event) {
            case New:
                content = note.getNewNoteNotificationContent();
                Log.d(TAG, "doStuff: New note made");
                break;
            case Update:
                content = note.getSubject() + " is zojuist aangepast";
                Log.d(TAG, "doStuff: This was an update.");
                break;
            case Delete:
                content = note.getSubject() + " is zojuist verwijderd";
                Log.d(TAG, "doStuff: Note was deleted.");
            default:
                Log.d(TAG, "doStuff: Could not determine Event in ReceivePushMessageNote");
        }
        new MyNotification(c, content).show();
    }
    //endregion
}
