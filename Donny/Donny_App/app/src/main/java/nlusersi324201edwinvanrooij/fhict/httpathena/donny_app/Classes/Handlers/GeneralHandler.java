package nlusersi324201edwinvanrooij.fhict.httpathena.donny_app.Classes.Handlers;

import android.util.Log;

import java.util.Random;

import nlusersi324201edwinvanrooij.fhict.httpathena.donny_app.Classes.Config;

/**
 * Created by Edwin on 11/9/2015.
 */
public class GeneralHandler {
    public static void log(String input) {
        Log.d(Config.LOG_TAG, input);
    }

    public static void logError(String input) {
        Log.e(Config.LOG_TAG, "Error: " + input);
    }

    public static void sleep(int extra_delay_in_ms) throws InterruptedException {
        int min = extra_delay_in_ms - 300;
        int max = extra_delay_in_ms + 300;

        Random r = new Random();
        int sleep_time = r.nextInt(max - min + 1) + min;
        Thread.sleep(sleep_time);
        GeneralHandler.log("Slept " + sleep_time + "ms.");
    }
}
