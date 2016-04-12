package nlusersi324201edwinvanrooij.fhict.httpathena.donny_app.Classes.Physical.Words.Pronouns;

import nlusersi324201edwinvanrooij.fhict.httpathena.donny_app.Classes.Physical.Phrase;
import nlusersi324201edwinvanrooij.fhict.httpathena.donny_app.Classes.Physical.Words.Word;

/**
 * Created by Edwin on 12/6/2015.
 */
// Zelfstandig Naamwoord
public class PersonalPronoun extends Word {
    //region Fields
    private Sex sex;
    private boolean formal;
    private String first_person_singular;
    private String second_person_singular;
    private String third_person_singular;
    private String first_person_plural;
    private String second_person_plural;
    private String third_person_plural;
    //endregion

    //region Properties

    public Sex getSex() {
        return sex;
    }

    public boolean isFormal() {
        return formal;
    }

    public String getFirst_person_singular() {
        return first_person_singular;
    }

    public String getSecond_person_singular() {
        return second_person_singular;
    }

    public String getThird_person_singular() {
        return third_person_singular;
    }

    public String getFirst_person_plural() {
        return first_person_plural;
    }

    public String getSecond_person_plural() {
        return second_person_plural;
    }

    public String getThird_person_plural() {
        return third_person_plural;
    }

    //endregion

    //region Constructors
    public PersonalPronoun(String language, Phrase phrase, Sex sex, boolean formal, String first_person_singular, String second_person_singular, String third_person_singular, String first_person_plural, String second_person_plural, String third_person_plural) {
        super(language, phrase);
        this.sex = sex;
        this.formal = formal;
        this.first_person_singular = first_person_singular;
        this.second_person_singular = second_person_singular;
        this.third_person_singular = third_person_singular;
        this.first_person_plural = first_person_plural;
        this.second_person_plural = second_person_plural;
        this.third_person_plural = third_person_plural;
    }
    //endregion

    //region Methods
    @Override
    public boolean isThisWord(String word) {
        return first_person_singular.equals(word)
                || second_person_singular.equals(word)
                || third_person_singular.equals(word)
                || first_person_plural.equals(word)
                || second_person_plural.equals(word)
                || third_person_plural.equals(word);
    }

    @Override
    public String toString() {
        return "<personal pronoun: sex; " + getSex()+ ", formal; " +isFormal()+">";
    }
    //endregion

    //region Enums

    //endregion
}
