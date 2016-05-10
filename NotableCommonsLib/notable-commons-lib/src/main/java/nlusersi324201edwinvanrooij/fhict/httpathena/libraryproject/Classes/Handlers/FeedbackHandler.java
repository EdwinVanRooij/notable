package nlusersi324201edwinvanrooij.fhict.httpathena.libraryproject.classes.handlers;

import android.content.Context;

import nlusersi324201edwinvanrooij.fhict.httpathena.libraryproject.classes.asynctasks.AsyncDataRequest;
import nlusersi324201edwinvanrooij.fhict.httpathena.libraryproject.classes.asynctasks.AsyncURLRequest;
import nlusersi324201edwinvanrooij.fhict.httpathena.libraryproject.classes.interfaces.AsyncResponse;
import nlusersi324201edwinvanrooij.fhict.httpathena.libraryproject.classes.physical.FeedbackItem;

/**
 * Created by Edwin on 11/23/2015.
 */
public class FeedbackHandler {
    //region Fields
    private final Context c;
    //endregion

    //region Properties

    //endregion

    //region Constructors
    public FeedbackHandler(Context c) {
        this.c = c;
    }
    //endregion

    //region Methods
    public void sendFeedback(FeedbackItem f) throws Exception {
        AsyncResponse a = new AsyncResponse() {

            @Override
            public void processFinish(String output) {

            }
        };
        String query = "insert into feedback(account_id, type, description) values(" + f.getAccountId()
                + ", '" + f.getType() + "', '" + f.getDescription() + "')";
        new AsyncDataRequest(a, c, AsyncURLRequest.queryType.Modify, query, true).execute();
    }

    public FeedbackItem.FeedbackType getFeedbackType(String input) {
        if (input.equals("Foutmelding")) {
            return FeedbackItem.FeedbackType.Bug;
        } else if (input.equals("Suggestie")) {
            return FeedbackItem.FeedbackType.Suggestion;
        } else {
            return null;
        }
    }
    //endregion
}
