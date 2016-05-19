package com.smarthome.utils;

import android.content.Context;
import android.widget.Toast;

public class ToastUtils {

	private static Context context;

	public static void initToastUtils(Context mContext) {
		context = mContext;
	}

	public static void showShortToast(String msg) {
		Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
	}

	public static void showShortToast(int msgId) {
		Toast.makeText(context, msgId, Toast.LENGTH_SHORT).show();
	}

	public static void showLongToast(String msg) {
		Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
	}

	public static void showLongToast(int msgId) {
		Toast.makeText(context, msgId, Toast.LENGTH_LONG).show();
	}

}
