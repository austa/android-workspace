package com.austa.barcodescanner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.json.JSONObject;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.austa.barcodescanner.adapter.ImageSearchResultAdapter;
import com.austa.barcodescanner.gson.parse.ProductContainerClass;
import com.austa.barcodescanner.gson.parse.ProductPropertyClass;
import com.google.gson.Gson;

public class SecondActivity extends Activity {
    private String url = "http://www.searchupc.com/handlers/upcsearch.ashx?request_type=3&access_token=C1D63810-388B-4B3E-BECD-5778741E60E0&upc=9780321356680";
    private Button scanButton;
    private ListView resultList;
    private ProgressBar progressBar;
    private RequestQueue queue;
    private Gson gson;
    private JsonObjectRequest searchRequest, extendedRequest;
    private ImageSearchResultAdapter adapter;
    private String searchText;
    private List<ProductPropertyClass> productList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);
        scanButton = (Button) findViewById(R.id.scanButton);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        resultList = (ListView) findViewById(R.id.resultsList);
        queue = Volley.newRequestQueue(getApplicationContext());
        scanButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                scanButton.setEnabled(false);
                progressBar.setVisibility(View.VISIBLE);
                resultList.setVisibility(View.GONE);
                searchRequest = new JsonObjectRequest(Request.Method.GET, url, null, new ResponseListener(), new ErrorListener());
                queue.add(searchRequest);
            }
        });
    }

    private class ResponseListener implements
            Response.Listener<JSONObject> {
        @Override
        public void onResponse(JSONObject response) {
            scanButton.setEnabled(true);
            setUpResults(response);
        }
    }

    private class ExtendedResponseListener implements
            Response.Listener<JSONObject> {
        @Override
        public void onResponse(JSONObject response) {
            addExtendedResults(response);
        }
    }

    private class ErrorListener implements
            Response.ErrorListener {
        @Override
        public void onErrorResponse(VolleyError error) {
            scanButton.setEnabled(true);
            progressBar.setVisibility(View.GONE);
            Toast.makeText(getApplicationContext(), "Error, Please Try Again", Toast.LENGTH_LONG).show();
        }
    }

    private void setUpResults(JSONObject response) {

        productList = new ArrayList<ProductPropertyClass>();
        Gson g = new Gson();
        ProductContainerClass vc = g.fromJson(findAndReplace(response), ProductContainerClass.class);
        productList = vc.getProduct();
        adapter = new ImageSearchResultAdapter(this, productList);
        resultList.setAdapter(adapter);
        resultList.setVisibility(View.VISIBLE);
        //get the next 8 results in the list           
        progressBar.setVisibility(View.GONE);

    }

    private void addExtendedResults(JSONObject response) {
        ProductPropertyClass searchClass = gson.fromJson(response.toString(), ProductPropertyClass.class);
        if (searchClass.getResponse() != null) {
            ProductPropertyClass[] results = searchClass.getResponse().getResults();
            List<ProductPropertyClass> tempList = Arrays.asList(results);
            productList.addAll(tempList);
            resultList.setAdapter(adapter);
        }
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
