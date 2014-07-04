package com.austa.barcodescanner;


import com.austa.barcodescanner.utils.Alerts;
import android.app.Activity;
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
					Toast.makeText(getApplicationContext(), "ERROR:" + e, 1).show();
				}
			}
		});

	}

	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
		if (requestCode == 0) {
			if (resultCode == RESULT_OK) {
				tvStatus.setText(intent.getStringExtra("SCAN_RESULT_FORMAT"));
				tvResult.setText(intent.getStringExtra("SCAN_RESULT"));
			} else if (resultCode == RESULT_CANCELED) {
				tvStatus.setText("Ekranı barkotun üzerine tutun.");
				tvResult.setText("Sonuç dönmedi.");
			}
		}
	}
	
	@Override
	public void onBackPressed() {
			Alerts.showAlertExit(this);
		}

	}


