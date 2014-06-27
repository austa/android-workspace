package com.example.exampleproject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends Activity {
	
	private String url = "http://api.openweathermap.org/data/2.5/weather?q=";
	
	private TextView sehir_id;
	private TextView icon_id;
	private TextView gokyuzu;
	private TextView hava_durumu;
	private EditText editSehir;
	private Button goster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        sehir_id = (TextView) findViewById(R.id.sehir_id);
        icon_id = (TextView) findViewById(R.id.icon);
        gokyuzu = (TextView) findViewById(R.id.gokyuzu);
        hava_durumu = (TextView) findViewById(R.id.hava_durumu);
        editSehir = (EditText) findViewById(R.id.editText1);
        goster = (Button) findViewById(R.id.button1);
        
        url = url + editSehir.getText().toString() + ",tr";
        System.out.println(url);
        goster.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
		        JsonObjectRequest getRequest = new JsonObjectRequest(Method.GET, url, null,
		        	    new Response.Listener<JSONObject>()
		        	    {
		        	        @Override
		        	        public void onResponse(JSONObject response) {  
		        	                        // display response    
		        	        	try {
									parseJSON(response);
								} catch (JSONException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
		        	        }
		        	    },
		        	    new Response.ErrorListener()
		        	    {
		        	    	 @Override
		        	    	    public void onErrorResponse(VolleyError error) {
		        	    	        System.out.println("Error ["+error+"]");
		        	    	         
		        	    	 }
		        	    });
		      
		        	queue.add(getRequest);

		        }

		});
        
    }
    private  void parseJSON(JSONObject json) throws JSONException{      
    	JSONArray items = json.getJSONArray("weather");

    	for(int i=0;i<items.length();i++) {
    		JSONObject jobt = items.getJSONObject(0);
        	sehir_id.setText(jobt.getString("id").toString());
        	icon_id.setText(jobt.getString("icon").toString());
        	gokyuzu.setText(jobt.getString("description").toString());
        	hava_durumu.setText(jobt.getString("main").toString());
		}
    	/*durum = id + "\n" + main + "\n" + description + "\n" + icon;
    	text1.setText(durum);
    */}
    
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

   

}
