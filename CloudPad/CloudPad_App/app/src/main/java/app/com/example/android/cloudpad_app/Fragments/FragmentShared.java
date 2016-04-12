package app.com.example.android.cloudpad_app.Fragments;

import android.support.v4.widget.SwipeRefreshLayout;

import app.com.example.android.cloudpad_app.Classes.Physical.Notes.Note;
import nlusersi324201edwinvanrooij.fhict.httpathena.libraryproject.Classes.Handlers.AccountHandler;
import app.com.example.android.cloudpad_app.Classes.Handlers.GeneralHandler;
import app.com.example.android.cloudpad_app.Classes.Handlers.NoteHandler;
import nlusersi324201edwinvanrooij.fhict.httpathena.libraryproject.Classes.AsyncTasks.AsyncURLRequest;
import nlusersi324201edwinvanrooij.fhict.httpathena.libraryproject.Classes.Handlers.DatabaseHandler;
import nlusersi324201edwinvanrooij.fhict.httpathena.libraryproject.Classes.Interfaces.AsyncResponse;


public class FragmentShared extends FragmentNote implements SwipeRefreshLayout.OnRefreshListener {

    public FragmentShared() {
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
                        notes = noteHandler.getSharedNotesFromJSON(output);

                        itemAdapter.removeItemRange(0, itemAdapter.getAdapterItemCount());

                        itemAdapter.add(GeneralHandler.convertNotesToAdapterItems(notes));
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    } finally {
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }
            };
            noteHandler.getSharedNotes(a, showProgress);
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
                        try {
                            AsyncResponse a = new AsyncResponse() {
                                @Override
                                public void processFinish(String output) {

                                }
                            };
                            noteHandler.deleteNote(a, itemAdapter.getItem(position).getNote(), NoteHandler.getAccountsFromJSON(output));
                            onRefresh();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                };
                Note note = itemAdapter.getItem(position).getNote();
                accountHandler = new AccountHandler(getActivity());
                String query = "select a.id, a.username, a.email, a.password, a.creation, a.blocked from account a, share an, note n where a.id = an.account_id and an.note_id = n.id and n.id = " + note.getId() + " and a.id <> " + thisAccount.getId();
                new DatabaseHandler(getActivity(), query, AsyncURLRequest.queryType.Select).withProgressBar().withAsyncResponse(a).execute();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
