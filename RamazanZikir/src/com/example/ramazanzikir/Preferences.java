package com.example.ramazanzikir;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

public class Preferences {
	// --- Statics --- //
	public static final String KEY_CACHE_OBJECT = "KEY_CACHE_OBJECT";

	// --- Objects --- //
	private static SharedPreferences pref;

	public static SharedPreferences getPreferences(Context context) {
		if (pref == null) {
			pref = PreferenceManager.getDefaultSharedPreferences(context);
			pref.edit().commit();
		}
		return pref;
	}

	public static String getString(String key, String defValue, Context context) {
		return getPreferences(context).getString(key, defValue);
	}

	public static void setString(String key, String newValue, Context context) {
		Editor editor = getPreferences(context).edit();
		editor.putString(key, newValue);
		editor.commit();
	}

	public static void setBoolean(String key, Boolean newValue, Context context) {
		Editor editor = getPreferences(context).edit();
		editor.putBoolean(key, newValue);
		editor.commit();
	}

	public static int getInt(String key, int defValue, Context context) {
		return getPreferences(context).getInt(key, defValue);
	}

	public static float getFloat(String key, float defValue, Context context) {
		return getPreferences(context).getFloat(key, defValue);
	}

	public static long getLong(String key, long defValue, Context context) {
		return getPreferences(context).getLong(key, defValue);
	}

	public static boolean getBoolean(String key, boolean defValue, Context context) {
		return getPreferences(context).getBoolean(key, defValue);
	}

	public static boolean isDefined(String key, Context context) {
		return Preferences.getBoolean(key, false, context);
	}

	public static void tutorialDefined(String key, Context context) {
		setBoolean(key, true, context);
	}

	public static void tutorialDefined(String key, boolean val, Context context) {
		setBoolean(key, val, context);
	}
}
