package com.austa.barcodescanner.gson.parse;

import com.google.gson.annotations.SerializedName;

public class ProductPropertyClass {
	
	@SerializedName(JsonParseClass.PRODUCT_NAME)
    private String productName;
     
	@SerializedName(JsonParseClass.PRODUCT_IMAGE_URL)
    private String imageUrl;
	
	@SerializedName(JsonParseClass.PRODUCT_URL)
    private String productUrl;
	
	@SerializedName(JsonParseClass.PRODUCT_PRICE)
    private String productPrice;
	
	@SerializedName(JsonParseClass.PRODUCT_CURRENCY)
    private String productCurrency;
	
	@SerializedName(JsonParseClass.PRODUCT_SALE_PRİCE)
    private String productSalePrice;
	
	@SerializedName(JsonParseClass.PRODUCT_STORE_NAME)
    private String productStoreName;
	
	
	@Override
	  public String toString(){
	    
		return "İsim: "+this.productName+"\n" +
	    
				"Fotoğraf url: "+this.imageUrl+"\n" +
	    
				"Ürün Url: "+ this.productUrl + "\n" +
	    
				"Ürün Fiyatı: "+ this.productPrice +"\n" +
	    
				"Para Birim: "+this.productCurrency +"\n" +
	    
				"İndirimli fiyat: "+this.productSalePrice +"\n" +
	    
				"Mağza adı: "+this.productStoreName +"\n";
	    
	  }
		

}
