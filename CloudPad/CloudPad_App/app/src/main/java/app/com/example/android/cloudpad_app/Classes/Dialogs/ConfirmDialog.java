package app.com.example.android.cloudpad_app.Classes.Dialogs;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import app.com.example.android.cloudpad_app.Classes.Interfaces.ReturnBoolean;
import app.com.example.android.cloudpad_app.R;

/**
 * Created by Edwin on 11/20/2015.
 */
public class ConfirmDialog {
    //region Fields
    private final Context c;
    private final LayoutInflater l;
    private ReturnBoolean delegate = null;
    //endregion

    //region Constructors
    public ConfirmDialog(Context c, LayoutInflater l, ReturnBoolean bool) {
        this.c = c;
        this.delegate = bool;
        this.l = l;
    }
    //endregion

    //region Methods
    public void show(String message) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(c);

        View inflatedView = l.inflate(R.layout.dialog, null);
        TextView tvDialog = (TextView) inflatedView.findViewById(R.id.tvDialogText);
        tvDialog.setVisibility(View.VISIBLE);
        tvDialog.setText(message);
        alertDialogBuilder.setView(inflatedView);

        alertDialogBuilder.setPositiveButton(c.getResources().getString(R.string.positive),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        delegate.userAnswer(true);
                    }
                });

        alertDialogBuilder.setNegativeButton(c.getResources().getString(R.string.negative),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        delegate.userAnswer(false);
                    }
                });

        alertDialogBuilder.create().show();
    }
    //endregion
}
