package com.austa.barcodescanner.db;

import java.util.ArrayList;
import java.util.List;
import android.content.Context;
import android.database.SQLException;

public class ProductPersistenceManager extends PersistenceManager<ProductClass> {

    /**
     * This constructor is used to initialise the DAO object for the given class.
     * 
     * @param context
     */
    public ProductPersistenceManager(Context context) {
        super(context, ProductClass.class);
    }

    /**
     * Returns the records that contains the given name in the 'name' column
     * 
     * @param name
     *            : The string that is expected in the 'name' column
     * @return
     * @throws java.sql.SQLException
     */
    public static List<ProductClass> getUsersWithName(String name) throws java.sql.SQLException {

        // query for all users which has the given name
        try {
            return dao.query(dao.queryBuilder().where().eq("name", name).prepare());

        } catch (SQLException e) {

            e.printStackTrace();
            return new ArrayList<ProductClass>();
        }
    }

}
