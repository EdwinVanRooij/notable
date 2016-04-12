package nlusersi324201edwinvanrooij.fhict.httpathena.cloudpadadmin_app.Classes.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mikepenz.fastadapter.FastAdapter;
import com.mikepenz.fastadapter.IAdapter;
import com.mikepenz.fastadapter.IItem;
import com.mikepenz.fastadapter.adapters.ItemAdapter;
import com.mikepenz.itemanimators.AlphaInAnimator;

import butterknife.Bind;
import butterknife.ButterKnife;
import nlusersi324201edwinvanrooij.fhict.httpathena.cloudpadadmin_app.R;
import nlusersi324201edwinvanrooij.fhict.httpathena.libraryproject.Classes.Config;
import nlusersi324201edwinvanrooij.fhict.httpathena.libraryproject.adapteritems.AdapterItemFeedback;


public class FragmentFeedback extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    //region Fields
    @Bind(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;
    @Bind(R.id.recyclerView)
    RecyclerView rv;

    protected FastAdapter<IItem> fastAdapter;
//    protected ItemAdapter<AdapterItemFeedback> itemAdapter;
    //endregion

    public FragmentFeedback() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initializeFields();

        setupAdapter();
    }

    private void setupAdapter() {
        fastAdapter = new FastAdapter<>();
        fastAdapter.withOnClickListener(new FastAdapter.OnClickListener<IItem>() {
            @Override
            public boolean onClick(View v, IAdapter<IItem> adapter, IItem item, int position) {
                onNoteClick(position);
                return true;
            }
        })
                .withOnLongClickListener(new FastAdapter.OnLongClickListener<IItem>() {
                    @Override
                    public boolean onLongClick(View v, IAdapter<IItem> adapter, IItem item, int position) {
                        onNoteLongClick(position);
                        return true;
                    }
                });

//        itemAdapter = new ItemAdapter<>();
        rv.setItemAnimator(new AlphaInAnimator());
//        rv.setAdapter(itemAdapter.wrap(fastAdapter));
    }
    protected void onNoteClick(int position) {
    }

    protected void onNoteLongClick(final int position) {
    }

    private void initializeFields() {
        swipeRefreshLayout.setColorSchemeColors(getResources().getIntArray(R.array.swipeRefreshColors));
        swipeRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        onRefresh();
    }

    @Override
    public void onRefresh() {
        try {
//            swipeRefreshLayout.setRefreshing(true);
//            swipeRefreshLayout.setRefreshing(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}