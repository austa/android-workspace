package com.austa.barcodescanner.db;

import java.util.ArrayList;
import java.util.List;
import android.content.Context;
import android.database.SQLException;

public class ProductPersistenceManager extends PersistenceManager<ProductClass> {

    public ProductPersistenceManager(Context context) {
        super(context, ProductClass.class);
    }

    public List<ProductClass> getUsersWithName(String name) throws java.sql.SQLException {

        try {
            return dao.query(dao.queryBuilder().where().eq("name", name).prepare());

        } catch (SQLException e) {

            e.printStackTrace();
            return new ArrayList<ProductClass>();
        }
    }

}
