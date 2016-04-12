package app.com.example.android.cloudpad_app;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import com.mikepenz.fastadapter.FastAdapter;
import com.mikepenz.fastadapter.IAdapter;
import com.mikepenz.fastadapter.IItem;
import com.mikepenz.fastadapter.adapters.ItemAdapter;
import com.mikepenz.fastadapter.items.AbstractItem;
import com.mikepenz.itemanimators.AlphaInAnimator;

import java.util.ArrayList;
import java.util.List;

import app.com.example.android.cloudpad_app.Classes.AdapterItems.AdapterItemAccount;
import nlusersi324201edwinvanrooij.fhict.httpathena.libraryproject.Classes.Handlers.DatabaseHandler;
import nlusersi324201edwinvanrooij.fhict.httpathena.libraryproject.Classes.Handlers.AccountHandler;
import app.com.example.android.cloudpad_app.Classes.Handlers.GeneralHandler;
import app.com.example.android.cloudpad_app.Classes.Handlers.NoteHandler;
import app.com.example.android.cloudpad_app.Classes.Handlers.PushHandler;
import app.com.example.android.cloudpad_app.Classes.Physical.Notes.Note;
import app.com.example.android.cloudpad_app.Classes.Physical.PushMessages.SendPushMessageNote;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import nlusersi324201edwinvanrooij.fhict.httpathena.libraryproject.Classes.AsyncTasks.AsyncURLRequest;
import nlusersi324201edwinvanrooij.fhict.httpathena.libraryproject.Classes.Config;
import nlusersi324201edwinvanrooij.fhict.httpathena.libraryproject.Classes.Interfaces.AsyncResponse;
import nlusersi324201edwinvanrooij.fhict.httpathena.libraryproject.Classes.Physical.Account;

public class NewNoteActivity extends AppCompatActivity {

    //region Fields
    private static final String TAG = "NewNoteActivity";

    @Bind(R.id.etSubject)
    EditText etSubject;
    @Bind(R.id.etText)
    EditText etText;
    @Bind(R.id.cbShare)
    CheckBox cbShare;

    private String subject;
    private String text;

    private List<Account> selectedAccounts;

    private List<AbstractItem> adapterItems;
    ItemAdapter<AbstractItem> itemAdapter;

    private Account thisAccount;
    private NoteHandler noteHandler;
    private AccountHandler accountHandler;
    //endregion

