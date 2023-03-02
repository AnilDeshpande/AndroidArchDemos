package com.self.androidarchdemos;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainActivityViewModel extends ViewModel {

    Thread counterThread;
    int count = 0;
    boolean isCounterInProgress;

    MutableLiveData<Integer> counterValue;

    public MainActivityViewModel(){
        isCounterInProgress = false;
        counterValue = new MutableLiveData<>();
    }

    public LiveData<Integer> getCounterValue(){
        return counterValue;
    }

    public void startCounter(){
        if(!isCounterInProgress){
            isCounterInProgress = true;

            counterThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (isCounterInProgress) {
                        try {
                            Thread.sleep(1000);
                            count++;
                            counterValue.postValue(count);
                        }catch (InterruptedException e){
                            e.printStackTrace();
                        }

                    }
                }
            });
            counterThread.start();
        }
    }

    public void stopCounter(){
        isCounterInProgress = false;
    }
}
