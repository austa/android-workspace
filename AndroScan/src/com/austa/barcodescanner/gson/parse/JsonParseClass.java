package com.austa.barcodescanner.gson.parse;


import java.util.HashMap;
import java.util.List;

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
	public static HashMap<String,String> hm;
	
	public static void addCurrentProductToProductsListUsingGson(JSONObject currentProduct, List<ProductPropertyClass> productsList) {
	   
		Gson gson = new Gson();
		ProductPropertyClass product = gson.fromJson(findAndReplace(currentProduct), ProductPropertyClass.class);
		productsList.add(product);
		
		
		/*
		Gson g = new Gson();
		ProductContainerClass vc = g.fromJson(findAndReplace(currentProduct), ProductContainerClass.class);
		hm = new HashMap<String,String>(); 

		for(ProductPropertyClass p: vc.products){
	    	hm.put(p.getProductName(), p.getProductURL());  
		}*/
		
		
	}
	
	public static String findAndReplace(JSONObject currentProduct){
		int counter = 0;
		String newString;
		newString = currentProduct.toString();
		
		while (counter < 5) {
			String mystring = String.valueOf(counter) + ":";
			if (newString.contains(mystring)) {
				newString.replace(mystring, "");
				counter++;
			}else{
				counter++;	
			}
			mystring = "";
		}	
			return newString;
	}
	
}
		
	
	
	
	


