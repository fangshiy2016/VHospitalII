package com.yuyang.VRHospital.broadcast;

import android.content.Context;
import android.content.Intent;

public interface LocalBroadcastListener {
    public abstract void onLocalReceive(Context context, Intent intent);
}
