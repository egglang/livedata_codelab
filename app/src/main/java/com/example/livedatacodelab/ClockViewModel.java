package com.example.livedatacodelab;


import android.app.Application;
import android.arch.core.util.Function;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Transformations;
import android.support.annotation.NonNull;

import java.util.Calendar;
import java.util.Date;

public class ClockViewModel extends AndroidViewModel {

    private LiveData<String> mStringClock;
    public ClockViewModel(@NonNull Application application) {
        super(application);
    }
    public LiveData<String> getClock() {
        if (mStringClock == null) {
            ClockLiveData clock = new ClockLiveData(getApplication());
            mStringClock = Transformations.map(clock, new Function<Date, String>() {
                @Override
                public String apply(Date input) {
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(input);
                    return String.format("%02d:%02d", cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE));
                }
            });
        }
        return mStringClock;
    }
}
