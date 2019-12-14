package com.self.androidarchdemos;

import android.util.Log;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.ViewModel;

public class MainActivityViewModel extends ViewModel implements LifecycleObserver{

    private static final String TAG = MainActivityViewModel.class.getSimpleName();

    private Thread counterThread;
    private int count = 0;
    private boolean isCounterInProgress;

    private MutableLiveData<Integer> counterValue;

    public MainActivityViewModel(){
        isCounterInProgress = false;
        counterValue = new MutableLiveData<>();

        counterThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (isCounterInProgress) {
                    try {
                        Thread.sleep(1000);
                        count++;
                        counterValue.postValue(count);
                        Log.i(TAG,"Counter in progress: "+count);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    public LiveData<Integer> getCounterValue(){
        return counterValue;
    }

    public void startCounter(){
        if(!isCounterInProgress){
            isCounterInProgress = true;
            counterThread.start();
        }
    }

    public void stopCounter(){
        isCounterInProgress = false;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private void functionalityBasedOnSomeLifeCyclerEventInActivity(){
        Log.i(TAG,"Execute this method when Activity is resumed");
    }
}
