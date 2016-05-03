package app.com.example.android.cloudpad_app.fragments;

import android.support.v4.widget.SwipeRefreshLayout;

import java.util.List;

import app.com.example.android.cloudpad_app.classes.adapteritems.AdapterItemNote;
import app.com.example.android.cloudpad_app.utils.GeneralHandler;
import nlusersi324201edwinvanrooij.fhict.httpathena.libraryproject.Classes.AsyncTasks.AsyncURLRequest;
import nlusersi324201edwinvanrooij.fhict.httpathena.libraryproject.Classes.Handlers.DatabaseHandler;
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
                String query = "update note set active = 0 where id = " + itemAdapter.getItem(position).getNote().getId();
                new DatabaseHandler(getActivity(), query, AsyncURLRequest.queryType.Modify).withProgressBar().withAsyncResponse(a).execute();
                onRefresh();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}