package com.example.p_sayac;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class GirisEkrani extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giris_ekrani);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
             startActivity(new Intent(GirisEkrani.this, MainActivity.class));
            }
        },2000);








    }
}