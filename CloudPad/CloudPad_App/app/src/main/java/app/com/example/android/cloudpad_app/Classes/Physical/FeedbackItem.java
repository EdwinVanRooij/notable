package app.com.example.android.cloudpad_app.Classes.Physical;

/**
 * Created by Edwin on 11/23/2015.
 */
public class FeedbackItem {
    private final FeedbackType type;
    //region Fields
    private final int accountId;
    private final String description;
    //endregion

    //region Constructors
    public FeedbackItem(int accountId, FeedbackType type, String description) {
        this.accountId = accountId;
        this.type = type;
        this.description = description;
    }

    //region Properties
    public int getAccountId() {
        return accountId;
    }

    public FeedbackType getType() {
        return type;
    }
    //endregion

    public String getDescription() {
        return description;
    }
    //endregion

    //region Methods

    //endregion
    public enum FeedbackType {
        Suggestion, Bug
    }
}
