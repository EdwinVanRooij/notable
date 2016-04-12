package nlusersi324201edwinvanrooij.fhict.httpathena.cloudpadadmin_app.Classes.Handlers;

import java.util.Objects;
import java.util.regex.Pattern;

/**
 * Created by Edwin on 11/17/2015.
 */
public class StringHandler {
    public boolean isWordValid(String word) {
        Pattern p = Pattern.compile("[^a-zA-Z0-9]");

        if (Objects.equals(word, "") || word == null || Objects.equals(word, " ")
                || p.matcher(word).find()) {
            GeneralHandler.logError("input word is invalid");
            return false;
        }
        return true;
    }
}

