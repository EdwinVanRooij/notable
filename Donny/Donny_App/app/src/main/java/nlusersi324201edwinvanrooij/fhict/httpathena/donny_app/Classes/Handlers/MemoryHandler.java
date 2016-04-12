package nlusersi324201edwinvanrooij.fhict.httpathena.donny_app.Classes.Handlers;

import android.view.View;

import java.util.ArrayList;
import java.util.List;

import nlusersi324201edwinvanrooij.fhict.httpathena.donny_app.Classes.Exceptions.UnknownPhraseException;
import nlusersi324201edwinvanrooij.fhict.httpathena.donny_app.Classes.Exceptions.UnknownSyntaxException;
import nlusersi324201edwinvanrooij.fhict.httpathena.donny_app.Classes.Exceptions.UnknownWordException;
import nlusersi324201edwinvanrooij.fhict.httpathena.donny_app.Classes.Memory;
import nlusersi324201edwinvanrooij.fhict.httpathena.donny_app.Classes.Physical.Phrase;
import nlusersi324201edwinvanrooij.fhict.httpathena.donny_app.Classes.Physical.Syntax;
import nlusersi324201edwinvanrooij.fhict.httpathena.donny_app.Classes.Physical.Words.Word;

/**
 * Created by Edwin on 12/8/2015.
 */
public class MemoryHandler {
    //region Fields

    //endregion

    //region Properties

    //endregion

    //region Constructors

    //endregion

    //region Methods
    public List<Word> getWords(String[] raw_words) throws UnknownWordException {
        List<Word> words = new ArrayList<>();
        for (String s :
                raw_words) {
            words.add(getWord(s));
        }
        return words;
    }

    private Word getWord(String raw_word) throws UnknownWordException {
        GeneralHandler.log("Word is " + raw_word);
        for (Word w :
                Memory.getInstance().getWords()) {
            if (w.isThisWord(raw_word.toLowerCase())) {

                GeneralHandler.log("raw_word " + raw_word + " got me " + w.toString());
                return w;
            }
        }
        GeneralHandler.log("raw_word " + raw_word + " got me nothing.");
        throw new UnknownWordException("Ik ken het woord '" + raw_word + "' nog niet.");
    }

    public Syntax getSyntax(List<Phrase> phrases) throws UnknownSyntaxException {
        for (Syntax s :
                Memory.getInstance().getSyntaxes()) {
            //Compare two phrase orders with each other
            if (phraseString(phrases).equals(phraseString(s.getPhrases()))) {
                GeneralHandler.log("I know this syntax. It has " + phrases.size() + " phrases.");
                GeneralHandler.log("The one I know has " + s.getPhrases().size() + " phrases, should be equal to above.");
                return s;
            }
        }
        String debugmsg = "";
        for (Phrase p :
                phrases) {
            debugmsg += p.getName() + " ";
        }
        debugmsg = debugmsg.substring(0, debugmsg.length() -1);
        throw new UnknownSyntaxException("Ik ken de zinsbouw '" + debugmsg + "' nog niet.");
    }

    public Phrase getPhrase(String phraseName) throws UnknownPhraseException {
        for (Phrase p :
                Memory.getInstance().getPhrases()) {
            if (p.getName().equals(phraseName)) {
                return p;
            }
        }
        GeneralHandler.log("Phrase with name " + phraseName + " not found in " + Memory.getInstance().getPhrases().size() + " known phrases.");
        throw new UnknownPhraseException("Ik ken het zinsdeel '" + phraseName + "' nog niet.");
    }

    // Turn the phrase into a string to be able to
    // compare two lists with each other
    public String phraseString(List<Phrase> phrases) {
        String result = "";
        for (Phrase p :
                phrases) {
            result += p.getName() + " ";
        }
        return result.trim();
    }
    //endregion

    //region Enums

    //endregion
}
