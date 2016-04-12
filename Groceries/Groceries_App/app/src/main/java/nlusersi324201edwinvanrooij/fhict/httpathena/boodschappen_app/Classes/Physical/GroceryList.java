package nlusersi324201edwinvanrooij.fhict.httpathena.boodschappen_app.Classes.Physical;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Edwin on 1/13/2016.
 */
public class GroceryList implements Parcelable {
    public static final Creator<GroceryList> CREATOR = new Creator<GroceryList>() {
        public GroceryList createFromParcel(Parcel in) {
            return new GroceryList(in);
        }

        public GroceryList[] newArray(int size) {
            return new GroceryList[size];
        }
    };

    //region Fields
    private int id;
    private String title;
    //endregion

    //region Properties

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    //endregion

    //region Constructors

    public GroceryList(int id, String title) {
        this.id = id;
        this.title = title;
    }

    private GroceryList(Parcel in) {
        id = in.readInt();
        title = in.readString();
    }
    //endregion

    //region Methods
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(getId());
        out.writeString(getTitle());
    }

    @Override
    public String toString() {
        return "Omschrijving per lijst is in de maak.";
    }
    //endregion

    //region Enums

    //endregion
}
