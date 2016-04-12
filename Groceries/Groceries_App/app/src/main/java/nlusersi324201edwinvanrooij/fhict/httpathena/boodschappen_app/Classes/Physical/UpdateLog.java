package nlusersi324201edwinvanrooij.fhict.httpathena.boodschappen_app.Classes.Physical;

import android.text.format.DateFormat;

import java.util.Date;

/**
 * Created by Edwin on 11/3/2015.
 */
public class UpdateLog {
    //region Fields
    private final double version;
    private final String description;
    private final Date date;
    //endregion

    //region Constructors
    public UpdateLog(double version, String description, Date date) {
        this.version = version;
        this.description = description;
        this.date = date;
    }

    //region Properties
    public double getVersion() {
        return version;
    }

    public String getDescription() {
        return description;
    }
    //endregion

    public String getDateString() {
        String result;

            result = (String) DateFormat.format("dd-MM-yyyy", date);

        return result;
    }
    //endregion
}
