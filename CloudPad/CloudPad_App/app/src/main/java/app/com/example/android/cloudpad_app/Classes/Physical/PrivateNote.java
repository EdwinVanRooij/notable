package app.com.example.android.cloudpad_app.classes.physical;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Edwin on 11/16/2015.
 */
public class PrivateNote extends Note implements Parcelable {
    //region Fields
    public static final Parcelable.Creator<PrivateNote> CREATOR = new Parcelable.Creator<PrivateNote>() {
        public PrivateNote createFromParcel(Parcel in) {
            return new PrivateNote(in);
        }

        public PrivateNote[] newArray(int size) {
            return new PrivateNote[size];
        }
    };
    //endregion

    //region Properties

    //endregion

    //region Constructors
    public PrivateNote(int id, String subject, String text, int owner_id) {
        super(id, subject, text, owner_id);
    }

    private PrivateNote(Parcel in) {
        super(in.readInt(), in.readString(), in.readString(), in.readInt());
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
        out.writeString(getSubject());
        out.writeString(getText());
        out.writeInt(getOwnerId());
    }
    //endregion
}
