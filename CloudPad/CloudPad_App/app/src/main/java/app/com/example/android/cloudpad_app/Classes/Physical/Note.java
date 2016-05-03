package app.com.example.android.cloudpad_app.classes.physical;

import android.os.Parcel;
import android.os.Parcelable;

import nlusersi324201edwinvanrooij.fhict.httpathena.libraryproject.Classes.Config;

/**
 * Created by Edwin on 11/3/2015.
 */
public class Note implements Parcelable {
    //region Fields
    int id;
    String subject;
    String text;
    final int ownerId;
    //endregion

    //region Constructors
    public Note(String subject, String text, int ownerId) {
        setSubject(subject);
        setText(text);
        this.ownerId = ownerId;
    }

    Note(int id, String subject, String text, int ownerId) {
        this.id = id;
        setSubject(subject);
        setText(text);
        this.ownerId = ownerId;
    }

    protected Note(Parcel in) {
        id = in.readInt();
        subject = in.readString();
        text = in.readString();
        ownerId = in.readInt();
    }

    //region Properties
    public int getId() {
        return id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        if (subject == null || subject.equals("")) {
            this.subject = Config.DEFAULT_SUBJECT;
        } else {
            this.subject = subject;
        }
    }
    //endregion

    public String getText() {
        return text;
    }

    public void setText(String text) {
        if (text == null || text.equals("")) {
            this.text = Config.DEFAULT_TEXT;
        } else {
            this.text = text;
        }
    }

    public int getOwnerId() {
        return ownerId;
    }
    //endregion

    //region Methods
    public String getNewNoteNotificationContent() {
        String subject = this.subject;
        if (subject.length() == 0) {
            subject = "onbekend";
        }
        String text = this.text;
        if (text.length() == 0) {
            text = "leeg";
        }
        int amount_of_chars;
        if (text.length() > 10) {
            amount_of_chars = 10;
            text = text.substring(0, amount_of_chars) + "...";
        }
        return "bekijk " + subject + ": " + text;
    }

    //region Parcelable, only actually used in implementations of Note
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(subject);
        dest.writeString(text);
        dest.writeInt(ownerId);
    }

    public static final Creator<Note> CREATOR = new Creator<Note>() {
        @Override
        public Note createFromParcel(Parcel in) {
            return new Note(in);
        }

        @Override
        public Note[] newArray(int size) {
            return new Note[size];
        }
    };
    //endregion
}