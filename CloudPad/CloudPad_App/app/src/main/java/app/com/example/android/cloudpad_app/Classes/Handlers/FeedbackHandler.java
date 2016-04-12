package app.com.example.android.cloudpad_app.Classes.Handlers;

import android.content.Context;
import android.util.Log;

import java.util.Objects;

import nlusersi324201edwinvanrooij.fhict.httpathena.libraryproject.Classes.Handlers.DatabaseHandler;
import app.com.example.android.cloudpad_app.Classes.Physical.FeedbackItem;
import nlusersi324201edwinvanrooij.fhict.httpathena.libraryproject.Classes.AsyncTasks.AsyncURLRequest;
import nlusersi324201edwinvanrooij.fhict.httpathena.libraryproject.Classes.Interfaces.AsyncResponse;

/**
 * Created by Edwin on 11/23/2015.
 */
public class FeedbackHandler {
    //region Fields
    private static final String TAG = "FeedbackHandler";
    private Context c;
    //endregion

    //region Properties

    //endregion

    //region Constructors
    public FeedbackHandler(Context c) {
        this.c = c;
    }
    //endregion

    //region Methods
    public void sendFeedback(AsyncResponse a, FeedbackItem item) throws Exception {
        String query = "insert into feedback(account_id, type, description) values(" + item.getAccountId()
                + ", '" + item.getType() + "', '" + item.getDescription() + "')";
        new DatabaseHandler(c, query, AsyncURLRequest.queryType.Modify).withAsyncResponse(a).withProgressBar().execute();
    }

    public FeedbackItem.FeedbackType getFeedbackType(String input) {
        if (Objects.equals(input, "Foutmelding")) {
            return FeedbackItem.FeedbackType.Bug;
        } else if (Objects.equals(input, "Suggestie")) {
            return FeedbackItem.FeedbackType.Suggestion;
        } else {
            Log.d(TAG, "getFeedbackType: Error");
            return null;
        }
    }
    //endregion
}
