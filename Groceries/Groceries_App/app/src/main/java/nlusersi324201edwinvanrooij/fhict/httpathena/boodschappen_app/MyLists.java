package nlusersi324201edwinvanrooij.fhict.httpathena.boodschappen_app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.mikepenz.fastadapter.FastAdapter;
import com.mikepenz.fastadapter.IAdapter;
import com.mikepenz.fastadapter.IItem;
import com.mikepenz.fastadapter.adapters.ItemAdapter;
import com.mikepenz.itemanimators.AlphaInAnimator;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import nlusersi324201edwinvanrooij.fhict.httpathena.boodschappen_app.Classes.AdapterItems.AdapterItemGroceryList;
import nlusersi324201edwinvanrooij.fhict.httpathena.boodschappen_app.Classes.Handlers.GeneralHandler;
import nlusersi324201edwinvanrooij.fhict.httpathena.boodschappen_app.Classes.Physical.GroceryList;
import nlusersi324201edwinvanrooij.fhict.httpathena.libraryproject.Classes.AsyncTasks.AsyncDataRequest;
import nlusersi324201edwinvanrooij.fhict.httpathena.libraryproject.Classes.AsyncTasks.AsyncURLRequest;
import nlusersi324201edwinvanrooij.fhict.httpathena.libraryproject.Classes.Config;
import nlusersi324201edwinvanrooij.fhict.httpathena.libraryproject.Classes.Interfaces.AsyncResponse;
import nlusersi324201edwinvanrooij.fhict.httpathena.libraryproject.Classes.Physical.Account;

public class MyLists extends AppCompatActivity {
    //region Fields
    @Bind(R.id.recyclerView)
    RecyclerView rv;

    protected List<GroceryList> lists;
    private Account thisAccount;

    protected FastAdapter<IItem> fastAdapter;
    protected ItemAdapter<AdapterItemGroceryList> itemAdapter;
    //endregion

    //region Properties

    //endregion

    //region Constructors
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mylists);
        ButterKnife.bind(this);

        initializeFields();

        getLists();

        setupAdapter();
    }


    //endregion

    //region Methods
    private void getLists() {
        try {
            AsyncResponse a = new AsyncResponse() {

                @Override
                public void processFinish(String output) {
                    try {
                        lists = GeneralHandler.getGroceryListsFromJSON(output);

                        itemAdapter.removeItemRange(0, itemAdapter.getAdapterItemCount());

                        itemAdapter.add(GeneralHandler.convertListsToAdapterItems(lists));
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            };
            String query = "select l.id, title from groceries_list l, groceries_list_account la, account a where a.id = la.account_id and l.id = la.list_id and account_id = " + thisAccount.getId();
            new AsyncDataRequest(a, this, AsyncURLRequest.queryType.Select, query, true).execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initializeFields() {
        thisAccount = getIntent().getParcelableExtra(Config.KEY_ACCOUNT);

        rv.setLayoutManager(new LinearLayoutManager(this));

    }

    private void setupAdapter() {
        fastAdapter = new FastAdapter<>();
        fastAdapter.withOnClickListener(new FastAdapter.OnClickListener<IItem>() {
            @Override
            public boolean onClick(View v, IAdapter<IItem> adapter, IItem item, int position) {
                onListClick(position);
                return true;
            }
        })
                .withOnLongClickListener(new FastAdapter.OnLongClickListener<IItem>() {
                    @Override
                    public boolean onLongClick(View v, IAdapter<IItem> adapter, IItem item, int position) {
                        onListLongClick(position);
                        return true;
                    }
                });

        itemAdapter = new ItemAdapter<>();
        rv.setItemAnimator(new AlphaInAnimator());
        rv.setAdapter(itemAdapter.wrap(fastAdapter));
    }

    private void onListClick(int position) {

    }

    private void onListLongClick(int position) {

    }
    //endregion
}
