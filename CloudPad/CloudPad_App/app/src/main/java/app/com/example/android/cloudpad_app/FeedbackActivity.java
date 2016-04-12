package app.com.example.android.cloudpad_app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

import app.com.example.android.cloudpad_app.Classes.Handlers.FeedbackHandler;
import app.com.example.android.cloudpad_app.Classes.Physical.FeedbackItem;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import nlusersi324201edwinvanrooij.fhict.httpathena.libraryproject.Classes.Interfaces.AsyncResponse;
import nlusersi324201edwinvanrooij.fhict.httpathena.libraryproject.Classes.Physical.Account;

public class FeedbackActivity extends AppCompatActivity {

    //region Fields
    @Bind(R.id.spinner)
    Spinner s;
    @Bind(R.id.etDescription)
    EditText etDescription;

    private FeedbackHandler feedbackHandler;
    private Account thisAccount;

    //endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        ButterKnife.bind(this);

        feedbackHandler = new FeedbackHandler(this);
        thisAccount = getIntent().getParcelableExtra("account");

        ArrayList<String> spinnerArray = new ArrayList<>();
        spinnerArray.add(getResources().getString(R.string.feedback_bug));
        spinnerArray.add(getResources().getString(R.string.feedback_suggestion));

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, spinnerArray);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s.setAdapter(adapter);
    }

    @OnClick(R.id.btnSend)
    void onSendClick() {
        try {
            int account_id = thisAccount.getId();
            FeedbackItem.FeedbackType type = feedbackHandler.getFeedbackType(s.getSelectedItem().toString());
            String description = etDescription.getText().toString().trim();

            AsyncResponse a = new AsyncResponse() {
                @Override
                public void processFinish(String output) {

                }
            };
            FeedbackItem f = new FeedbackItem(account_id, type, description);
            feedbackHandler.sendFeedback(a, f);

            finish();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
