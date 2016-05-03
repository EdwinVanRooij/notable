package app.com.example.android.cloudpad_app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import java.util.List;

import app.com.example.android.cloudpad_app.classes.dialogs.ListViewDialog;
import app.com.example.android.cloudpad_app.utils.GeneralHandler;
import app.com.example.android.cloudpad_app.classes.handlers.NoteHandler;
import app.com.example.android.cloudpad_app.classes.physical.Note;
import app.com.example.android.cloudpad_app.classes.physical.SharedNote;
import butterknife.Bind;
import butterknife.ButterKnife;
import nlusersi324201edwinvanrooij.fhict.httpathena.libraryproject.Classes.AsyncTasks.AsyncURLRequest;
import nlusersi324201edwinvanrooij.fhict.httpathena.libraryproject.Classes.Config;
import nlusersi324201edwinvanrooij.fhict.httpathena.libraryproject.Classes.Handlers.DatabaseHandler;
import nlusersi324201edwinvanrooij.fhict.httpathena.libraryproject.Classes.Interfaces.AsyncResponse;
import nlusersi324201edwinvanrooij.fhict.httpathena.libraryproject.Classes.Physical.Account;

public class EditNoteActivity extends AppCompatActivity {

    //region Fields
    @Bind(R.id.etSubject)
    EditText etSubject;
    @Bind(R.id.etText)
    EditText etText;

    private Note thisNote;
    private Account thisAccount;

    private static final String TAG = "EditNoteActivity";

    private NoteHandler noteHandler;
    //endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_edit_note);
            ButterKnife.bind(this);

            thisNote = getIntent().getParcelableExtra(Config.KEY_NOTE);
            thisAccount = getIntent().getParcelableExtra(Config.KEY_ACCOUNT);

            noteHandler = new NoteHandler(this, thisAccount);

            etSubject.setText(thisNote.getSubject());
            etText.setText(thisNote.getText());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean noteHasChanged() {
        return !(etSubject.getText().toString().trim().equals(thisNote.getSubject())
                && etText.getText().toString().trim().equals(thisNote.getText()));
    }

    @Override
    protected void onPause() {
        super.onPause();
        try {
            if (!noteHasChanged()) {
                return;
            }

            final String subject = etSubject.getText().toString().trim();
            final String text = etText.getText().toString().trim();
            thisNote.setSubject(subject);
            thisNote.setText(text);

            try {
                AsyncResponse a = new AsyncResponse() {
                    @Override
                    public void processFinish(String output) {
                        Log.d(TAG, "processFinish: Finished process updatenote");
                    }
                };
                String query = "update note set subject = '" + thisNote.getSubject() + "', text ='" + thisNote.getText() + "' where id = " + thisNote.getId();
                new DatabaseHandler(this, query, AsyncURLRequest.queryType.Modify).withProgressBar().withAsyncResponse(a).execute();
            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showSharedWith() {
        try {
            AsyncResponse a = new AsyncResponse() {
                @Override
                public void processFinish(String output) {
                    try {
                        List<Account> accounts = NoteHandler.getAccountsFromJSON(output);
                        new ListViewDialog(EditNoteActivity.this, getLayoutInflater(), GeneralHandler.convertInfoAccountsToAdapterItems(accounts)).show();
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
            };

            String query = "select a.id, a.username, a.email, a.password, a.creation, a.blocked from account a, share an, note n where a.id = an.account_id and an.note_id = n.id and n.id = " + thisNote.getId() + " and a.id <> " + thisAccount.getId();
            new DatabaseHandler(this, query, AsyncURLRequest.queryType.Select).withProgressBar().withAsyncResponse(a).execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //region Actionbar
        int id = item.getItemId();

        switch (id) {
            case R.id.action_shared_with:
                showSharedWith();
                return true;
        }

        return super.onOptionsItemSelected(item);
        //endregion
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //region Initialize actionbar
        if (thisNote instanceof SharedNote) {
            getMenuInflater().inflate(R.menu.menu_note, menu);
            return true;
        }
        return true;
        //endregion
    }
}