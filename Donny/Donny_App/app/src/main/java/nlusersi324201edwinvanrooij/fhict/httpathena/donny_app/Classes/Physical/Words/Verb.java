package nlusersi324201edwinvanrooij.fhict.httpathena.donny_app.Classes.Physical.Words;

import nlusersi324201edwinvanrooij.fhict.httpathena.donny_app.Classes.Physical.Phrase;

/**
 * Created by Edwin on 12/6/2015.
 */
public class Verb extends Word {
    //region Fields
    private String infinitive;
    private String first_person_singular;
    private String second_person_singular;
    private String third_person_singular;
    private String first_person_plural;
    private String second_person_plural;
    private String third_person_plural;
    //endregion

    //region Properties
    public String getInfinitive() {
        return infinitive;
    }

    public void setInfinitive(String infinitive) {
        this.infinitive = infinitive;
    }

    public String getFirst_person_singular() {
        return first_person_singular;
    }

    public void setFirst_person_singular(String first_person_singular) {
        this.first_person_singular = first_person_singular;
    }

    public String getSecond_person_singular() {
        return second_person_singular;
    }

    public void setSecond_person_singular(String second_person_singular) {
        this.second_person_singular = second_person_singular;
    }

    public String getThird_person_singular() {
        return third_person_singular;
    }

    public void setThird_person_singular(String third_person_singular) {
        this.third_person_singular = third_person_singular;
    }

    public String getFirst_person_plural() {
        return first_person_plural;
    }

    public void setFirst_person_plural(String first_person_plural) {
        this.first_person_plural = first_person_plural;
    }

    public String getSecond_person_plural() {
        return second_person_plural;
    }

    public void setSecond_person_plural(String second_person_plural) {
        this.second_person_plural = second_person_plural;
    }

    public String getThird_person_plural() {
        return third_person_plural;
    }

    public void setThird_person_plural(String third_person_plural) {
        this.third_person_plural = third_person_plural;
    }
    //endregion

    //region Constructors
    public Verb(String language, Phrase phrase, String infinitive, String first_person_singular, String second_person_singular, String third_person_singular, String first_person_plural, String second_person_plural, String third_person_plural) {
        super(language, phrase);
        this.infinitive = infinitive;
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
        return infinitive.equals(word)
                || first_person_singular.toLowerCase().equals(word)
                || second_person_singular.toLowerCase().equals(word)
                || third_person_singular.toLowerCase().equals(word)
                || first_person_plural.toLowerCase().equals(word)
                || second_person_plural.toLowerCase().equals(word)
                || third_person_plural.toLowerCase().equals(word);
    }

    @Override
    public String toString() {
        return "<verb infinitive: "+ infinitive + ">";
    }
    //endregion

    //region Enums

    //endregion
}
