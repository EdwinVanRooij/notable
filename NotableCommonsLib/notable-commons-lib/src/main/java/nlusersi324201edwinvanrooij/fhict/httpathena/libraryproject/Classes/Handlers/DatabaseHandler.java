package nlusersi324201edwinvanrooij.fhict.httpathena.libraryproject.Classes.Handlers;

import android.content.Context;

import nlusersi324201edwinvanrooij.fhict.httpathena.libraryproject.Classes.AsyncTasks.AsyncDataRequest;
import nlusersi324201edwinvanrooij.fhict.httpathena.libraryproject.Classes.Interfaces.AsyncResponse;

/**
 * Created by Edwin on 1/28/2016
 */
public class DatabaseHandler {
    //region Fields
    private Context c;

    private String query;
    private AsyncDataRequest.queryType type;
    private AsyncResponse response;
    private boolean progressbar;

    //region Properties

    //endregion

    //region Constructors
    public DatabaseHandler(Context c, String query, AsyncDataRequest.queryType type) {
        this.c = c;
        this.query = query;
        this.type = type;
    }

    public DatabaseHandler withAsyncResponse(AsyncResponse response) {
        this.response = response;
        return this;
    }

    public DatabaseHandler withProgressBar() {
        progressbar = true;
        return this;
    }
    public DatabaseHandler withProgressBar(boolean bool) {
        progressbar = bool;
        return this;
    }
    //endregion

    //region Methods
    public void execute() {
        if (response == null) {
            response = new AsyncResponse() {
                @Override
                public void processFinish(String output) {
                    //do nothing with result
                }
            };
        }
        new AsyncDataRequest(response, c, type, query, progressbar).execute();
    }
    //endregion
}
