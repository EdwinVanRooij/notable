package nlusersi324201edwinvanrooij.fhict.httpathena.cloudpadadmin_app.Classes;

/**
 * Created by Edwin on 10/27/2015.
 */
public class Config {
    public static final String POSITIVE = "1";
    public static final String NEGATIVE = "0";
    public static final String LOG_TAG = "My log";
    //region PHP script URLs
    private static final String default_url = "http://athena.fhict.nl/users/i324201/dotnet/dotnet1/php/";
    public static final String URL_SELECT_QUERY = default_url + "selectQuery.php";
    public static final String URL_MODIFY_QUERY = default_url + "modifyQuery.php";
}