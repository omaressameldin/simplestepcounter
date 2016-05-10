package com.example.oessa_000.simplestepcounter;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    private SensorManager mSensorManager;
    private Sensor mStepCounterSensor;

    ToggleButton countToggle;
    TextView stepView;

    private int stepCount = 0;
    boolean toggle = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mStepCounterSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);

        stepView = (TextView)  findViewById(R.id.stepView);
         countToggle = (ToggleButton) findViewById(R.id.countToggle);
        ;

        countToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggle = !toggle ;
                if(toggle){
                startListening();
                }
                else{
                    stopListening();
                }
            }
        });
    }
    protected void startListening() {

        mSensorManager.registerListener(this, mStepCounterSensor, SensorManager.SENSOR_DELAY_NORMAL);


    }

    protected void stopListening() {
        mSensorManager.unregisterListener(this, mStepCounterSensor);
    }



    public void onResume() {
        super.onResume();

    }


    @Override
    public void onSensorChanged(SensorEvent event) {
        Sensor sensor = event.sensor;
        switch(sensor.getType()) {
            case Sensor.TYPE_STEP_COUNTER:
                if (toggle){
                    stepCount++;
                    stepView.setText("Step Count: " + stepCount);
                }
                break;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
