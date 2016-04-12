package nlusersi324201edwinvanrooij.fhict.httpathena.donny_app.Classes.Runnables;

import android.view.View;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import nlusersi324201edwinvanrooij.fhict.httpathena.donny_app.Classes.Config;
import nlusersi324201edwinvanrooij.fhict.httpathena.donny_app.Classes.Handlers.GeneralHandler;

/**
 * Created by Edwin on 12/12/2015.
 */
public class SendRequest extends MyRunnable {
    //region Fields
    private URL url;
    private String result;
    private TextView tvSpeech;
    private String content;
    //endregion

    //region Properties
    @Override
    public String getOutput() throws Exception {
        if (exception != null) {
            throw exception;
        }
        run();
        return result;
    }
    //endregion

    //region Constructors
    public SendRequest(TextView tvSpeech, QueryType type, String content, String query) {
        try {
            String q;
            switch (type) {
                case Select:
                    q = URLEncoder.encode(query, "UTF-8");
                    this.url = new URL(Config.URL_SELECT_QUERY + "?query=" + q);
                    break;
                case Modify:
                    q = URLEncoder.encode(query, "UTF-8");
                    this.url = new URL(Config.URL_MODIFY_QUERY + "?query=" + q);
                    break;
                default:
                    GeneralHandler.logError("Could not determine the QueryType.");
            }
            this.tvSpeech = tvSpeech;
            this.content = content;
        } catch (MalformedURLException | UnsupportedEncodingException e) {
            exception = e;
        }
    }
    //endregion

    //region Methods
    @Override
    public void runPre() {
        tvSpeech.setText(content);
        tvSpeech.setVisibility(View.VISIBLE);
    }

    @Override
    public void run() {
        try {
            result = sendRequest(url);
        } catch (InterruptedException | IOException e) {
            exception = e;
        }
    }

    @Override
    public void runPost() {
        try {
            tvSpeech.setVisibility(View.INVISIBLE);
        } catch (Exception e) {
            exception = e;
        }
    }

    public String sendRequest(URL url) throws InterruptedException, IOException {
        StringBuilder result = new StringBuilder();

        HttpURLConnection con = getHttpURLConnection(url);
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

    public HttpURLConnection getHttpURLConnection(URL entries) throws InterruptedException, IOException {
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
                    GeneralHandler.log(entries + " **OK**");
                    connection.setConnectTimeout(5000);
                    connection.setReadTimeout(3000);
                    return connection; // **EXIT POINT** fine, go on
                case HttpURLConnection.HTTP_GATEWAY_TIMEOUT:
                    GeneralHandler.log(entries + " **gateway timeout**");
                    break;// retry
                case HttpURLConnection.HTTP_UNAVAILABLE:
                    GeneralHandler.log(entries + "**unavailable**");
                    break;// retry, server is unstable
                default:
                    GeneralHandler.log(entries + " **unknown response code**.");
                    break; // abort
            }
            // we did not succeed with connection (or we would have returned the connection).
            connection.disconnect();
            // retry
            retry++;
            GeneralHandler.logError("Failed retry " + retry + "/" + RETRIES);
            delay = true;

        } while (retry < RETRIES);

        GeneralHandler.logError("Aborting download of dataset.");
        return null;
    }
    //endregion

    //region Enums
    public enum QueryType {
        Select, Modify
    }
    //endregion
}
