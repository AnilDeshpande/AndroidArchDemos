package com.self.androidarchdemos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();


    Thread counterThread;
    int count = 0;
    boolean isCounterInProgress;

    Button startButton, stopButton;

    TextView textViewCounter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        isCounterInProgress = false;

        counterThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (isCounterInProgress) {
                    try {
                        Thread.sleep(1000);
                        count++;
                        textViewCounter.post(new Runnable() {
                            @Override
                            public void run() {
                                textViewCounter.setText("Counter: "+count);
                            }
                        });
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }

                }
            }
        });


        textViewCounter = (TextView)findViewById(R.id.textViewCunter);
        startButton = (Button)findViewById(R.id.start);
        stopButton = (Button)findViewById(R.id.stop);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!isCounterInProgress){
                    isCounterInProgress = true;
                    counterThread.start();
                }

            }
        });

        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isCounterInProgress = false;
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
