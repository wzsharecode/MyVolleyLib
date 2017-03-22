package cn.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class HttpTool {
	/**
	 * 网络是否可用
	 * 
	 * @param context
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static boolean isNetworkAvailable(Context context) {
		ConnectivityManager mgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo[] info = mgr.getAllNetworkInfo();
		if (info != null) {
			for (int i = 0; i < info.length; i++) {
				if (info[i].getState() == NetworkInfo.State.CONNECTED) {
					return true;
				}
			}
		}
		return false;
	}
}
