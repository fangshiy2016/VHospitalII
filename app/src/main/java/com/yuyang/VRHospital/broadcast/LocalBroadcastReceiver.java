package com.yuyang.VRHospital.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

public class LocalBroadcastReceiver extends BroadcastReceiver {
    public interface LocalIntent {
        public static final String ACTION_MAIN = "vrhospital.intent.action.MAIN";

        public static final String EXTRA_EVENT = "vrhospital.intent.extra.EVENT";


        public static final String EVENT_LOGIN_NOTIFY = "login_notify";
        public static final String EVENT_REGISTER_NOTIFY = "register_notify";
        public static final String EVENT_CLOSE_MAIN_ACTIVITY = "close_mian_activity";
    }

    private LocalBroadcastListener mListener;

    public LocalBroadcastReceiver(LocalBroadcastListener listener) {
        mListener = listener;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (mListener != null) {
            mListener.onLocalReceive(context, intent);
        }
    }

    public static void notifyLogin(Context context) {
        Intent intent = new Intent(LocalIntent.ACTION_MAIN);
        intent.putExtra(LocalIntent.EXTRA_EVENT, LocalIntent.EVENT_LOGIN_NOTIFY);
        LocalBroadcastManager lbm = LocalBroadcastManager.getInstance(context);
        lbm.sendBroadcast(intent);
    }

    public static void notifyRegister(Context context) {
        Intent intent = new Intent(LocalIntent.ACTION_MAIN);
        intent.putExtra(LocalIntent.EXTRA_EVENT, LocalIntent.EVENT_REGISTER_NOTIFY);
        LocalBroadcastManager lbm = LocalBroadcastManager.getInstance(context);
        lbm.sendBroadcast(intent);
    }

    public static void notifyCloseMainActivity(Context context) {
        Intent intent = new Intent(LocalIntent.ACTION_MAIN);
        intent.putExtra(LocalIntent.EXTRA_EVENT, LocalIntent.EVENT_CLOSE_MAIN_ACTIVITY);
        LocalBroadcastManager lbm = LocalBroadcastManager.getInstance(context);
        lbm.sendBroadcast(intent);
    }

}