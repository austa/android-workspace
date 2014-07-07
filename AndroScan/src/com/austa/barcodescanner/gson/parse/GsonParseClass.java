package com.austa.barcodescanner.gson.parse;

import java.util.HashMap;

import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

public class GsonParseClass {
	
	private JSONObject response;
	private HashMap<String, String> hm;
	public JSONObject getResponse() {
		return response;
	}
	
	public void setResponse(JSONObject response2) {
		this.response = response2;
	}
	
	public void gsonParse() {
		
		
		Gson g = new Gson();
		ProductContainerClass vc = g.fromJson(response.toString(), ProductContainerClass.class);
		hm = new HashMap<String,String>();

		for(ProductPropertyClass p: vc.products){
		  hm.put(p.getProductName(), p.getProductURL());  
		};

	}
	
	
}

