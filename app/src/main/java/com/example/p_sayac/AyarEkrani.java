package com.example.p_sayac;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

public class AyarEkrani extends AppCompatActivity {

    Button btn_ayar_plus, btn_ayar_plus2, btn_ayar_minus, btn_ayar_minus2;
    CheckBox chb_ayar_vibrate, chb_ayar_vibrate2, chb_ayar_sound, chb_ayar_sound2;
    EditText editTextNumberSigned_ayar_value, editTextNumberSigned_ayar_value2;

    AyarSinifi ayarSinifi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ayar_ekrani);

        btn_ayar_plus = (Button) findViewById(R.id.btn_ayar_plus);
        btn_ayar_minus = (Button) findViewById(R.id.btn_ayar_minus);
        btn_ayar_plus2 = (Button) findViewById(R.id.btn_ayar_plus2);
        btn_ayar_minus2 = (Button) findViewById(R.id.btn_ayar_minus2);
        chb_ayar_vibrate = (CheckBox) findViewById(R.id.chb_ayar_vibrate);
        chb_ayar_vibrate2 = (CheckBox) findViewById(R.id.chb_ayar_vibrate2);
        chb_ayar_sound = (CheckBox) findViewById(R.id.chb_ayar_sound);
        chb_ayar_sound2 = (CheckBox) findViewById(R.id.chb_ayar_sound2);
        editTextNumberSigned_ayar_value = (EditText) findViewById(R.id.editTextNumberSigned_ayar_value);
        editTextNumberSigned_ayar_value2 = (EditText) findViewById(R.id.editTextNumberSigned_ayar_value2);

        ayarSinifi = AyarSinifi.AyarSinifi(getApplicationContext());

        btn_ayar_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ayarSinifi.upperLimit++;
                editTextNumberSigned_ayar_value.setText(String.valueOf(ayarSinifi.upperLimit));
            }
        });
        btn_ayar_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ayarSinifi.upperLimit--;
                editTextNumberSigned_ayar_value.setText(String.valueOf(ayarSinifi.upperLimit));
            }
        });
        btn_ayar_plus2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ayarSinifi.lowerLimit++;
                editTextNumberSigned_ayar_value2.setText(String.valueOf(ayarSinifi.lowerLimit));
            }
        });
        btn_ayar_minus2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ayarSinifi.lowerLimit--;
                editTextNumberSigned_ayar_value2.setText(String.valueOf(ayarSinifi.lowerLimit));
            }
        });
        chb_ayar_vibrate.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                ayarSinifi.topVib = isChecked;
            }
        });
        chb_ayar_sound.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                ayarSinifi.topSound = isChecked;
            }
        });
        chb_ayar_vibrate2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                ayarSinifi.bottomVib = isChecked;
            }
        });
        chb_ayar_sound2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                ayarSinifi.bottomSound = isChecked;
            }
        });
        editTextNumberSigned_ayar_value.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editTextNumberSigned_ayar_value.getText().toString().length() != 0) {
                    ayarSinifi.upperLimit = Integer.parseInt(editTextNumberSigned_ayar_value.getText().toString());
                }

            }
        });
        editTextNumberSigned_ayar_value2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editTextNumberSigned_ayar_value2.getText().toString().length() != 0) {
                    ayarSinifi.lowerLimit = Integer.parseInt(editTextNumberSigned_ayar_value.getText().toString());
                }

            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        editTextNumberSigned_ayar_value.setText(String.valueOf(ayarSinifi.upperLimit));
        editTextNumberSigned_ayar_value2.setText(String.valueOf(ayarSinifi.lowerLimit));
        chb_ayar_vibrate.setText(String.valueOf(ayarSinifi.topVib));
        chb_ayar_vibrate2.setText(String.valueOf(ayarSinifi.bottomVib));
        chb_ayar_sound.setText(String.valueOf(ayarSinifi.topSound));
        chb_ayar_sound2.setText(String.valueOf(ayarSinifi.bottomSound));
    }


    @Override
    protected void onPause() {
        super.onPause();
        ayarSinifi.saveValues();

    }
}
