package nlusersi324201edwinvanrooij.fhict.httpathena.donny_app.Classes.Physical;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Edwin on 12/7/2015.
 */
// Zinsbouw
public class Syntax {
    //region Fields
    private String name;
    private List<Phrase> phrases;
    //endregion

    //region Properties

    public String getName() {
        return name;
    }

    public List<Phrase> getPhrases() {
        return phrases;
    }

    //endregion

    //region Constructors
    public Syntax(String name) {
        phrases = new ArrayList<>();
        this.name = name;
    }
    //endregion

    //region Methods
    public void addPhrase(Phrase phrase) {
        this.phrases.add(phrase);
    }
    //endregion

    //region Enums

    //endregion
}
