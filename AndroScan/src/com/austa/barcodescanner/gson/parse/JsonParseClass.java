package com.austa.barcodescanner.gson.parse;

import java.util.ArrayList;
import org.json.JSONObject;
import com.google.gson.Gson;

public class JsonParseClass {

    public static final String PRODUCT_NAME = "productname";
    public static final String PRODUCT_IMAGE_URL = "imageurl";
    public static final String PRODUCT_URL = "producturl";
    public static final String PRODUCT_PRICE = "price";
    public static final String PRODUCT_CURRENCY = "currency";
    public static final String PRODUCT_SALE_PRİCE = "saleprice";
    public static final String PRODUCT_STORE_NAME = "storename";

    //public static HashMap<String,String> hm;

    public ArrayList<ProductPropertyClass> addCurrentProductToProductsListUsingGson(JSONObject currentProduct) {

        Gson g = new Gson();
        ProductContainerClass vc = g.fromJson(findAndReplace(currentProduct), ProductContainerClass.class);
        return vc.getProduct();

        /*
         * Gson gson = new Gson();
         * ProductContainerClass product = gson.fromJson(findAndReplace(currentProduct), ProductContainerClass.class);
         * productsList.add(product);
         */
    }

    public String findAndReplace(JSONObject currentProduct) {
        char theLastCharacter;
        char theFirstCharacter;
        final String PRODUCT_NAME = "\"product\":[";
        final String THE_LAST_CHARACTER = "]";
        int counter = 0;
        String notInNumberJsonObjectString, newString = null;
        notInNumberJsonObjectString = currentProduct.toString();

        while(counter < 5) {
            String mystring = "\"" + counter + "\":";
            if (notInNumberJsonObjectString.contains(mystring)) {
                notInNumberJsonObjectString = notInNumberJsonObjectString.replace(mystring, "");
            }
            counter++;
        }

        theFirstCharacter = notInNumberJsonObjectString.charAt(0);
        theLastCharacter = notInNumberJsonObjectString.charAt(notInNumberJsonObjectString.length() - 1);
        notInNumberJsonObjectString = notInNumberJsonObjectString.substring(1, notInNumberJsonObjectString.length() - 1);

        newString = theFirstCharacter + PRODUCT_NAME + notInNumberJsonObjectString + THE_LAST_CHARACTER + theLastCharacter;

        return newString;
    }

}
