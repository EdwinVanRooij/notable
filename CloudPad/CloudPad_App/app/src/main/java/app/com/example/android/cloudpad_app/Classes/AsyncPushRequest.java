package app.com.example.android.cloudpad_app.Classes;

import android.content.Context;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import app.com.example.android.cloudpad_app.Classes.Physical.PushMessages.SendPushMessage;
import nlusersi324201edwinvanrooij.fhict.httpathena.libraryproject.Classes.AsyncTasks.AsyncURLRequest;
import nlusersi324201edwinvanrooij.fhict.httpathena.libraryproject.Classes.Config;
import nlusersi324201edwinvanrooij.fhict.httpathena.libraryproject.Classes.Interfaces.AsyncResponse;

/**
 * Created by Edwin on 12/18/2015.
 */
public class AsyncPushRequest extends AsyncURLRequest {
    //region Fields

    //endregion

    //region Properties

    //endregion

    //region Constructors
    public AsyncPushRequest(Context c, SendPushMessage pm, AsyncResponse a) throws UnsupportedEncodingException, MalformedURLException {
        this.c = c;
        this.delegate = a;
        String channel = URLEncoder.encode(pm.getChannel(), "UTF-8");
        String event = URLEncoder.encode(pm.getEvent(), "UTF-8");
        String message = URLEncoder.encode(pm.getMessage(), "UTF-8");

        url = new URL(Config.URL_PUSHER + "?channel=" + channel + "&event=" + event + "&message=" + message);
    }
    //endregion

    //region Methods

    //endregion

    //region Enums

    //endregion
}
