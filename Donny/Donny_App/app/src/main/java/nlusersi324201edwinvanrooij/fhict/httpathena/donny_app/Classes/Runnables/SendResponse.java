package nlusersi324201edwinvanrooij.fhict.httpathena.donny_app.Classes.Runnables;

import android.view.View;
import android.widget.TextView;

import nlusersi324201edwinvanrooij.fhict.httpathena.donny_app.Classes.Handlers.GeneralHandler;
import nlusersi324201edwinvanrooij.fhict.httpathena.donny_app.Classes.Handlers.ReceiveHandler;
import nlusersi324201edwinvanrooij.fhict.httpathena.donny_app.Classes.Handlers.SendHandler;
import nlusersi324201edwinvanrooij.fhict.httpathena.donny_app.Classes.Physical.Sentence;
import nlusersi324201edwinvanrooij.fhict.httpathena.donny_app.Classes.Strings;

/**
 * Created by Edwin on 12/12/2015.
 */
public class SendResponse extends MyRunnable {
    //region Fields
    private ReceiveHandler receiveHandler;
    private SendHandler sendHandler;
    private TextView tvSpeech;
    private String raw_received_message;
    private String result;
    private Exception exception;
    //endregion

    //region Properties
    public String getOutput() throws Exception {
        run();
        if (exception != null) {
            throw exception;
        }
        return result;
    }
    //endregion

    //region Constructors
    public SendResponse(TextView tvSpeech, String raw_received_message) {
        receiveHandler = new ReceiveHandler();
        sendHandler = new SendHandler();
        this.tvSpeech = tvSpeech;
        this.raw_received_message = raw_received_message;
    }
    //endregion

    //region Methods
    @Override
    public void runPre() {
        tvSpeech.setText(Strings.Typing);
        tvSpeech.setVisibility(View.VISIBLE);
    }

    @Override
    public void run() {
        try {
            Sentence received = receiveHandler.getSentence(raw_received_message);
            Sentence response = sendHandler.getSentence(received);
            result = response.toString();
        } catch (Exception e) {
            exception = e;
        }
    }

    @Override
    public void runPost() {
        try {
            tvSpeech.setVisibility(View.INVISIBLE);
        } catch (Exception e) {
            exception = e;
        }
    }
    //endregion

    //region Enums

    //endregion
}
