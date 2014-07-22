package com.austa.barcodescanner.db;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "Product")
public class ProductClass implements
        PersistenceManager.Modal {

    @DatabaseField(id = true, columnName = "Id")
    private int id;

    @DatabaseField(columnName = "product_name")
    private String productName;

    @DatabaseField(columnName = "product_price")
    private String productPrice;

    @DatabaseField(columnName = "product_sname")
    private String productStoreName;

    public ProductClass(int id, String productName, String productPrice, String productStoreName) {
        this.id = id;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productStoreName = productStoreName;
    }

    public ProductClass() {
        // TODO Auto-generated constructor stub
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductStoreName() {
        return productStoreName;
    }

    public void setProductStoreName(String productStoreName) {
        this.productStoreName = productStoreName;
    }

    @Override
    public String toString() {

        return "ID: " + this.id + "\n" +

        "Ürün Fiyatı: " + this.productName + "\n" +

        "Ürün Fiyatı: " + this.productPrice + "\n" +

        "Mağza adı: " + this.productStoreName + "\n";
    }
}
