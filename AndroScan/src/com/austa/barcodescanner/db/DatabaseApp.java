package com.austa.barcodescanner.db;

import java.util.List;
import android.content.Context;
import android.util.Log;

public class DatabaseApp {

    private String TAG = getClass().getSimpleName();
    private ProductPersistenceManager userPersistenceManager;
    private static int id = 1;
    private String productName;
    private String productPrice;
    private String productStoreName;
    private String productCurrency;
    private List<ProductClass> personList;

    public DatabaseApp(String productName, String productPrice, String productStoreName, String productCurrency) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.productStoreName = productStoreName;
        this.productCurrency = productCurrency;
    }

    public void executeInsert(Context context) {

        userPersistenceManager = new ProductPersistenceManager(context);
        userPersistenceManager.create(new ProductClass(id, productName, productPrice, productStoreName, productCurrency));
        id = id + 1;
    }

    public void executeGetList(Context context) {
        userPersistenceManager = new ProductPersistenceManager(context);
        personList = userPersistenceManager.readAll();

        Log.d(TAG, "Kayıtlı kişiler:");
        for(ProductClass product : personList) {
            Log.d(TAG, product.toString());
        }

    }

    //        Log.d(TAG, "İsmi Barış olan kişiler:");
    //        try {
    //            for(ProductClass person : ProductPersistenceManager.getUsersWithName("Barış")) {
    //                Log.d(TAG, person.toString());
    //            }
    //        } catch (SQLException e) {
    //            // TODO Auto-generated catch block
    //            e.printStackTrace();
    //        }
    //
    //        
    //        ((ListView) findViewById(R.id.lwdatabase)).setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, personList));

}
