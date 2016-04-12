package nlusersi324201edwinvanrooij.fhict.httpathena.cloudpadadmin_app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import nlusersi324201edwinvanrooij.fhict.httpathena.cloudpadadmin_app.Classes.Handlers.ConvertHandler;
import nlusersi324201edwinvanrooij.fhict.httpathena.cloudpadadmin_app.Classes.Handlers.GeneralHandler;
import nlusersi324201edwinvanrooij.fhict.httpathena.cloudpadadmin_app.Classes.Handlers.QueryHandler;
import nlusersi324201edwinvanrooij.fhict.httpathena.libraryproject.Classes.AsyncTasks.AsyncDataRequest;
import nlusersi324201edwinvanrooij.fhict.httpathena.libraryproject.Classes.AsyncTasks.AsyncURLRequest;
import nlusersi324201edwinvanrooij.fhict.httpathena.libraryproject.Classes.Interfaces.AsyncResponse;

public class QueryActivity extends AppCompatActivity {

    //region Fields
    @Bind(R.id.loadView)
    ImageView loadView;
    @Bind(R.id.btnSuggestionOne)
    Button btnSuggestionOne;
    @Bind(R.id.btnSuggestionTwo)
    Button btnSuggestionTwo;
    @Bind(R.id.btnSuggestionThree)
    Button btnSuggestionThree;
    @Bind(R.id.etQueryText)
    EditText etQuery;

    private QueryHandler queryHandler;
    //endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //region Initialize
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query);
        ButterKnife.bind(this);
        queryHandler = new QueryHandler(this);
        setSuggestionsOnButtons();
        //endregion

        //region Listeners
        etQuery.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                if (queryHandler.isEmpty(s.toString())) {
                    queryHandler.setDefaultSuggestions();
                    setSuggestionsOnButtons();
                    return;
                }

                if (!queryHandler.userFinishedTyping(s.toString())) {
                    return;
                }

                queryHandler.insertWords(s.toString());

                String last_word = queryHandler.getLastWord(s.toString());
                if (!queryHandler.wordIsValid(last_word)) {
                    queryHandler.setDefaultSuggestions();
                    setSuggestionsOnButtons();
                    return;
                }

                GeneralHandler.log("Started loading suggestions from DB.");
                loadView.setImageResource(R.drawable.geel_bolletje);
                AsyncResponse a = new AsyncResponse() {
                    @Override
                    public void processFinish(String output) {
                        queryHandler.setSuggestions(ConvertHandler.getSuggestionsFromJSON(output));
                        setSuggestionsOnButtons();

                        GeneralHandler.log("Finished loading suggestions from DB.");
                        loadView.setImageResource(R.drawable.groen_bolletje);
                    }
                };
                queryHandler.getSuggestions(QueryActivity.this, a, last_word, false);
            }

            //region Other TextChanged events
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

            }

            public void afterTextChanged(Editable s) {

            }
            //endregion
        });
        //endregion
    }

    @OnClick(R.id.btnQuery)
    void send() {
        String query = etQuery.getText().toString().trim();

        AsyncResponse a = new AsyncResponse() {
            @Override
            public void processFinish(String output) {
                clearText();
            }
        };
        new AsyncDataRequest(a, QueryActivity.this, AsyncURLRequest.queryType.Modify, query, true).execute();
    }

    //region Send suggestions

    @OnClick(R.id.btnSuggestionOne)
    void sendSuggestionOne(View v) {
        sendSuggestion(1);
    }

    @OnClick(R.id.btnSuggestionTwo)
    void sendSuggestionTwo(View v) {
        sendSuggestion(2);
    }

    @OnClick(R.id.btnSuggestionThree)
    void sendSuggestionThree() {
        sendSuggestion(3);
    }
    private void sendSuggestion(int which) {
        GeneralHandler.log("Pressed suggestion " + which + ".");
        int indexValue = which - 1;
        String toInsert = etQuery.getText() + queryHandler.getSuggestions()[indexValue];
        sendSuggestion(toInsert);
    }
    //endregion

    public void clearQuery(View v) {
        GeneralHandler.log("Pressed clear query.");
        clearText();
    }

    private void clearText() {
        GeneralHandler.log("Clearing text.");
        etQuery.setText("");
        setSuggestionsOnButtons();
    }

    private void setSuggestionsOnButtons() {
        GeneralHandler.log("Setting suggestions on buttons.");
        btnSuggestionOne.setText(queryHandler.getSuggestions()[0]);
        btnSuggestionTwo.setText(queryHandler.getSuggestions()[1]);
        btnSuggestionThree.setText(queryHandler.getSuggestions()[2]);
    }

    private void sendSuggestion(String p_input) {
        GeneralHandler.log("Sending suggestion to EditText.");
        String input = p_input + " ";
        etQuery.setText(input);
        etQuery.setSelection(etQuery.getText().length());
    }
}
