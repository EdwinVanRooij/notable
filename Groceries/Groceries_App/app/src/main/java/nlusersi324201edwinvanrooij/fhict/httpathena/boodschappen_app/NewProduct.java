package nlusersi324201edwinvanrooij.fhict.httpathena.boodschappen_app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.RelativeLayout;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import nlusersi324201edwinvanrooij.fhict.httpathena.libraryproject.Classes.Config;
import nlusersi324201edwinvanrooij.fhict.httpathena.boodschappen_app.Classes.Handlers.GeneralHandler;
import nlusersi324201edwinvanrooij.fhict.httpathena.boodschappen_app.Classes.Handlers.ProductHandler;
import nlusersi324201edwinvanrooij.fhict.httpathena.boodschappen_app.Classes.Physical.GroceryList;
import nlusersi324201edwinvanrooij.fhict.httpathena.boodschappen_app.Classes.Physical.Product;
import nlusersi324201edwinvanrooij.fhict.httpathena.libraryproject.Classes.Interfaces.AsyncResponse;
import nlusersi324201edwinvanrooij.fhict.httpathena.libraryproject.Classes.Physical.Account;

public class NewProduct extends AppCompatActivity {
    //region Fields
    @Bind(R.id.etName)
    EditText etName;
    @Bind(R.id.etAmount)
    EditText etAmount;
    @Bind(R.id.etRemark)
    EditText etRemark;
    @Bind(R.id.root)
    RelativeLayout root;

    private ProductHandler productHandler;
    private Account thisAccount;
    private GroceryList thisList;
    //endregion

    //region Properties

    //endregion

    //region Constructors
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_product);
        ButterKnife.bind(this);

        initializeFields();
    }

    private void initializeFields() {
        productHandler = new ProductHandler(this);

        thisAccount = getIntent().getParcelableExtra(Config.KEY_ACCOUNT);
        thisList = getIntent().getParcelableExtra(Config.KEY_LIST);
    }

    @OnClick(R.id.btnAdd)
    void btnAddClicked() {
        String name = etName.getText().toString().trim();

        if (name.length() == 0) {
            return;
        }

        String amount_str = etAmount.getText().toString().trim();
        int amount;
        if (amount_str.length() == 0) {
            amount = getResources().getInteger(R.integer.product_amount_default);
        } else {
            amount = Integer.valueOf(amount_str);
        }

        String remark = etRemark.getText().toString().trim();
        if (remark.length() == 0) {
            remark = getResources().getString(R.string.product_remark_default);
        }

        Product product = new Product(name, amount, remark);

        AsyncResponse a = new AsyncResponse() {
            @Override
            public void processFinish(String output) {
                finish();
            }
        };
        productHandler.addProduct(a, product, true, thisAccount, thisList);
    }
    //endregion

    //region Methods

    //endregion
}
