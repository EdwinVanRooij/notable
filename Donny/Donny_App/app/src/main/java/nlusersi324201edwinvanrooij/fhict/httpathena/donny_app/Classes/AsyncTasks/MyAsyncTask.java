package nlusersi324201edwinvanrooij.fhict.httpathena.donny_app.Classes.AsyncTasks;

import android.os.AsyncTask;
import android.widget.TextView;

import java.net.URL;
import java.net.URLEncoder;
import java.util.Random;

import nlusersi324201edwinvanrooij.fhict.httpathena.donny_app.Classes.Config;
import nlusersi324201edwinvanrooij.fhict.httpathena.donny_app.Classes.Handlers.GeneralHandler;
import nlusersi324201edwinvanrooij.fhict.httpathena.donny_app.Classes.Handlers.ReceiveHandler;
import nlusersi324201edwinvanrooij.fhict.httpathena.donny_app.Classes.Handlers.SendHandler;
import nlusersi324201edwinvanrooij.fhict.httpathena.donny_app.Classes.Interfaces.AsyncResponse;
import nlusersi324201edwinvanrooij.fhict.httpathena.donny_app.Classes.Runnables.MyRunnable;


/**
 * Created by Edwin on 11/11/2015.
 */
public class MyAsyncTask extends AsyncTask<Void, Void, String> {

    //region Fields
    public AsyncResponse delegate = null;
    private Exception exception;
    private MyRunnable myRunnable;
    private int extra_delay_in_ms;
    //endregion

    public MyAsyncTask(AsyncResponse asyncResponse, MyRunnable myRunnable, int extra_delay_in_ms) {
        try {
            delegate = asyncResponse;
            this.myRunnable = myRunnable;
            this.extra_delay_in_ms = extra_delay_in_ms;
        } catch (Exception e) {
            exception = e;
        }
    }

    @Override
    protected void onPreExecute() {
        try {
            super.onPreExecute();
            myRunnable.runPre();
        } catch (Exception e) {
            exception = e;
        }
    }

    @Override
    protected void onPostExecute(String output) {
        try {
            myRunnable.runPost();
        } catch (Exception e) {
            exception = e;
        } finally {
            try {
                delegate.processFinish(output, exception);
            } catch (Exception e) {
                exception = e;
            }
        }
    }

    @Override
    protected String doInBackground(Void... v) {
        try {
            GeneralHandler.sleep(extra_delay_in_ms);
            return myRunnable.getOutput();
//            return sendHandler.getSentence(receiveHandler.getSentence(incoming_raw_sentence)).toString();
        } catch (Exception e) {
            exception = e;
            GeneralHandler.logError("Error in MyAsyncTask, myRunnable.getOutput() didn't work");
            return "Error in MyAsyncTask, myRunnable.getOutput() didn't work; returnmsg";
        }
    }

    //region Etc methods

    //endregion
}
