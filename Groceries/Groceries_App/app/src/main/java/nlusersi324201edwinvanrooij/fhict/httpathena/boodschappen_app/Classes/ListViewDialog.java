package nlusersi324201edwinvanrooij.fhict.httpathena.boodschappen_app.Classes;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;

import nlusersi324201edwinvanrooij.fhict.httpathena.boodschappen_app.R;


/**
 * Created by Edwin on 11/12/2015.
 */
public class ListViewDialog {
    //region Fields
    private final Context c;
    private final LayoutInflater l;
    private final ListAdapter la;
    //endregion

    //region Properties

    //endregion

    //region Constructors
    public ListViewDialog(Context c, LayoutInflater l, ListAdapter la) {
        this.c = c;
        this.l = l;
        this.la = la;
    }
    //endregion

    //region Methods
    public void show() {
        //region Shows update dialog
        AlertDialog.Builder d = new AlertDialog.Builder(c);
        View inflatedView = l.inflate(R.layout.dialog, null);

        final ListView lv = (ListView) inflatedView.findViewById(R.id.listView);
        lv.setVisibility(View.VISIBLE);
        lv.setAdapter(la);

        d.setView(inflatedView);

        d.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        }).create().show();
        //endregion
    }
    //endregion
}
