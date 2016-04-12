package nlusersi324201edwinvanrooij.fhict.httpathena.donny_app.Classes;

/**
 * Created by Edwin on 10/27/2015.
 */
public class Config {
    //Etc
    public static final String LOG_TAG = "My log";
    //region PHP script URLs
    private static final String default_url = "http://athena.fhict.nl/users/i324201/dotnet/dotnet1/php/";
    //token meegeven & terugkrijgen van server
    public static final String URL_SELECT_QUERY = default_url + "selectQuery.php";
    public static final String URL_MODIFY_QUERY = default_url + "modifyQuery.php";

    public static final int default_extra_delay_in_ms = 400;
}