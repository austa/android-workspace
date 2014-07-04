package com.austa.barcodescanner.utils;


import com.austa.barcodescanner.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;


public class Alerts {
	public static void showAlertNoUuid(final Context context) {
		new AlertDialog.Builder(context)
				.setTitle("Uygulamayı kapat üzeresiniz!")
				.setMessage(
						"Uygulamadan Çıkkmak üzeresiniz. Barcode Readers uygulamasını sonlandırmak istiyor musunuz? ")
				.setPositiveButton("Evet",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
								((Activity) context).finish();
							}
						})
				.setNegativeButton("Hayır",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
								// do nothing
							}
						}).setIcon(R.drawable.ic_launcher).show();
	}

	public static Dialog showAlertNoInternetConnection(final Context context,
			final Boolean isFinish) {
		final AlertDialog dialog = new AlertDialog.Builder(context)
				.setTitle("Internet bağlantısı yok!")
				.setMessage(
						"Internet bağlatınoz yok. Cihazınızın internet bağlantısını sağlayap tekrar deneyiniz! ")
				.setPositiveButton("Evet",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
								Intent settingsIntent = new Intent(
										android.provider.Settings.ACTION_SETTINGS);
								context.startActivity(settingsIntent);
								if (isFinish) {
									((Activity) context).finish();
								}
							}
						})
				.setNegativeButton("Hayır",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
								if (isFinish) {
									((Activity) context).finish();
								}

							}
						}).setIcon(R.drawable.ic_launcher).create();
		dialog.show();
		dialog.setOnCancelListener(new OnCancelListener() {

			@Override
			public void onCancel(DialogInterface dialog) {
				try {
					dialog.dismiss();
					dialog.cancel();
				} catch (Exception e) {
					// TODO: handle exception
				}

			}
		});

		return dialog;
	}

	public static void showAlertExit(final Context context) {
		new AlertDialog.Builder(context)
				.setTitle("Barcode Scanner uygulaması kapanıyor!")
				.setMessage(
						"Uygulamadan Çıkmak Üzeresiniz. Uygulamayı sonlandırmak istiyor musunuz? ")
				.setPositiveButton("Evet",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
								((Activity) context).finish();
							}
						})
				.setNegativeButton("Hayır",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
								// do nothing
							}
						}).setIcon(R.drawable.ic_launcher).show();
	}
}
