package cn.util;

import android.text.TextUtils;
import android.util.Log;

public class LogUtil {

	private static String TAG = "MyLog";
	private static boolean deBug = true;
	
	/**
	 * debug
	 * 
	 * @param str
	 */
	public static void d(String str) {
		if (TextUtils.isEmpty(str)) {
			return;
		}
		if (deBug) {
			Log.d(TAG, str);
		}
	}
	/**
	 * info
	 * 
	 * @param str
	 */
	public static void i(String str) {
		if (TextUtils.isEmpty(str)) {
			return;
		}
		if (deBug) {
			Log.i(TAG, str);
		}
	}
	/**
	 * verbose
	 * 
	 * @param str
	 */
	public static void v(String str) {
		if (TextUtils.isEmpty(str)) {
			return;
		}
		if (deBug) {
			Log.v(TAG, str);
		}
	}
	

	/**
	 * warn
	 * 
	 * @param str
	 */
	public static void w(String str) {
		if (TextUtils.isEmpty(str)) {
			return;
		}
		if (deBug) {
			Log.w(TAG, str);
		}
	}

	/**
	 * error
	 * 
	 * @param str
	 */
	public static void e(String str) {
		if (TextUtils.isEmpty(str)) {
			return;
		}
		if (deBug) {
			Log.e(TAG, str);
		}
	}

}
