package nlusersi324201edwinvanrooij.fhict.httpathena.cloudpadadmin_app.Classes.Handlers;

import android.content.Context;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

import nlusersi324201edwinvanrooij.fhict.httpathena.cloudpadadmin_app.Classes.Config;

/**
 * Created by Edwin on 11/16/2015.
 */
public class GeneralHandler {

    public static boolean HandlePHPOutput(Context c, String output, String negative_message) {
        switch (output.substring(0, 1)) {
            case Config.POSITIVE:
                Log.d(Config.LOG_TAG, "Gelukt.");
                return true;
            case Config.NEGATIVE:
                Log.d(Config.LOG_TAG, negative_message);
                return false;
            default:
                Log.d(Config.LOG_TAG, "Error in HandlePHPOutput, output: " + output);
                return false;
        }
    }

    private static Date getDateFromDatabase(String date_from_db) throws ParseException {
        if (Objects.equals(date_from_db, "null") || Objects.equals(date_from_db, "")) {
            return null;
        } else {
            String format = "yyyy-MM-dd hh:mm:ss";
            SimpleDateFormat sdf = new SimpleDateFormat(format);

            return sdf.parse(date_from_db);
        }
    }

    public static void log(String input) {
        Log.d(Config.LOG_TAG, input);
    }

    public static void logError(String input) {
        Log.d(Config.LOG_TAG, "Error: " + input);
    }
}
