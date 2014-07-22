package com.austa.barcodescanner.db;

import java.util.List;
import android.content.Context;
import android.util.Log;

public class DatabaseApp {

    private String TAG = getClass().getSimpleName();
    private ProductPersistenceManager userPersistenceManager;
    private String productName;
    private String productPrice;
    private String productStoreName;
    private List<ProductClass> personList;

    public DatabaseApp(String productName, String productPrice, String productStoreName) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.productStoreName = productStoreName;
    }

    public void executeInsert(Context context) {

        userPersistenceManager = new ProductPersistenceManager(context);
        userPersistenceManager.create(new ProductClass(1, productName, productPrice, productStoreName));
    }

    public void executeGetList(Context context) {

        userPersistenceManager = new ProductPersistenceManager(context);
        personList = userPersistenceManager.readAll();

    }

    public void executeProductList() {

        Log.d(TAG, "Kayıtlı kişiler:");
        for(ProductClass product : personList) {
            Log.d(TAG, product.toString());
        }

    }

    //
    //        // kişi listesini yazdırma
    //        
    //
    //        // ismi Barış olan kişilerin listesini yazdırma
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
    //        // kullanıcları listview'da gösterme
    //        ((ListView) findViewById(R.id.lwdatabase)).setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, personList));

}
