package nlusersi324201edwinvanrooij.fhict.httpathena.donny_app.Classes.Physical.Words;

import nlusersi324201edwinvanrooij.fhict.httpathena.donny_app.Classes.Physical.Phrase;

/**
 * Created by Edwin on 12/14/2015.
 */
public class Article extends Word {
    //region Fields
    private String singular;
    private String plural;
    private Sex sex;
    private boolean isDefinite;
    //endregion

    //region Properties

    public String getSingular() {
        return singular;
    }

    public String getPlural() {
        return plural;
    }

    public Sex getSex() {
        return sex;
    }

    public boolean isDefinite() {
        return isDefinite;
    }

    //endregion

    //region Constructors
    public Article(String language, Phrase phrase, String singular, String plural, Sex sex, boolean isDefinite) {
        super(language, phrase);
        this.singular = singular;
        this.plural = plural;
        this.sex = sex;
        this.isDefinite = isDefinite;
    }
    //endregion

    //region Methods

    //endregion

    //region Enums
    @Override
    public boolean isThisWord(String word) {
        return singular.toLowerCase().equals(word)
                || plural.toLowerCase().equals(word);
    }

    @Override
    public String toString() {
        return "<article isDefinite " + isDefinite + ": " + singular + ">";
    }
    //endregion
}
