package com.self.androidarchdemos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    MainActivityViewModel mainActivityViewModel;

    Button startButton, stopButton;

    TextView textViewCounter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainActivityViewModel = ViewModelProviders
                .of(this)
                .get(MainActivityViewModel.class);

        mainActivityViewModel.getCounterValue()
                .observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                textViewCounter.setText("Counter: "+integer);
            }
        });

        getLifecycle().addObserver(mainActivityViewModel);

        textViewCounter = (TextView)findViewById(R.id.textViewCunter);
        startButton = (Button)findViewById(R.id.start);
        stopButton = (Button)findViewById(R.id.stop);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivityViewModel.startCounter();
            }
        });

        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivityViewModel.stopCounter();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
