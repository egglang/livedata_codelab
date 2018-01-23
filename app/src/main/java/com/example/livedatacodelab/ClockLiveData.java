package com.example.livedatacodelab;


import android.arch.lifecycle.MutableLiveData;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;

import java.util.Calendar;
import java.util.Date;

public class ClockLiveData extends MutableLiveData<Date> {
    private final Context mAppContext;

    private BroadcastReceiver mTimeTickBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            setValue(Calendar.getInstance().getTime());
        }
    };

    public ClockLiveData(Context appContext) {
        mAppContext = appContext;
    }

    @Override
    protected void onActive() {
        Log.v("CODELAB", "onActive");
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_TIME_TICK);
        mAppContext.registerReceiver(mTimeTickBroadcastReceiver, intentFilter);
    }

    @Override
    protected void onInactive() {
        Log.v("CODELAB", "onInactive");
        mAppContext.unregisterReceiver(mTimeTickBroadcastReceiver);
    }
}
