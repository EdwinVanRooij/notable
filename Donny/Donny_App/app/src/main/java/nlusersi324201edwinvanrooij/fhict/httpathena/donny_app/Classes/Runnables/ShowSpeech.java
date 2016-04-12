package nlusersi324201edwinvanrooij.fhict.httpathena.donny_app.Classes.Runnables;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Edwin on 12/12/2015.
 */
public class ShowSpeech extends MyRunnable {
    //region Fields
    private TextView tvSpeech;
    private String content;
    //endregion

    //region Properties

    //endregion

    //region Constructors
    public ShowSpeech(TextView tvSpeech, String content) {
        super();
        this.tvSpeech = tvSpeech;
        this.content = content;
    }
    //endregion

    //region Methods
    @Override
    public void runPre() {
        tvSpeech.setText(content);
        tvSpeech.setVisibility(View.VISIBLE);

    }
    @Override
    public void run() {

    }
    @Override
    public void runPost() {
        tvSpeech.setVisibility(View.INVISIBLE);
    }
    //endregion

    //region Enums

    //endregion
}
