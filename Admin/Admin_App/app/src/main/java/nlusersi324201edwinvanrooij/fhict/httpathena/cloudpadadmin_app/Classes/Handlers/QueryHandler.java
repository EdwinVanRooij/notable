package nlusersi324201edwinvanrooij.fhict.httpathena.cloudpadadmin_app.Classes.Handlers;

import android.content.Context;

import java.util.Objects;

import nlusersi324201edwinvanrooij.fhict.httpathena.libraryproject.Classes.AsyncTasks.AsyncDataRequest;
import nlusersi324201edwinvanrooij.fhict.httpathena.libraryproject.Classes.AsyncTasks.AsyncURLRequest;
import nlusersi324201edwinvanrooij.fhict.httpathena.libraryproject.Classes.Interfaces.AsyncResponse;

/**
 * Created by Edwin on 12/4/2015.
 */
public class QueryHandler {
    //region Fields
    private Context c;
    private String[] defaultSuggestions;
    private String[] suggestions;
    //endregion

    //region Properties

    public String[] getSuggestions() {
        return suggestions;
    }

    //endregion

    //region Constructors

    public QueryHandler(Context c) {
        this.c = c;
        defaultSuggestions = new String[]{"insert", "update", "delete"};
        setDefaultSuggestions();
    }

    //endregion

    //region Methods
    public boolean userFinishedTyping(String input) {
        if (wordIsValid(input)) {
            if (Objects.equals(input.substring(input.length() - 1), " ")) {
                return true;
            }
        }
        return false;
    }

    public void setDefaultSuggestions() {
        GeneralHandler.log("Setting default suggestions.");
        suggestions = defaultSuggestions;
    }

    public void setSuggestions(String[] suggestions) {
        this.suggestions = suggestions;
    }

    public String getLastWord(String input) {
        String[] words_array = input.trim().split("\\s+");
        String last_word = words_array[words_array.length - 1];
        GeneralHandler.log("Last word; " + last_word);
        return last_word;
    }

    public boolean hasAtLeastTwoWords(String input) {
        String[] words_array = input.trim().split("\\s+");
        if (words_array.length >= 2) {
            String last_word = words_array[words_array.length - 1];
            String second_last_word = words_array[words_array.length - 2];
            GeneralHandler.log("Last word; " + last_word);
            GeneralHandler.log("Second last word; " + second_last_word);
            return true;
        }
        return false;
    }

    public void insertWords(String whole_query) {
        if (!hasAtLeastTwoWords(whole_query)) {
            GeneralHandler.logError("'"+whole_query + "' does not contain at least two words.");
            return;
        }
        String[] words_array = whole_query.trim().split("\\s+");
        final String last_word = words_array[words_array.length - 1];
        final String second_last_word = words_array[words_array.length - 2];

        AsyncResponse a = new AsyncResponse() {
            @Override
            public void processFinish(String output) {
                GeneralHandler.log("Finished inserting words. (first; " + second_last_word + ", second; " + last_word + ".");
            }
        };
        String query = "call procedure_insert_word('"+second_last_word+"','"+last_word+"');";
        new AsyncDataRequest(a, c, AsyncURLRequest.queryType.Select, query, false).execute();
    }

    public boolean isEmpty(String input) {
        if (input == null || input.length() == 0) {
            return true;
        }
        return false;
    }

    public boolean wordIsValid(String input) {
        if (input.length() == 0 ) {
            GeneralHandler.log("Word " + input + " is not valid.");
            return false;
        }
        if (input.length() > 0) {
            GeneralHandler.log("Word " + input + " is valid.");
            return true;
        }
        GeneralHandler.log("Word " + input + " is not valid.");
        return false;
    }

    public void getSuggestions(Context c, AsyncResponse a, String word, boolean showprogress) {
        String query = "call procedure_get_words('" + word + "');";
        new AsyncDataRequest(a, c, AsyncURLRequest.queryType.Select, query, showprogress).execute();
    }
    //endregion

    //region Enums

    //endregion
}
