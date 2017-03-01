package com.yuyang.VRHospital.utils;

import android.content.pm.ApplicationInfo;
import android.util.Log;

import com.yuyang.VRHospital.BaseApp;

public class DebugUtil {

	public static boolean DEBUGABLE = true && isDebugable();

	public static boolean DUMMY_OFFLINE = false && isDebugable();

	private static boolean isDebugable() {
		ApplicationInfo appInfo = BaseApp.getAppInfo();
		if (appInfo == null) {
			return false;
		}
		if ((appInfo.flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * @param msgS
	 */
	public static void logD(String msgS) {
		if (!DEBUGABLE) {
			return;
		}
		logD("", msgS);
	}

	/**
	 * @param tag
	 * @param msgS
	 */
	public static void logD(String tag,String msgS) {
		if (!DEBUGABLE) {
			return;
		}
		StackTraceElement t = Thread.currentThread().getStackTrace()[3];
		String clsName = t.getClassName();
		String message = String.format("%s(L:%d)/ ", t.getMethodName(),
                t.getLineNumber());
		Log.d(tag + clsName, message + msgS);
	}

	/**
	 * @param msgS
	 */
	public static void logW(String msgS) {
		if (!DEBUGABLE) {
			return;
		}
		logW("", msgS);
	}

	/**
	 * @param msgS
	 */
	public static void logW(String tag,String msgS) {
		if (!DEBUGABLE) {
			return;
		}
		StackTraceElement t = Thread.currentThread().getStackTrace()[3];
		String clsName = t.getClassName();
		String message = String.format("%s(L:%d)/ ", t.getMethodName(),
                t.getLineNumber());
		Log.w(tag + clsName, message + msgS);
	}

	/**
	 * @param e
	 */
	public static void logE(Exception e) {
		String tag = "%s(L:%d)/ ";
		StackTraceElement t = Thread.currentThread().getStackTrace()[3];
		String clsName = t.getClassName();
		String message = String.format(tag, t.getMethodName(), t.getLineNumber());
		StringBuilder sb = new StringBuilder();
		sb.append(e);
		sb.append("::");
		sb.append(e != null ? e.getMessage() : e);
		Log.e(clsName, message + sb.toString());
	}

	/**
	 * @param msgS
	 */
	public static void logE(String msgS) {
		if (!DEBUGABLE) {
			return;
		}
		logE("", msgS);
	}

	/**
	 * @param tag
	 * @param msgS
	 */
	public static void logE(String tag,String msgS) {
		if (!DEBUGABLE) {
			return;
		}
		StackTraceElement t = Thread.currentThread().getStackTrace()[3];
		String clsName = t.getClassName();
		String message = String.format("%s(L:%d)/ ", t.getMethodName(),
                t.getLineNumber());
		Log.e(tag + clsName, message + msgS);
	}

}
