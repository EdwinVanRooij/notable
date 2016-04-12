package nlusersi324201edwinvanrooij.fhict.httpathena.donny_app.Classes.Handlers;

import java.util.ArrayList;
import java.util.List;

import nlusersi324201edwinvanrooij.fhict.httpathena.donny_app.Classes.Exceptions.UnknownSyntaxException;
import nlusersi324201edwinvanrooij.fhict.httpathena.donny_app.Classes.Exceptions.UnknownWordException;
import nlusersi324201edwinvanrooij.fhict.httpathena.donny_app.Classes.Physical.Phrase;
import nlusersi324201edwinvanrooij.fhict.httpathena.donny_app.Classes.Physical.Sentence;
import nlusersi324201edwinvanrooij.fhict.httpathena.donny_app.Classes.Physical.Syntax;
import nlusersi324201edwinvanrooij.fhict.httpathena.donny_app.Classes.Physical.Words.Word;

/**
 * Created by Edwin on 12/8/2015.
 */
public class ReceiveHandler {
    //region Fields
    private MemoryHandler memoryHandler;
    private String raw_sentence;
    private String[] raw_words;
    private List<Word> words;
    private Syntax syntax;
    private Sentence receivedSentence;
    //endregion

    //region Properties

    //endregion

    //region Constructors
    public ReceiveHandler() {
        memoryHandler = new MemoryHandler();
    }
    //endregion

    //region Methods
    public Sentence getSentence(String raw_sentence) throws UnknownWordException, UnknownSyntaxException {
        getRawSentence(raw_sentence);
        getRawWords();
        getWords();
        getSyntax();
        buildSentence();
        GeneralHandler.log("receivedSentence in getSentence has " + receivedSentence.amountOfWords() + " words.");
        return receivedSentence;
    }

    private void getRawSentence(String raw_sentence) {
        String new_raw_sentence = raw_sentence;
        GeneralHandler.log("Received sentence; " + raw_sentence);

        boolean wordIsLegit = false;

        while (!wordIsLegit) {
            String lastChar = new_raw_sentence.substring(new_raw_sentence.length() -1);
            GeneralHandler.log("Last char of; " + new_raw_sentence + " is <"+lastChar+">");
            if (lastChar.equals(".")
                    || lastChar.equals("?")
                    || lastChar.equals(" ")
                    || lastChar.equals("!")) {
                GeneralHandler.log("Current sentence; " + new_raw_sentence + " is not legit.");
                new_raw_sentence = new_raw_sentence.substring(0, new_raw_sentence.length() -1);
            } else {
                GeneralHandler.log("Current sentence; " + new_raw_sentence + " is legit.");
                wordIsLegit = true;
            }
        }

        this.raw_sentence = new_raw_sentence;
    }

    private void getRawWords() {
        raw_words = raw_sentence.split("\\s+");
        String debug_msg = "";
        for (String s :
                raw_words) {
            debug_msg += s + " ";
        }
        GeneralHandler.log(raw_words.length + " raw words set; " + debug_msg);
    }

    private void getWords() throws UnknownWordException {
        words = memoryHandler.getWords(raw_words);
        GeneralHandler.log(words.size() + " words set.");
    }

    private void getSyntax() throws UnknownSyntaxException {
        List<Phrase> phrases = new ArrayList<>();

        for (Word w :
                words) {
            phrases.add(w.getPhrase());
        }
        syntax = memoryHandler.getSyntax(phrases);
        GeneralHandler.log("Syntax with " + syntax.getPhrases().size() + " phrases set.");
    }

    private void buildSentence() {
        receivedSentence = new Sentence(syntax);
        GeneralHandler.log("Amount of words to go in setWords; " + words.size());
        receivedSentence.setWords(words);
        GeneralHandler.log("Sentence with " + receivedSentence.amountOfWords() + " words built.");
    }
    //endregion

    //region Enums

    //endregion
}
