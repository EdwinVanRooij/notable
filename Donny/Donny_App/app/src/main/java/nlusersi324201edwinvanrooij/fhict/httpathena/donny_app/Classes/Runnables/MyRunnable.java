package nlusersi324201edwinvanrooij.fhict.httpathena.donny_app.Classes.Runnables;

/**
 * Created by Edwin on 12/12/2015.
 */
public abstract class MyRunnable implements Runnable {
    //region Fields
    protected String output;
    protected Exception exception;
    //endregion

    //region Properties
    public String getOutput() throws Exception {
        if (exception != null) {
            throw exception;
        }
        return output;
    }
    //endregion

    //region Constructors
    public MyRunnable() {

    }
    //endregion

    //region Methods
    public abstract void runPre();
    @Override
    public abstract void run();
    public abstract void runPost();
    //endregion

    //region Enums

    //endregion
}
