package nlusersi324201edwinvanrooij.fhict.httpathena.donny_app.Classes;

import android.content.Context;

import nlusersi324201edwinvanrooij.fhict.httpathena.donny_app.R;

/**
 * Created by Edwin on 11/3/2015.
 */
public class Strings {
    //region Fields
    public static String AppName;
    public static String Typing;
    public static String Loading;
    public static String Waiting;
    public static String Adding;
    public static String Saving;
    public static String Deleting;
    public static String Positive;
    public static String Negative;
    public static String OK;

    //Exceptions
    public static String ExceptionNoInternetConnection;
    //endregion

    //region Methods
    public static void Initialize(Context c) {
        AppName = c.getResources().getString(R.string.app_name);
        Typing = c.getResources().getString(R.string.main_tv_typing);
        Loading = c.getResources().getString(R.string.loading);
        Waiting = c.getResources().getString(R.string.waiting);
        Adding = c.getResources().getString(R.string.adding);
        Saving = c.getResources().getString(R.string.saving);
        Deleting = c.getResources().getString(R.string.deleting);
        Negative = c.getResources().getString(R.string.negative);
        Positive = c.getResources().getString(R.string.positive);
        OK = c.getResources().getString(R.string.ok);

        //Exceptions
        ExceptionNoInternetConnection = c.getResources().getString(R.string.exception_no_internet_connection);
    }
    //endregion
}
