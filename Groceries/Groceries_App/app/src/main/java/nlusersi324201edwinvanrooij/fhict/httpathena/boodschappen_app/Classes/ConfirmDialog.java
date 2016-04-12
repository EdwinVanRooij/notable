package nlusersi324201edwinvanrooij.fhict.httpathena.boodschappen_app.Classes;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import nlusersi324201edwinvanrooij.fhict.httpathena.boodschappen_app.Classes.Interfaces.ReturnBoolean;
import nlusersi324201edwinvanrooij.fhict.httpathena.boodschappen_app.R;

/**
 * Created by Edwin on 11/20/2015.
 */
public class ConfirmDialog {
    //region Fields
    private Context c;
    private boolean clickedYes;
    //endregion

    //region Properties
    public ReturnBoolean delegate = null;
    //endregion

    //region Constructors
    public ConfirmDialog(Context c, ReturnBoolean bool) {
        this.c = c;
        this.delegate = bool;
    }
    //endregion

    //region Methods
    public boolean show(String message) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(c);
        alertDialogBuilder.setMessage(message);

        alertDialogBuilder.setPositiveButton(c.getResources().getString(R.string.positive),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        delegate.processFinish(true);
                    }
                });

        alertDialogBuilder.setNegativeButton(c.getResources().getString(R.string.negative),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        delegate.processFinish(false);
                    }
                });

        alertDialogBuilder.create().show();
        return clickedYes;
    }
    //endregion
}
