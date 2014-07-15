package com.austa.barcodescanner;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.austa.barcodescanner.adapter.ImageSearchResultAdapter;
import com.austa.barcodescanner.dfragment.DFragment;
import com.austa.barcodescanner.gson.parse.ProductContainerClass;
import com.austa.barcodescanner.gson.parse.ProductPropertyClass;
import com.austa.barcodescanner.utils.Alerts;
import com.google.gson.Gson;

public class MainActivity extends FragmentActivity {
    private String url = "http://www.searchupc.com/handlers/upcsearch.ashx?request_type=3&access_token=C1D63810-388B-4B3E-BECD-5778741E60E0&upc=";
    private View vDraw;
    private TextView tvNotResult;
    private Button scanButton;
    private ListView resultList;
    private ProgressBar progressBar;
    private RequestQueue queue;
    private Gson g;
    private ImageSearchResultAdapter adapter;
    private List<ProductPropertyClass> productList;
    FragmentManager fm = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        vDraw = (View) findViewById(R.id.view1);
        tvNotResult = (TextView) findViewById(R.id.tvNotResult);
        scanButton = (Button) findViewById(R.id.scanButton);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        resultList = (ListView) findViewById(R.id.resultsList);
        queue = Volley.newRequestQueue(getApplicationContext());
        scanButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                scanButton.setEnabled(false);
                resultList.setVisibility(View.GONE);
                progressBar.setVisibility(View.VISIBLE);
                try {
                    Intent intent = new Intent("com.google.zxing.client.android.SCAN");
                    intent.putExtra("SCAN_MODE", "QR_CODE_MODE,PRODUCT_MODE");
                    startActivityForResult(intent, 0);
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "ERROR:" + e, 1).show();
                }
            }
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {
                tvNotResult.setVisibility(View.GONE);
                String result = intent.getStringExtra("SCAN_RESULT");
                generateRequest(result);
            } else if (resultCode == RESULT_CANCELED) {
                tvNotResult.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
                scanButton.setEnabled(true);
            }
        }
    }

    private void generateRequest(String result) {
        String newUrl = url + result;
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Method.GET, newUrl, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                scanButton.setEnabled(true);
                setUpResults(response);
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                scanButton.setEnabled(true);
                progressBar.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(), "Error, Please Try Again", Toast.LENGTH_LONG).show();
            }
        });
        addToRequestQueue(jsonObjReq);
    }

    @Override
    public void onBackPressed() {
        Alerts.showAlertExit(this);
    }

    public RequestQueue getRequestQueue() {
        if (queue == null) {
            queue = Volley.newRequestQueue(getApplicationContext());
        }

        return queue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }

    public String editJsonEntity(String jsonEntity) {
        jsonEntity = jsonEntity.substring(1, jsonEntity.length() - 1);
        return jsonEntity;
    }

    private void setUpResults(JSONObject response) {

        productList = new ArrayList<ProductPropertyClass>();
        g = new Gson();
        ProductContainerClass vc = g.fromJson(findAndReplace(response), ProductContainerClass.class);
        productList = vc.getProduct();
        adapter = new ImageSearchResultAdapter(this, productList);
        resultList.setAdapter(adapter);
        resultList.setVisibility(View.VISIBLE);
        vDraw.setVisibility(View.VISIBLE);
        listViewClikEvent(resultList);
        progressBar.setVisibility(View.GONE);

    }

    private void listViewClikEvent(ListView listview) {
        listview.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View view, int position, long id) {
                DFragment alertdFragment = new DFragment();
                alertdFragment.setData(productList.get(position));
                alertdFragment.show(fm, "Alert Dialog Fragment");
            }
        });

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
