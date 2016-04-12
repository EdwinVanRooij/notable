package app.com.example.android.cloudpad_app.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mikepenz.fastadapter.FastAdapter;
import com.mikepenz.fastadapter.IAdapter;
import com.mikepenz.fastadapter.IItem;
import com.mikepenz.fastadapter.adapters.ItemAdapter;
import com.mikepenz.itemanimators.AlphaInAnimator;

import java.util.List;

import app.com.example.android.cloudpad_app.Classes.AdapterItems.AdapterItemNote;
import app.com.example.android.cloudpad_app.Classes.Dialogs.ConfirmDialog;
import nlusersi324201edwinvanrooij.fhict.httpathena.libraryproject.Classes.Handlers.AccountHandler;
import app.com.example.android.cloudpad_app.Classes.Handlers.NoteHandler;
import app.com.example.android.cloudpad_app.Classes.Interfaces.ReturnBoolean;
import app.com.example.android.cloudpad_app.Classes.Physical.Notes.Note;
import app.com.example.android.cloudpad_app.EditNoteActivity;
import app.com.example.android.cloudpad_app.R;
import butterknife.Bind;
import butterknife.ButterKnife;
import nlusersi324201edwinvanrooij.fhict.httpathena.libraryproject.Classes.Config;
import nlusersi324201edwinvanrooij.fhict.httpathena.libraryproject.Classes.Physical.Account;


public abstract class FragmentNote extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    //region Fields
    protected List<Note> notes;

    @Bind(R.id.recyclerView)
    RecyclerView rv;
    @Bind(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    protected AccountHandler accountHandler;
    protected NoteHandler noteHandler;
    protected Account thisAccount;

    protected FastAdapter<IItem> fastAdapter;
    protected ItemAdapter<AdapterItemNote> itemAdapter;

    protected boolean firstLoad = true;
    protected boolean showProgress;
    //endregion

    public FragmentNote() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_note, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initializeFields();

        setupAdapter();
    }

    private void initializeFields() {
        swipeRefreshLayout.setColorSchemeColors(getResources().getIntArray(R.array.swipeRefreshColors));
        swipeRefreshLayout.setOnRefreshListener(this);

        rv.setLayoutManager(new LinearLayoutManager(getActivity()));

        thisAccount = getArguments().getParcelable(Config.KEY_ACCOUNT);

        accountHandler = new AccountHandler(getActivity(), thisAccount);
        noteHandler = new NoteHandler(getActivity(), thisAccount);
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

        itemAdapter = new ItemAdapter<>();
        rv.setItemAnimator(new AlphaInAnimator());
        rv.setAdapter(itemAdapter.wrap(fastAdapter));
    }

    @Override
    public void onResume() {
        super.onResume();
        onRefresh();
    }

    protected void checkFirstRefresh() {
        if (firstLoad) {
            showProgress = true;
            firstLoad = false;
        } else {
            showProgress = false;
        }

        swipeRefreshLayout.setRefreshing(true);
    }

    protected void onNoteClick(int position) {
        startActivity(new Intent(getActivity(), EditNoteActivity.class).putExtra(Config.KEY_NOTE, itemAdapter.getItem(position).getNote()).putExtra(Config.KEY_ACCOUNT, thisAccount));
    }

    protected void onNoteLongClick(final int position) {
        ReturnBoolean r = new ReturnBoolean() {
            @Override
            public void userAnswer(Boolean result) {
                onUserAnswer(result, position);
            }
        };
        new ConfirmDialog(getActivity(), getActivity().getLayoutInflater(), r).show(getResources().getString(R.string.note_warning_delete));
    }

    protected abstract void onUserAnswer(boolean result, final int position);
}