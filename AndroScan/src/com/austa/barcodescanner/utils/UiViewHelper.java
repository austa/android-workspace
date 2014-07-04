package com.austa.barcodescanner.utils;

import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import android.view.ViewGroup;

public class UiViewHelper {
	public static void unbindDrawables(View view) {
		if (view.getBackground() != null) {
			view.getBackground().setCallback(null);
		}
		if (view instanceof ViewGroup) {
			for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
				unbindDrawables(((ViewGroup) view).getChildAt(i));
			}
			((ViewGroup) view).removeAllViews();
		}
	}

	public static void unbindDrawablesRecycle(View view) {

		try {
			System.out.println("UNBINDING" + view);
			if (view.getBackground() != null) {
				try {
					((BitmapDrawable) view.getBackground()).getBitmap()
							.recycle();

					view.getBackground().setCallback(null);
					view = null;
				} catch (Exception e) {
					// TODO: handle exception
				}
			}

			if (view instanceof ViewGroup) {
				for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
					unbindDrawablesRecycle(((ViewGroup) view).getChildAt(i));
				}
				((ViewGroup) view).removeAllViews();
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