    //region Constructors
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_new_note);
            ButterKnife.bind(this);

            //region Initializing views
            thisAccount = getIntent().getParcelableExtra(Config.KEY_ACCOUNT);
            noteHandler = new NoteHandler(this, thisAccount);
            accountHandler = new AccountHandler(this, thisAccount);
            //endregion

        } catch (Exception e) {
                e.printStackTrace();
        }
    }
    //endregion

    //region Methods
    @OnClick(R.id.btnAdd)
    void onBtnAddClick() {
        try {
            subject = etSubject.getText().toString().trim();
            text = etText.getText().toString().trim();
            if (cbShare.isChecked()) {
                AsyncResponse a = new AsyncResponse() {
                    @Override
                    public void processFinish(String output) {
                        try {
                            showAccountsDialog(output);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                };
                accountHandler.getAccountsExcludingSelf(a);
            } else {
                AsyncResponse a = new AsyncResponse() {
                    @Override
                    public void processFinish(String output) {
                    }
                };
                noteHandler.addNote(a, new Note(subject, text, thisAccount.getId()));
                finish();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showAccountsDialog(String input) throws Exception {
        //region Shows accounts
        AlertDialog.Builder d = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View inflatedView = inflater.inflate(R.layout.dialog, null);
        final RecyclerView rv = (RecyclerView) inflatedView.findViewById(R.id.recyclerView);
        rv.setVisibility(View.VISIBLE);

        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(layoutManager);

        FastAdapter<IItem> fastAdapter = new FastAdapter<>();
        fastAdapter.withOnClickListener(new FastAdapter.OnClickListener<IItem>() {
            @Override
            public boolean onClick(View v, IAdapter<IItem> adapter, IItem item, int position) {
                CheckBox cb = (CheckBox) v.findViewById(R.id.checkBox);
                cb.toggle();

                if (selectedAccounts == null) {
                    selectedAccounts = new ArrayList<>();
                }

                if (cb.isChecked()) {
                    AdapterItemAccount adapterItemAccount = adapter.getItem(position);
                    Account a = adapterItemAccount.getAccount();
                    selectedAccounts.add(a);
                } else {
                    AdapterItemAccount adapterItemAccount = adapter.getItem(position);
                    Account a = adapterItemAccount.getAccount();
                    selectedAccounts.remove(a);
                }
                return true;
            }
        });

        itemAdapter = new ItemAdapter<>();
        rv.setItemAnimator(new AlphaInAnimator());
        rv.setAdapter(itemAdapter.wrap(fastAdapter));

        List<Account> accounts = NoteHandler.getAccountsFromJSON(input);
        List<AbstractItem> abstractItems = GeneralHandler.convertAccountsToAdapterItems(accounts);
        itemAdapter.add(abstractItems);

        d.setView(inflatedView);


        d.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                try {
                    AsyncResponse a = new AsyncResponse() {
                        @Override
                        public void processFinish(String output) {
                        }
                    };
                    noteHandler.addNote(a, new Note(subject, text, thisAccount.getId()));

                    if (rv.getAdapter().getItemCount() == 0) {
                        return;
                    }
                    List<Integer> accountIdList = new ArrayList<>();
                    accountIdList.add(thisAccount.getId());

                    ArrayList<String> columns = new ArrayList<>();
                    columns.add("account_id");
                    columns.add("note_id");

                    ArrayList<ArrayList<String>> values = new ArrayList<>();

                    for (Account selectedAccount :
                            selectedAccounts) {
                        ArrayList<String> al = new ArrayList<>();

                        accountIdList.add(selectedAccount.getId());

                        al.add(String.valueOf(selectedAccount.getId()));

                        al.add("(select max(id) from note)");
                        values.add(al);
                    }

                    String query = InsertIntValues(columns, values);
                    new DatabaseHandler(NewNoteActivity.this, query, AsyncURLRequest.queryType.Modify).withProgressBar().execute();

                    AsyncResponse a3 = new AsyncResponse() {
                        @Override
                        public void processFinish(String output) {

                        }
                    };
                    Note n = new Note(subject, text, thisAccount.getId());
                    new PushHandler(NewNoteActivity.this).push(a3, new SendPushMessageNote(Config.KEY_CHANNEL_NOTES, Config.KEY_EVENT_NOTE_NEW, accountIdList, n));
                    finish();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).create().show();
        //endregion
    }


    public static String InsertIntValues(ArrayList<String> columns, ArrayList<ArrayList<String>> valueListOfArrays) {
        if (columns.size() != valueListOfArrays.get(0).size()) {
            return "error";
        }
        StringBuilder result = new StringBuilder();
        result.append("insert into ").append("share").append(" (");
        // insert into foo (
        boolean isFirst = true;
        for (String string : columns) {
            if (isFirst) {
                result.append(string);
                // insert into foo (col1
                isFirst = false;
            } else {
                result.append(", ").append(string);
                // insert into foo (col1, col2
            }
        }
        result.append(") values ");
        // insert into foo (col1, col2) values

        boolean firstvaluesrow = true;
        for (int i = 0; i < valueListOfArrays.size(); i++) {
            if (firstvaluesrow) {
                firstvaluesrow = false;
            } else {
                result.append(", ");
            }
            result.append("(");
            boolean isFirstNow = true;
            for (int j = 0; j < valueListOfArrays.get(i).size(); j++) {
                if (isFirstNow) {
                    isFirstNow = false;
                } else {
                    result.append(", ");
                }
                result.append(valueListOfArrays.get(i).get(j));
            }
            result.append(")");
        }
        return result.toString();
    }
    //endregion
}