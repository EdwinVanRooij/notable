package nlusersi324201edwinvanrooij.fhict.httpathena.donny_app;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import nlusersi324201edwinvanrooij.fhict.httpathena.donny_app.Classes.Config;
import nlusersi324201edwinvanrooij.fhict.httpathena.donny_app.Classes.Exceptions.MyException;
import nlusersi324201edwinvanrooij.fhict.httpathena.donny_app.Classes.Handlers.MemoryHandler;
import nlusersi324201edwinvanrooij.fhict.httpathena.donny_app.Classes.MessageAdapter;
import nlusersi324201edwinvanrooij.fhict.httpathena.donny_app.Classes.Handlers.GeneralHandler;
import nlusersi324201edwinvanrooij.fhict.httpathena.donny_app.Classes.AsyncTasks.MyAsyncTask;
import nlusersi324201edwinvanrooij.fhict.httpathena.donny_app.Classes.Interfaces.AsyncResponse;
import nlusersi324201edwinvanrooij.fhict.httpathena.donny_app.Classes.Memory;
import nlusersi324201edwinvanrooij.fhict.httpathena.donny_app.Classes.Physical.Message;
import nlusersi324201edwinvanrooij.fhict.httpathena.donny_app.Classes.Runnables.MyRunnable;
import nlusersi324201edwinvanrooij.fhict.httpathena.donny_app.Classes.Runnables.SendResponse;
import nlusersi324201edwinvanrooij.fhict.httpathena.donny_app.Classes.Runnables.ShowSpeech;
import nlusersi324201edwinvanrooij.fhict.httpathena.donny_app.Classes.Strings;

public class MainActivity extends AppCompatActivity {

    //region Fields
    private EditText etMessage;
    private ListView lvMessages;
    private MessageAdapter messageAdapter;
    private TextView tvSpeech;
    private Button sendBtn;
    //endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //region Initialize controls
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        lvMessages = (ListView) findViewById(R.id.lvMessages);
        etMessage = (EditText) findViewById(R.id.etMessage);
        sendBtn = (Button) findViewById(R.id.btnSend);
        tvSpeech = (TextView) findViewById(R.id.tvBalloonDonnyHead);
        //endregion

        InitializeActivity();
    }

    private void InitializeActivity() {
        Strings.Initialize(this);
        Memory.getInstance().Initialize(tvSpeech);

        ArrayList<Message> chatHistory = new ArrayList<>();
        messageAdapter = new MessageAdapter(MainActivity.this, R.id.list_item, chatHistory);
        lvMessages.setAdapter(messageAdapter);

        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String messageText = etMessage.getText().toString();
                if (TextUtils.isEmpty(messageText)) {
                    return;
                }
                etMessage.setText("");

                messageAdapter.add(new Message(true, messageText));
                messageAdapter.notifyDataSetChanged();

                lvMessages.setSelection(lvMessages.getCount() - 1);

                GeneralHandler.log("text received; " + messageText);

                AsyncResponse a = new AsyncResponse() {
                    @Override
                    public void processFinish(String outgoing_raw_message, Exception exception) {
                        try {
                            if (exception != null) {
                                GeneralHandler.logError("Exception; " + exception.getMessage());
                                throw exception;
                            }
                            GeneralHandler.log("output; " + outgoing_raw_message);
                            messageAdapter.add(new Message(false, outgoing_raw_message));
                        } catch (MyException e) {
                            messageAdapter.add(new Message(false, e.getMessage()));
                        } catch (Exception e) {
                            MyRunnable showSpeech = new ShowSpeech(tvSpeech, "Onbekende fout #2 opgetreden. Stuur alstublieft een screenshot van dit gesprek naar mijn maker.");

                            showSpeech.runPre();
                            new Handler().postDelayed(showSpeech, 7500);

                            e.printStackTrace();
                        }
                    }
                };
                MyRunnable myRunnable = new SendResponse(tvSpeech, messageText);
                new MyAsyncTask(a, myRunnable, Config.default_extra_delay_in_ms).execute();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
