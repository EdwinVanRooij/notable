package app.com.example.android.cloudpad_app.classes.physical;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.format.DateFormat;

import java.util.Date;

import nlusersi324201edwinvanrooij.fhict.httpathena.libraryproject.Classes.Handlers.ConvertHandler;

/**
 * Created by Edwin on 11/16/2015.
 */
public class SharedNote extends Note implements Parcelable {
    public static final Parcelable.Creator<SharedNote> CREATOR = new Parcelable.Creator<SharedNote>() {
        public SharedNote createFromParcel(Parcel in) {
            return new SharedNote(in);
        }

        public SharedNote[] newArray(int size) {
            return new SharedNote[size];
        }
    };
    //region Fields
    private final Date last_edited;
    private final String owner;
    //endregion

    //region Properties
    //endregion

    //region Constructors
    public SharedNote(int id, String subject, String text, int ownerId, String owner, Date last_edited) {
        super(id, subject, text, ownerId);
        this.owner = owner;
        this.last_edited = last_edited;
    }
    //endregion

    private SharedNote(Parcel in) {
        super(in.readInt(), in.readString(), in.readString(), in.readInt());
        this.owner = in.readString();
        this.last_edited = ConvertHandler.StringToDate(in.readString());
    }

    public String getLastEditedString() {
        String result;

        if (last_edited == null) {
            result = "-";
        } else {
            result = (String) DateFormat.format("dd-MM-yyyy", last_edited);
        }

        return result;
    }
    //endregion

    public String getOwner() {
        return "~" + owner;
    }

    //region Methods
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(getId());
        out.writeString(getSubject());
        out.writeString(getText());
        out.writeInt(getOwnerId());
        out.writeString(getOwner());
        out.writeString(ConvertHandler.DateToString(last_edited));
    }

    @Override
    public String toString() {
        return "id: " + id + ", subject:" + subject + ", text: " + text + ", lastedited: " + ConvertHandler.DateToString(last_edited) + ", owner; " + owner + ", ownerid: " + ownerId;
    }
    //endregion
}
