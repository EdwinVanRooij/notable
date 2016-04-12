package app.com.example.android.cloudpad_app.Fragments;

import android.support.v4.widget.SwipeRefreshLayout;

import java.util.List;

import app.com.example.android.cloudpad_app.Classes.AdapterItems.AdapterItemNote;
import app.com.example.android.cloudpad_app.Classes.Handlers.GeneralHandler;
import nlusersi324201edwinvanrooij.fhict.httpathena.libraryproject.Classes.Interfaces.AsyncResponse;


public class FragmentPrivate extends FragmentNote implements SwipeRefreshLayout.OnRefreshListener {

    public FragmentPrivate() {
        // Required empty public constructor
    }

    @Override
    public void onRefresh() {
        try {
            checkFirstRefresh();

            AsyncResponse a = new AsyncResponse() {

                @Override
                public void processFinish(String output) {
                    try {
                        notes = noteHandler.getPrivateNotesFromJSON(output);

                        itemAdapter.removeItemRange(0, itemAdapter.getAdapterItemCount());
                        List<AdapterItemNote> abstractItemList = GeneralHandler.convertNotesToAdapterItems(notes);
                        itemAdapter.add(abstractItemList);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    } finally {
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }
            };
            noteHandler.getPrivateNotes(a, showProgress);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onUserAnswer(boolean result, final int position) {
        if (result) {
            try {
                AsyncResponse a = new AsyncResponse() {
                    @Override
                    public void processFinish(String output) {

                    }
                };
                noteHandler.deleteNote(a, itemAdapter.getItem(position).getNote());
                onRefresh();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}