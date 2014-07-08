package com.austa.barcodescanner;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.Request.Method;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.austa.barcodescanner.gson.parse.JsonParseClass;
import com.austa.barcodescanner.gson.parse.ProductPropertyClass;
import com.austa.barcodescanner.utils.Alerts;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	private TextView tvStatus;
	private TextView tvResult;
	private JsonParseClass jtos;
	private RequestQueue mRequestQueue;
	private static List<ProductPropertyClass> productList = new ArrayList<ProductPropertyClass>();
	private ProductPropertyClass propertyProduct;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		tvStatus = (TextView) findViewById(R.id.tvStatus);
		tvResult = (TextView) findViewById(R.id.tvResult);

		Button scanBtn = (Button) findViewById(R.id.btnScan);
		scanBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				try {
					Intent intent = new Intent(
							"com.google.zxing.client.android.SCAN");
					intent.putExtra("SCAN_MODE", "QR_CODE_MODE,PRODUCT_MODE");
					startActivityForResult(intent, 0);
				} catch (Exception e) {
					e.printStackTrace();
					Toast.makeText(getApplicationContext(), "ERROR:" + e, 1)
							.show();
				}
			}
		});
	}

	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
		propertyProduct = new ProductPropertyClass();
		if (requestCode == 0) {
			if (resultCode == RESULT_OK) {
				tvStatus.setText(intent.getStringExtra("SCAN_RESULT_FORMAT"));
				String result = intent.getStringExtra("SCAN_RESULT");
				generateRequest(result);
				tvResult.setText(propertyProduct.toString());
			} else if (resultCode == RESULT_CANCELED) {
				tvStatus.setText("Ekranı barkotun üzerine tutun.");
				tvResult.setText("Sonuç dönmedi.");
			}
		}
	}

	private void generateRequest(String result) {
		String url = "http://www.searchupc.com/handlers/upcsearch.ashx?request_type=3&access_token=C1D63810-388B-4B3E-BECD-5778741E60E0&upc="
				+ result;

		jtos = new JsonParseClass();

		final ProgressDialog pDialog = new ProgressDialog(this);
		pDialog.setMessage("Bilgiler geliyor...");
		pDialog.show();
		JsonObjectRequest jsonObjReq = new JsonObjectRequest(Method.GET, url,
				null, new Response.Listener<JSONObject>() {

					@Override
					public void onResponse(JSONObject response) {
						pDialog.hide();
						String deneme = jtos.findAndReplace(response);
						Toast.makeText(getApplicationContext(), deneme, Toast. LENGTH_LONG).show();
						jtos.addCurrentProductToProductsListUsingGson(response, productList);
					}
				}, new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {
						pDialog.hide();
					}
				});

		// Adding request to request queue
		addToRequestQueue(jsonObjReq);
	}

	@Override
	public void onBackPressed() {
		Alerts.showAlertExit(this);
	}

	public RequestQueue getRequestQueue() {
		if (mRequestQueue == null) {
			mRequestQueue = Volley.newRequestQueue(getApplicationContext());
		}

		return mRequestQueue;
	}

	public <T> void addToRequestQueue(Request<T> req) {
		getRequestQueue().add(req);
	}
}
