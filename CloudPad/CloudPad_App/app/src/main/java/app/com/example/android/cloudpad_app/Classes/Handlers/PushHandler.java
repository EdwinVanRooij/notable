package app.com.example.android.cloudpad_app.Classes.Handlers;

import android.content.Context;
import android.util.Log;

import com.pusher.client.Pusher;
import com.pusher.client.channel.Channel;
import com.pusher.client.channel.SubscriptionEventListener;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;

import app.com.example.android.cloudpad_app.Classes.AsyncPushRequest;
import app.com.example.android.cloudpad_app.Classes.Physical.PushMessages.PushMessageEnums;
import app.com.example.android.cloudpad_app.Classes.Physical.PushMessages.ReceivePushMessage;
import app.com.example.android.cloudpad_app.Classes.Physical.PushMessages.ReceivePushMessageNote;
import app.com.example.android.cloudpad_app.Classes.Physical.PushMessages.SendPushMessage;
import nlusersi324201edwinvanrooij.fhict.httpathena.libraryproject.Classes.Config;
import nlusersi324201edwinvanrooij.fhict.httpathena.libraryproject.Classes.Interfaces.AsyncResponse;
import nlusersi324201edwinvanrooij.fhict.httpathena.libraryproject.Classes.Physical.Account;

/**
 * Created by Edwin on 11/12/2015.
 */
public class PushHandler {
    //region Fields
    private static final String TAG = "PushHandler";
    private final Pusher pusher;
    private final Context c;
    private Account thisAccount;
    //endregion

    //region Constructors
    public PushHandler(Context context, Account thisAccount) {
        this.c = context;
        this.thisAccount = thisAccount;
        pusher = new Pusher(Config.PUSHER_KEY);
    }

    public PushHandler(Context context) {
        this.c = context;
        pusher = new Pusher(Config.PUSHER_KEY);
    }
    //endregion

    //region Methods
    public void connectToAllChannels() {
        connect(Config.KEY_CHANNEL_NOTES, Config.KEY_EVENTS_NOTES);
        String events = "";
        for (String s :
                Config.KEY_EVENTS_NOTES) {
            events += s + ", ";
        }
        Log.d(TAG, "connectToAllChannels: listening on channel <"+ Config.KEY_CHANNEL_NOTES + "> to events <" + events+ ">");
    }

    private void connect(String channel_string, String[] events_array) {
        Channel channel = pusher.subscribe(channel_string);
        for (final String event_string :
                events_array) {
            channel.bind(event_string, new SubscriptionEventListener() {
                        @Override
                        public void onEvent(String channelName, String eventName, String json_string) {
                            ReceivePushMessage rpm;
                            PushMessageEnums.PushMessageType pmt = PushMessageEnums.PushMessageType.valueOf(channelName);
                            switch (pmt) {
                                case Note:
                                    PushMessageEnums.NoteEvent noteEvent = PushMessageEnums.NoteEvent.valueOf(eventName);
                                    rpm = new ReceivePushMessageNote(c, thisAccount, json_string, noteEvent);
                                    rpm.doStuff();
                                    break;
                                default:
                                    Log.d(TAG, "onEvent: Could not figure out what kind of PushMessageType we're receiving.");
                            }
                        }
                    }

            );
            pusher.connect();
        }
    }

    public void push(AsyncResponse a, SendPushMessage pm) throws UnsupportedEncodingException, MalformedURLException {
        new AsyncPushRequest(c, pm, a).execute();
    }
    //endregion
}
