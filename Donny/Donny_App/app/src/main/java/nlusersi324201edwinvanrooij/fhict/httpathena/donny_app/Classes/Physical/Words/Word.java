package nlusersi324201edwinvanrooij.fhict.httpathena.donny_app.Classes.Physical.Words;

import nlusersi324201edwinvanrooij.fhict.httpathena.donny_app.Classes.Physical.Phrase;

/**
 * Created by Edwin on 12/6/2015.
 */
// Woord
public abstract class Word {
    //region Fields
    protected String language;
    protected Phrase phrase;
    //endregion

    //region Properties
    public String getLanguage() {
        return language;
    }
    public Phrase getPhrase() {
        return phrase;
    }
    //endregion

    //region Constructors
    public Word(String language, Phrase phrase) {
        this.language = language;
        this.phrase = phrase;
    }
    //endregion

    //region Methods
    public abstract boolean isThisWord(String word);
    @Override
    public abstract String toString();
    //endregion

    //region Enums
    public enum Sex { neutral, male, female }
    //endregion
}
