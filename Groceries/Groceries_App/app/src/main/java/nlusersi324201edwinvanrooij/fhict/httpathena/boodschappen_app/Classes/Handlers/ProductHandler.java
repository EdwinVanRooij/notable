package nlusersi324201edwinvanrooij.fhict.httpathena.boodschappen_app.Classes.Handlers;

import android.content.Context;

import nlusersi324201edwinvanrooij.fhict.httpathena.boodschappen_app.Classes.Physical.GroceryList;
import nlusersi324201edwinvanrooij.fhict.httpathena.boodschappen_app.Classes.Physical.Product;
import nlusersi324201edwinvanrooij.fhict.httpathena.libraryproject.Classes.AsyncTasks.AsyncDataRequest;
import nlusersi324201edwinvanrooij.fhict.httpathena.libraryproject.Classes.AsyncTasks.AsyncURLRequest;
import nlusersi324201edwinvanrooij.fhict.httpathena.libraryproject.Classes.Interfaces.AsyncResponse;
import nlusersi324201edwinvanrooij.fhict.httpathena.libraryproject.Classes.Physical.Account;

/**
 * Created by Edwin on 11/24/2015.
 */
public class ProductHandler {
    //region Fields
    private Context c;
    //endregion

    //region Properties

    //endregion

    //region Constructors
    public ProductHandler(Context c) {
        this.c = c;
    }
    //endregion

    //region Methods
    public void deleteProduct(AsyncResponse a, Product p, boolean show_progress) {
        String query1 ="delete from groceries_unknown_product where id = " + p.getId();
        String query2 = "delete from groceries_product where id = " + p.getId();
        new AsyncDataRequest(a, c, AsyncURLRequest.queryType.Modify, query1, true).execute();
        new AsyncDataRequest(a, c, AsyncURLRequest.queryType.Modify, query2, true).execute();
    }

    public void addProduct(AsyncResponse a, Product p, boolean show_progress, Account thisAccount, GroceryList thisList) {
        String query1 = "INSERT INTO groceries_product (name, amount, remark) VALUES ('" + p.getName() + "', " + p.getAmount() + ", '" + p.getRemark() + "');";
        String query2 = "INSERT INTO groceries_unknown_product (id, list_id) VALUES ((select max(id) from groceries_product), "+thisList.getId()+")";
        new AsyncDataRequest(a, c, AsyncURLRequest.queryType.Modify, query1, true).execute();
        new AsyncDataRequest(a, c, AsyncURLRequest.queryType.Modify, query2, true).execute();
    }

    public void getAllProducts(AsyncResponse a, Account thisAccount, GroceryList currentList) {
        String query = "select p.id, p.name, p.amount, p.remark from groceries_product p join groceries_unknown_product up on p.id = up.id join groceries_list l on up.list_id = l.id join groceries_list_account la on la.list_id = l.id join account a on a.id = la.account_id and l.id = " + currentList.getId() + " and a.id = " + thisAccount.getId() + ";";
        new AsyncDataRequest(a, c, AsyncURLRequest.queryType.Select, query, true).execute();
    }
    //endregion
}
