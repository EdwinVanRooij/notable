package app.com.example.android.cloudpad_app.classes.dialogs;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.mikepenz.fastadapter.FastAdapter;
import com.mikepenz.fastadapter.IAdapter;
import com.mikepenz.fastadapter.IItem;
import com.mikepenz.fastadapter.adapters.ItemAdapter;
import com.mikepenz.fastadapter.items.AbstractItem;
import com.mikepenz.itemanimators.AlphaInAnimator;

import java.util.List;

import app.com.example.android.cloudpad_app.R;

/**
 * Created by Edwin on 11/12/2015.
 */
public class ListViewDialog {
    //region Fields
    private final Context c;
    private final LayoutInflater l;

    private final List<AbstractItem> adapterItems;
    ItemAdapter<AbstractItem> itemAdapter;

    RecyclerView rv;
    //endregion

    //region Properties

    //endregion

    //region Constructors
    public ListViewDialog(Context c, LayoutInflater l, List<AbstractItem> adapterItems) {
        this.c = c;
        this.l = l;
        this.adapterItems = adapterItems;
    }
    //endregion

    //region Methods
    public void show() {
        //region Shows update dialog
        AlertDialog.Builder d = new AlertDialog.Builder(c);
        View inflatedView = l.inflate(R.layout.dialog, null);

        rv = (RecyclerView) inflatedView.findViewById(R.id.recyclerView);
        rv.setVisibility(View.VISIBLE);

        final LinearLayoutManager layoutManager = new LinearLayoutManager(c);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(layoutManager);

        setupAdapter();
        d.setView(inflatedView);

        itemAdapter.add(adapterItems);

        d.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        }).create().show();
        //endregion
    }

    private void setupAdapter() {
        FastAdapter<IItem> fastAdapter = new FastAdapter<>();
        fastAdapter.withOnClickListener(new FastAdapter.OnClickListener<IItem>() {
            @Override
            public boolean onClick(View v, IAdapter<IItem> adapter, IItem item, int position) {
                return true;
            }
        }).withOnLongClickListener(new FastAdapter.OnLongClickListener<IItem>() {
            @Override
            public boolean onLongClick(View v, IAdapter<IItem> adapter, IItem item, int position) {
                return true;
            }
        });

        itemAdapter = new ItemAdapter<>();
        rv.setItemAnimator(new AlphaInAnimator());
        rv.setAdapter(itemAdapter.wrap(fastAdapter));
    }
    //endregion
}
