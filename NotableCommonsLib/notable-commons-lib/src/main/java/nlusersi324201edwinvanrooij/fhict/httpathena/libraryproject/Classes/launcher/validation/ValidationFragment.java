package nlusersi324201edwinvanrooij.fhict.httpathena.libraryproject.Classes.launcher.validation;


import android.content.Intent;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;

import nlusersi324201edwinvanrooij.fhict.httpathena.libraryproject.Classes.Handlers.AccountHandler;
import nlusersi324201edwinvanrooij.fhict.httpathena.libraryproject.Classes.Handlers.GeneralHandler;
import nlusersi324201edwinvanrooij.fhict.httpathena.libraryproject.Classes.launcher.LauncherActivity;
import nlusersi324201edwinvanrooij.fhict.httpathena.libraryproject.Classes.Handlers.LocalHandler;
import nlusersi324201edwinvanrooij.fhict.httpathena.libraryproject.Classes.Interfaces.AsyncResponse;
import nlusersi324201edwinvanrooij.fhict.httpathena.libraryproject.Classes.Physical.Account;
import nlusersi324201edwinvanrooij.fhict.httpathena.libraryproject.R;


/**
 * A simple {@link Fragment} subclass.
 */
public abstract class ValidationFragment extends Fragment {

    static Class loginSuccessActivity;

    LocalHandler localHandler;
    AccountHandler accountHandler;

    private static final String TAG = "ValidationFragment";

    protected abstract void initialize();

    protected abstract void initViews(ViewGroup rootView);

    protected abstract void initButtons();

    protected abstract void initEditTexts();

    ValidationObject getDefaultUsernameValidation(String username) {
        if (username.length() < 5) {
            return new ValidationObject("Moet meer dan 5 karakters bevatten.");
        }

        if (username.length() > 15) {
            return new ValidationObject("Mag niet meer dan 15 karakters bevatten.");
        }

        return new ValidationObject();
    }

    ValidationObject getDefaultEmailValidation(String email) {
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return new ValidationObject("Vul een geldig e-mail adres in.");
        }

        return new ValidationObject();
    }

    ValidationObject getDefaultPasswordValidation(String password) {
        if (password.length() < 5) {
            return new ValidationObject("Moet meer dan 5 karakters bevatten.");
        }

        if (password.length() > 20) {
            return new ValidationObject("Mag niet meer dan 20 karakters bevatten.");
        }

        if(!password.matches(".*\\d.*")){
            return new ValidationObject("Moet minimaal één cijfer bevatten.");
        }

        return new ValidationObject();
    }

    void checkCredentials(String username, String password) throws UnsupportedEncodingException, MalformedURLException {
        AsyncResponse a = new AsyncResponse() {
            @Override
            public void processFinish(String output) {
                try {
                    Account a = accountHandler.getAccountIfExists(output);
                    if (a != null) {
                        Log.d(TAG, "processFinish: Correct login");
                        onCorrectCredentials(a);
                    } else {
                        Log.d(TAG, "processFinish: Incorrect login");
                        onIncorrectCredentials();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        accountHandler.getAccount(a, username, password);
    }

    private void onCorrectCredentials(Account a) {

        localHandler.ExecuteLogin(a);

        if (loginSuccessActivity != null) {
            startActivity(new Intent(getActivity(), loginSuccessActivity));
        }

        GeneralHandler.ClearTopGo(getActivity(), LauncherActivity.class, R.anim.slide_in_top, R.anim.slide_out_bottom);
    }

    private void onIncorrectCredentials() {
        Toast.makeText(getActivity(), R.string.exception_login_credentials, Toast.LENGTH_LONG).show();
    }
}
