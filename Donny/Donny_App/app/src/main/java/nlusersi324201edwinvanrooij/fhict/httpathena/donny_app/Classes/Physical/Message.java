package nlusersi324201edwinvanrooij.fhict.httpathena.donny_app.Classes.Physical;

/**
 * Created by Technovibe on 17-04-2015.
 */
public class Message {
    private boolean senderIsMe;
    private String message;

    public boolean senderIsMe() {
        return senderIsMe;
    }

    public String getMessage() {
        return message;
    }

    public Message(boolean senderIsMe, String message) {
        this.senderIsMe = senderIsMe;
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
