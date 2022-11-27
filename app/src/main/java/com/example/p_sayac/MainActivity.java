package com.example.p_sayac;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button btn_minus,btn_plus,btn_settings;
    TextView txt_baslik,txt_value;


    AyarSinifi ayarSinifi;

    Vibrator vibrator=null;
    MediaPlayer mediaPlayer=null;

    SensorManager sensorManager;
    Sensor sensorShake;
    SensorEventListener sensorEventListener;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_minus = findViewById(R.id.btn_minus);
        btn_plus = findViewById(R.id.btn_plus);
        btn_settings = findViewById(R.id.btn_settings);
        txt_value = findViewById(R.id.txt_value);

        Context context=getApplicationContext();
        ayarSinifi =AyarSinifi.AyarSinifi(context);

        vibrator=(Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        mediaPlayer = MediaPlayer.create(context,R.raw.phone);

        btn_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uptadeValue(-3);
            }
        });
        btn_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uptadeValue(3);

            }
        });
        btn_settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            startActivity(new Intent(MainActivity.this,AyarEkrani.class));
            }
        });

        sensorManager=(SensorManager)getSystemService(SENSOR_SERVICE);
        sensorShake=sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorEventListener= new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {

                float x = sensorEvent.values[0];
                float y = sensorEvent.values[1];
                float z = sensorEvent.values[2];

                float sum = Math.abs(x) + Math.abs(y) + Math.abs(z);
                if (sum > 19) {
                    ayarSinifi.currentValue = ayarSinifi.lowerLimit;
                    txt_value.setText(String.valueOf(ayarSinifi.currentValue));
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        };





    }
    public void uptadeValue(int step){
        if(step<0){
            if(ayarSinifi.currentValue+step<ayarSinifi.lowerLimit){
                ayarSinifi.currentValue=ayarSinifi.lowerLimit;
                if(ayarSinifi.bottomVib){
                    alertVib();
                }
                if(ayarSinifi.bottomSound){
                    alertSound();
                }

            }
            else
                ayarSinifi.currentValue+=step;
        }
        if(step>0){
            if(ayarSinifi.currentValue-step>ayarSinifi.upperLimit) {
                ayarSinifi.currentValue = ayarSinifi.upperLimit;
                if (ayarSinifi.bottomVib) {
                    alertVib();
                }
                if (ayarSinifi.bottomSound) {
                    alertSound();
                }
            }
            else
                ayarSinifi.currentValue-=step;
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        txt_value.setText(String.valueOf(ayarSinifi.currentValue));
        sensorManager.registerListener(sensorEventListener,sensorShake,SensorManager.SENSOR_DELAY_NORMAL);

    }


    @Override
    protected void onPause() {
        super.onPause();
        ayarSinifi.saveValues();
        sensorManager.unregisterListener(sensorEventListener,sensorShake);

    }
    public void alertSound(){
        mediaPlayer.seekTo(0);
        mediaPlayer.start();
    }
    public void alertVib(){
        if(vibrator.hasVibrator()){
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    vibrator.vibrate(VibrationEffect.createOneShot(1000,VibrationEffect.DEFAULT_AMPLITUDE));
                }
            }
            vibrator.vibrate(1000);
        }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        int action=event.getAction();
        int keyCode=event.getKeyCode();
        switch (keyCode) {
            case KeyEvent.KEYCODE_VOLUME_DOWN:
                if (action == KeyEvent.ACTION_DOWN)
                    uptadeValue(-5);
                    return true;
            case KeyEvent.KEYCODE_VOLUME_UP:
                if (action == KeyEvent.ACTION_DOWN)
                    uptadeValue(5);
                    return true;

        }
        return super.dispatchKeyEvent(event);
    }
}




