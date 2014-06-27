package com.example.ramazanzikir;

import java.io.Serializable;
import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {
	
	// ---- Referanslar ----//
	
	ArrayList<ZikirClass> zikList;
	private Integer zikirSayisi = 0;
	private TextView zikirSayisiText;
	private EditText etZikirName;
	private ImageButton zikirStart;
	private Button btnSave;
	private Button btnSifirla;
	private Button btnZikirGor;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.zikir_main);
		
		//----- İliklendirmeler -----//
		
		btnZikirGor = (Button) findViewById(R.id.zikir_gor_buton);
		zikirSayisiText = (TextView) findViewById(R.id.zikirSayiciText);
		zikirStart = (ImageButton) findViewById(R.id.zikirButon);
		etZikirName = (EditText) findViewById(R.id.ziki_ad_editText);
		btnSave = (Button) findViewById(R.id.kaydet_buton);
		btnSifirla = (Button) findViewById(R.id.sifirla_buton);
		zikList = new ArrayList<ZikirClass>();
		
		//---- Olay yükleme ----//
		
		zikirStart.setOnClickListener(this);	
		btnSave.setOnClickListener(this);
		btnSifirla.setOnClickListener(this);

	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		
		case R.id.zikirButon :
			
			zikirSayisi++;
			zikirSayisiText.setText(zikirSayisi.toString());	
			
			break;
		case R.id.kaydet_buton:
			
			ZikirClass zik = new ZikirClass(etZikirName.getText()
					.toString(), Integer.parseInt(zikirSayisiText
					.getText().toString()));
			zikList.add(zik);
			
			break;
		case R.id.sifirla_buton:
			
			zikirSayisi = 0;
			zikirSayisiText.setText(zikirSayisi.toString());
			
			break;
		}
		
	}

	private void serializeCacheList() {
		try {

			Preferences.setString(Preferences.KEY_CACHE_OBJECT,
					ObjectSerializer.serialize((Serializable) zikList), this);
			
			Toast.makeText(this, "Bu test", Toast.LENGTH_LONG).show();

		} catch (Exception e) {

		}
	}

	@SuppressWarnings("unchecked")
	private void deserializeCacheList() {

		try {

			zikList = (ArrayList<ZikirClass>) ObjectSerializer
					.deserialize(Preferences.getString(
							Preferences.KEY_CACHE_OBJECT, ObjectSerializer
									.serialize(new ArrayList<ZikirClass>()),
							this));

		} catch (Exception e) {

		}

	}

	@Override
	protected void onPause() {
		super.onPause();
		serializeCacheList();
	}

	@Override
	protected void onResume() {
		super.onResume();

		deserializeCacheList();
		if (zikList.size() > 0)
			Toast.makeText(this, zikList.get(0).getZikirAd(), Toast.LENGTH_LONG)
					.show();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();

		Toast.makeText(getApplicationContext(), "onDestroy" + zikirSayisi,
				Toast.LENGTH_LONG).show();
	}

	
}
