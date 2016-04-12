package nlusersi324201edwinvanrooij.fhict.httpathena.boodschappen_app.Classes.Physical;

/**
 * Created by Edwin on 11/24/2015.
 */
public class Product {
    //region Fields
    private int id;
    private String name;
    private int amount;
    private String remark;
    //endregion

    //region Properties
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public int getAmount() {
        return amount;
    }
    public String getRemark() {
        return remark;
    }
    //endregion

    //region Constructors
    public Product(int id, String name, int amount, String remark) {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.remark = remark;
    }
    public Product(String name, int amount, String remark) {
        this.name = name;
        this.amount = amount;
        this.remark = remark;
    }
    //endregion

    //region Methods

    //endregion
}
