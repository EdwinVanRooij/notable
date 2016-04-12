package nlusersi324201edwinvanrooij.fhict.httpathena.donny_app.Classes.Interfaces;

import java.util.List;

/**
 * Created by Edwin on 12/5/2015.
 */
public interface AsyncResponse {
    void processFinish(String output, Exception e) throws Exception;
}
