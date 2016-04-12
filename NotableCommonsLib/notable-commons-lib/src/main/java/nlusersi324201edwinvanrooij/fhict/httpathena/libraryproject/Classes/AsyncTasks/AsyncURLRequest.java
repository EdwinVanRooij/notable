package nlusersi324201edwinvanrooij.fhict.httpathena.libraryproject.Classes.AsyncTasks;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import nlusersi324201edwinvanrooij.fhict.httpathena.libraryproject.Classes.Interfaces.AsyncResponse;
import nlusersi324201edwinvanrooij.fhict.httpathena.libraryproject.R;

/**
 * Created by Edwin on 1/19/2016
 */
public abstract class AsyncURLRequest extends AsyncTask<Void, Void, Void> {
    //region Fields
    private static final String TAG = "AsyncURLRequest";
    protected Context c;
    protected URL url;
    protected ProgressDialog loading;
    protected boolean progress;
    protected AsyncResponse delegate = null;
    protected String output;
    //endregion

    //region Properties

    //endregion

    //region Constructors

    //endregion

    //region Methods
    @Override
    protected void onPreExecute() {
        try {
            super.onPreExecute();
            if (progress) {

                loading = ProgressDialog.show(c, c.getResources().getString(R.string.waiting_head), c.getResources().getString(R.string.waiting_content), false, false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected Void doInBackground(Void... v) {
        try {
            output = sendRequest(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void v) {
        try {
            if (progress) {
                try {
                    if ((loading != null) && loading.isShowing()) {
                        loading.dismiss();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    loading = null;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            delegate.processFinish(output);
        }
    }

    protected String sendRequest(URL url) throws InterruptedException, IOException {
        StringBuilder result = new StringBuilder();

        HttpURLConnection con = getHttpURLConnection(url);
        assert con != null;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

        String s;
        while ((s = bufferedReader.readLine()) != null) {
            result.append(s);
            result.append("\n");
        }
        con.disconnect();
        bufferedReader.close();
        return result.toString();
    }

    protected HttpURLConnection getHttpURLConnection(URL entries) throws InterruptedException, IOException {
        int retry = 0;
        boolean delay = false;
        int RETRIES = 5;
        do {
            if (delay) {
                int RETRY_DELAY_MS = 1000;
                Thread.sleep(RETRY_DELAY_MS);
            }
            HttpURLConnection connection = (HttpURLConnection) entries.openConnection();
            switch (connection.getResponseCode()) {
                case HttpURLConnection.HTTP_OK:
                    Log.d(TAG, "getHttpURLConnection: **OK**");
                    connection.setConnectTimeout(5000);
                    connection.setReadTimeout(3000);
                    return connection; // **EXIT POINT** fine, go on
                case HttpURLConnection.HTTP_GATEWAY_TIMEOUT:
                    Log.d(TAG, "getHttpURLConnection: **gateway timeout**");
                    break;// retry
                case HttpURLConnection.HTTP_UNAVAILABLE:
                    Log.d(TAG, "getHttpURLConnection: **unavailable**");
                    break;// retry, server is unstable
                default:
                    Log.d(TAG, "getHttpURLConnection: **unknown response code**");
                    break; // abort
            }
            // we did not succeed with connection (or we would have returned the connection).
            connection.disconnect();
            // retry
            retry++;
            Log.d(TAG, "getHttpURLConnection: Failed retry " + retry + ", " + RETRIES);
            delay = true;

        } while (retry < RETRIES);

        Log.d(TAG, "getHttpURLConnection: Aborting download of dataset.");
        return null;
    }
    //endregion

    //region Enums
    public enum queryType {
        Select, Modify
    }
    //endregion
}
