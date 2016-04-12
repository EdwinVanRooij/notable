package nlusersi324201edwinvanrooij.fhict.httpathena.donny_app.Classes;

import android.content.Context;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import nlusersi324201edwinvanrooij.fhict.httpathena.donny_app.Classes.AsyncTasks.MyAsyncTask;
import nlusersi324201edwinvanrooij.fhict.httpathena.donny_app.Classes.Exceptions.UnknownPhraseException;
import nlusersi324201edwinvanrooij.fhict.httpathena.donny_app.Classes.Handlers.GeneralHandler;
import nlusersi324201edwinvanrooij.fhict.httpathena.donny_app.Classes.Handlers.MemoryHandler;
import nlusersi324201edwinvanrooij.fhict.httpathena.donny_app.Classes.Interfaces.AsyncResponse;
import nlusersi324201edwinvanrooij.fhict.httpathena.donny_app.Classes.Physical.Phrase;
import nlusersi324201edwinvanrooij.fhict.httpathena.donny_app.Classes.Physical.Syntax;
import nlusersi324201edwinvanrooij.fhict.httpathena.donny_app.Classes.Physical.Words.Article;
import nlusersi324201edwinvanrooij.fhict.httpathena.donny_app.Classes.Physical.Words.Noun;
import nlusersi324201edwinvanrooij.fhict.httpathena.donny_app.Classes.Physical.Words.Pronouns.PersonalPronoun;
import nlusersi324201edwinvanrooij.fhict.httpathena.donny_app.Classes.Physical.Words.Verb;
import nlusersi324201edwinvanrooij.fhict.httpathena.donny_app.Classes.Physical.Words.Word;
import nlusersi324201edwinvanrooij.fhict.httpathena.donny_app.Classes.Runnables.MyRunnable;
import nlusersi324201edwinvanrooij.fhict.httpathena.donny_app.Classes.Runnables.SendRequest;
import nlusersi324201edwinvanrooij.fhict.httpathena.donny_app.Classes.Runnables.ShowSpeech;

/**
 * Created by Edwin on 12/6/2015.
 */
public class Memory {
    private static Memory onlyInstance = new Memory();

    public static Memory getInstance() {
        return onlyInstance;
    }

    //region Fields
    private MemoryHandler memoryHandler;
    private List<Word> words;
    private List<Syntax> syntaxes;
    private List<Phrase> phrases;
    private TextView tvSpeech;
    //endregion

    //region Properties
    public List<Word> getWords() {
        return words;
    }

    public List<Syntax> getSyntaxes() {
        GeneralHandler.log("Syntaxes; " + syntaxes.size());
        return syntaxes;
    }

    public List<Phrase> getPhrases() {
        return phrases;
    }
    //endregion

    //region Constructors
    private Memory() {
    }
    //endregion

    //region Methods
    public void Initialize(TextView tvSpeech) {
        try {
            GeneralHandler.log("Starting memory initialization...");
            this.tvSpeech = tvSpeech;
            words = new ArrayList<>();
            memoryHandler = new MemoryHandler();
            startInitializeChain();
        } catch (Exception e) {
            GeneralHandler.log("Caught exception in Memory; initialize; " + e.getMessage());
            MyRunnable showSpeech = new ShowSpeech(tvSpeech, "Ik kan mij niets meer herinneren...\r\nControleer je netwerkverbinding.");
            showSpeech.runPre();
            new Handler().postDelayed(showSpeech, 7500);
        }
    }

