package app.com.example.android.cloudpad_app.Classes.Physical.PushMessages;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import app.com.example.android.cloudpad_app.Classes.Physical.Notes.Note;

/**
 * Created by Edwin on 12/3/2015.
 */
public class SendPushMessageNote extends SendPushMessage {
    //region Fields
    private final Note note;
    //endregion

    //region Properties

    //endregion

    //region Constructors
    public SendPushMessageNote(String channel, String event, List<Integer> accountList, Note note) {
        super(channel, event, accountList);
        this.note = note;
        setMessage();
    }
    //endregion

    //region Methods

    private void setMessage() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put(Settings.KEY_SUBJECT, note.getSubject());
            jsonObject.put(Settings.KEY_TEXT, note.getText());
            jsonObject.put(Settings.KEY_OWNER_ID, note.getOwnerId());
            jsonObject.put(Settings.KEY_ACCOUNT_ID_LIST, AccountIdListToString(accountIdList));

            message = jsonObject.toString();
        } catch (JSONException e) {
            e.printStackTrace();
            message = "Failed to convert MessageNote to JSON string";
        }
    }
}
