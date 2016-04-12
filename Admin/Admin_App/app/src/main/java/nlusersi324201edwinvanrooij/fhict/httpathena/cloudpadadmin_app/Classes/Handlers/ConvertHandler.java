package nlusersi324201edwinvanrooij.fhict.httpathena.cloudpadadmin_app.Classes.Handlers;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Edwin on 12/4/2015.
 */
public class ConvertHandler {
    public static String[] getSuggestionsFromJSON(String json_string) {
        String[] suggestions = new String[3];

        try {
            JSONArray jsonArray = new JSONArray(json_string);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject j = jsonArray.getJSONObject(i);

                suggestions[i] = j.getString("content");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return suggestions;
    }
}