    private void startInitializeChain() {
        GeneralHandler.log("Starting initialize chain...");
        AsyncResponse a = new AsyncResponse() {
            @Override
            public void processFinish(String output, Exception e) throws Exception {
                if (e != null) {
                    throw e;
                }
                phrases = getPhrasesFromDatabase(output);
                GeneralHandler.log("Got " + phrases.size() + " phrases from db.");
                AsyncResponse a2 = new AsyncResponse() {
                    @Override
                    public void processFinish(String output, Exception e) throws Exception {
                        if (e != null) {
                            throw e;
                        }
                        syntaxes = getSyntaxesFromDatabase(output);
                        GeneralHandler.log("Got " + syntaxes.size() + " syntaxes from db.");

                        AsyncResponse a3 = new AsyncResponse() {
                            @Override
                            public void processFinish(String output, Exception e) throws Exception {
                                if (e != null) {
                                    throw e;
                                }
                                int amount = addVerbsFromDatabase(output);
                                GeneralHandler.log("Got " + amount + " verbs from db.");
                                AsyncResponse a4 = new AsyncResponse() {
                                    @Override
                                    public void processFinish(String output, Exception e) throws Exception {
                                        if (e != null) {
                                            throw e;
                                        }
                                        int amount = getNounsFromDatabase(output);
                                        GeneralHandler.log("Got " + amount + " nouns from db.");
                                        AsyncResponse a5 = new AsyncResponse() {
                                            @Override
                                            public void processFinish(String output, Exception e) throws Exception {
                                                if (e != null) {
                                                    throw e;
                                                }
                                                int amount = getPersonalPronounsFromDatabase(output);
                                                GeneralHandler.log("Got " + amount + " personal pronouns from db.");
                                                AsyncResponse a6 = new AsyncResponse() {
                                                    @Override
                                                    public void processFinish(String output, Exception e) throws Exception {
                                                        if (e != null) {
                                                            throw e;
                                                        }
                                                        int amount = getArticlesFromDatabase(output);
                                                        GeneralHandler.log("Got " + amount + " articles from db.");
                                                    }
                                                };
                                                InitializeArticles(a6);
                                            }
                                        };
                                        InitializePersonalPronouns(a5);
                                    }
                                };
                                InitializeNouns(a4);
                            }
                        };
                        InitializeVerbs(a3);
                    }
                };
                InitializeSyntaxes(a2);
            }
        };
        InitializePhrases(a);
    }

    //region Syntax & Phrase PHP requests
    private void InitializePhrases(AsyncResponse a) {
        MyRunnable myRunnable = new SendRequest(tvSpeech, SendRequest.QueryType.Select, "Zinsdelen herinneren...", "select * from all_phrases");
        new MyAsyncTask(a, myRunnable, Config.default_extra_delay_in_ms).execute();
        GeneralHandler.log("Starting InitializePhrases...");
    }

    private void InitializeSyntaxes(AsyncResponse a) {
        MyRunnable myRunnable = new SendRequest(tvSpeech, SendRequest.QueryType.Select, "Zinsbouwen herinneren...", "select * from all_syntaxes");
        new MyAsyncTask(a, myRunnable, Config.default_extra_delay_in_ms).execute();
        GeneralHandler.log("Starting InitializeSyntaxes...");
    }
    //endregion

    //region Word PHP requests
    private void InitializeVerbs(AsyncResponse a) {
        MyRunnable myRunnable = new SendRequest(tvSpeech, SendRequest.QueryType.Select, "Werkwoorden herinneren...", "select * from all_verbs");
        new MyAsyncTask(a, myRunnable, Config.default_extra_delay_in_ms).execute();
        GeneralHandler.log("Starting InitializeVerbs...");
    }

    private void InitializeNouns(AsyncResponse a) {
        MyRunnable myRunnable = new SendRequest(tvSpeech, SendRequest.QueryType.Select, "Zelfstandige naamwoorden herinneren...", "select * from all_nouns");
        new MyAsyncTask(a, myRunnable, Config.default_extra_delay_in_ms).execute();
        GeneralHandler.log("Starting InitializeNouns...");
    }

    private void InitializePersonalPronouns(AsyncResponse a) {
        MyRunnable myRunnable = new SendRequest(tvSpeech, SendRequest.QueryType.Select, "Persoonlijke voornaamwoorden herinneren...", "select * from all_personal_pronouns");
        new MyAsyncTask(a, myRunnable, Config.default_extra_delay_in_ms).execute();
        GeneralHandler.log("Starting InitializePersonalPronouns...");
    }

    private void InitializeArticles(AsyncResponse a) {
        MyRunnable myRunnable = new SendRequest(tvSpeech, SendRequest.QueryType.Select, "Lidwoorden herinneren...", "select * from all_articles");
        new MyAsyncTask(a, myRunnable, Config.default_extra_delay_in_ms).execute();
        GeneralHandler.log("Starting InitializeArticles...");
    }
    //endregion

