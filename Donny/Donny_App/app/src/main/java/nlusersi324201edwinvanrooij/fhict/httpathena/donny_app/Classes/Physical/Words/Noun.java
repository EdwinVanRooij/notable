package nlusersi324201edwinvanrooij.fhict.httpathena.donny_app.Classes.Physical.Words;

import nlusersi324201edwinvanrooij.fhict.httpathena.donny_app.Classes.Physical.Phrase;

/**
 * Created by Edwin on 12/6/2015.
 */
// Zelfstandig Naamwoord
public class Noun extends Word {
    //region Fields
    private String word;
    private Sex sex;
    //endregion

    //region Properties

    //endregion

    //region Constructors
    public Noun(String language, Phrase phrase, String word, Sex sex) {
        super(language, phrase);
        this.word = word;
        this.sex = sex;
    }
    //endregion

    //region Methods
    @Override
    public boolean isThisWord(String word) {
        return this.word.toLowerCase().equals(word);
    }

    @Override
    public String toString() {
        return "<noun: " + word+ ">";
    }
    //endregion

    //region Enums

    //endregion
}
