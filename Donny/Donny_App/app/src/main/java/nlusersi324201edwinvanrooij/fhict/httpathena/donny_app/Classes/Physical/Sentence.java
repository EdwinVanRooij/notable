package nlusersi324201edwinvanrooij.fhict.httpathena.donny_app.Classes.Physical;

import java.util.ArrayList;
import java.util.List;

import nlusersi324201edwinvanrooij.fhict.httpathena.donny_app.Classes.Handlers.GeneralHandler;
import nlusersi324201edwinvanrooij.fhict.httpathena.donny_app.Classes.Physical.Words.Word;

/**
 * Created by Edwin on 12/6/2015.
 */
// Zin
public class Sentence {
    //region Fields
    protected Syntax syntax;
    protected List<Word> words;
    //endregion

    //region Properties
    public void setWords(List<Word> words) {
        List<Word> result = new ArrayList<>();
        // Example:
        // Syntax phrases order is; Onderwerp, persoonsvorm
        // check for each phrase if the current phrase of
        // the word in the words field equals 'Onderwerp'
        for (Phrase p :
                syntax.getPhrases()) {
            for (Word w :
                    words) {
                if (w.getPhrase().getName().equals(p.getName())) {
                    result.add(w);
//                    GeneralHandler.log("w.getPhrase equals p");
                }
            }
        }
        this.words = result;
    }

    public int amountOfWords() {
        return words.size();
    }

    public String syntaxType() {
        return syntax.getName();
    }
    //endregion

    //region Constructors
    public Sentence(Syntax syntax) {
        this.syntax = syntax;
    }
    //endregion

    //region Methods
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("Syntax: ");
        result.append(syntax.getName());
        result.append("\r\n");

        boolean isfirst = true;
        for (Word w :
                words) {
            if (isfirst) {
                result.append(w);
                isfirst = false;
            } else {
                result.append("\r\n");
                result.append(w);
            }
        }
        GeneralHandler.log("Result in toString from Sentence, " + words.size() + " words; '" + result.toString() + "'");
        return result.toString().trim();
    }
    //endregion

    //region Enums

    //endregion
}