    //region Syntax & Phrase converters
    private List<Phrase> getPhrasesFromDatabase(String json_string) {
        ArrayList<Phrase> phrases = new ArrayList<>();

        try {
            JSONArray jsonArray = new JSONArray(json_string);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject j = jsonArray.getJSONObject(i);
                String name = j.getString("name");

                phrases.add(new Phrase(name));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return phrases;
    }

    private List<Syntax> getSyntaxesFromDatabase(String json_string) throws UnknownPhraseException {
        ArrayList<Syntax> syntaxes = new ArrayList<>();

        try {
            JSONArray jsonArray = new JSONArray(json_string);

            Syntax syntax = null;
            int current_syntax_id = -1;

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject j = jsonArray.getJSONObject(i);
                int syntax_id = j.getInt("syntax_id");
                String name = j.getString("syntax_name");
                Phrase phrase = memoryHandler.getPhrase(j.getString("phrase"));

                if (syntax_id != current_syntax_id) {
                    if (current_syntax_id != -1) {
                        syntaxes.add(syntax);
                    }
                    syntax = new Syntax(name);
                    current_syntax_id = syntax_id;
//                    GeneralHandler.log("Initialized new syntax " + name + " for syntax id " + current_syntax_id);
                }
                syntax.addPhrase(phrase);
            }
            syntaxes.add(syntax);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return syntaxes;
    }
    //endregion

    //region Word converters
    private int addVerbsFromDatabase(String json_string) {
        int amount = 0;

        try {
            JSONArray jsonArray = new JSONArray(json_string);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject j = jsonArray.getJSONObject(i);
                String language = j.getString("language");
                Phrase phrase = new Phrase(j.getString("phrase"));
                String s1 = j.getString("infinitive");
                String s2 = j.getString("first_person_singular");
                String s3 = j.getString("second_person_singular");
                String s4 = j.getString("third_person_singular");
                String s5 = j.getString("first_person_plural");
                String s6 = j.getString("second_person_plural");
                String s7 = j.getString("third_person_plural");

                words.add(new Verb(language, phrase, s1, s2, s3, s4, s5, s6, s7));
                amount += 1;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return amount;
    }

    private int getNounsFromDatabase(String json_string) {
        int amount = 0;

        try {
            JSONArray jsonArray = new JSONArray(json_string);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject j = jsonArray.getJSONObject(i);
                String language = j.getString("language");
                Phrase phrase = new Phrase(j.getString("phrase"));
                String word = j.getString("word");
                Noun.Sex sex = Noun.Sex.values()[j.getInt("sex")];

                words.add(new Noun(language, phrase, word, sex));
                amount += 1;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return amount;
    }

    private int getPersonalPronounsFromDatabase(String json_string) {
        int amount = 0;

        try {
            JSONArray jsonArray = new JSONArray(json_string);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject j = jsonArray.getJSONObject(i);
                String language = j.getString("language");
                Phrase phrase = new Phrase(j.getString("phrase"));
                Noun.Sex sex = Noun.Sex.values()[j.getInt("sex")];
                boolean formal = j.getInt("formal") != 0;
                String s1 = j.getString("first_person_singular");
                String s2 = j.getString("second_person_singular");
                String s3 = j.getString("third_person_singular");
                String s4 = j.getString("first_person_plural");
                String s5 = j.getString("second_person_plural");
                String s6 = j.getString("third_person_plural");

                words.add(new PersonalPronoun(language, phrase, sex, formal, s1, s2, s3, s4, s5, s6));
                amount += 1;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return amount;
    }

    private int getArticlesFromDatabase(String json_string) {
        int amount = 0;

        try {
            JSONArray jsonArray = new JSONArray(json_string);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject j = jsonArray.getJSONObject(i);

                String language = j.getString("language");
                Phrase phrase = new Phrase(j.getString("phrase"));
                String singular = j.getString("singular");
                String plural = j.getString("plural");
                Noun.Sex sex = Noun.Sex.values()[j.getInt("sex")];
                boolean isDefinite = j.getInt("is_definite") != 0;

                words.add(new Article(language, phrase, singular, plural, sex, isDefinite));
                amount += 1;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return amount;
    }
    //endregion

    //endregion

    //region Enums

    //endregion
}
