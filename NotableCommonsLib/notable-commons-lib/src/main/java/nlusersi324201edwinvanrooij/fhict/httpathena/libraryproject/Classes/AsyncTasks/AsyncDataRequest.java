package nlusersi324201edwinvanrooij.fhict.httpathena.libraryproject.classes.asynctasks;

import android.content.Context;
import android.util.Log;

import java.net.URL;
import java.net.URLEncoder;

import nlusersi324201edwinvanrooij.fhict.httpathena.libraryproject.classes.Config;
import nlusersi324201edwinvanrooij.fhict.httpathena.libraryproject.classes.interfaces.AsyncResponse;

/**
 * Created by Edwin on 12/18/2015.
 */
public class AsyncDataRequest extends AsyncURLRequest {

    //region Fields
    private static final String TAG = "AsyncPushRequest";
    //endregion

    public AsyncDataRequest(AsyncResponse asyncResponse, Context c, queryType type, String raw_query, boolean progress) {
        try {
            this.delegate = asyncResponse;
            this.progress = progress;
            this.c = c;
            String q = URLEncoder.encode(raw_query, "UTF-8");
            switch (type) {
                case Select:
                    url = new URL(Config.URL_SELECT_QUERY + "?query=" + q);
                    break;
                case Modify:
                    url = new URL(Config.URL_MODIFY_QUERY + "?query=" + q);
                    break;
                default:
                    Log.e(TAG, "DataRequest: no QueryType found", new Exception());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // TODO: 2/6/2016 make an option for sending multiple url requests at the same time
}
