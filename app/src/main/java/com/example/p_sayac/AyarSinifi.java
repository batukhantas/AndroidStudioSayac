package com.example.p_sayac;

import android.content.Context;
import android.content.SharedPreferences;

public class AyarSinifi {
    int upperLimit,lowerLimit,currentValue;
    boolean topVib,topSound,bottomVib,bottomSound;

    static AyarSinifi ayarSinifi=null;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;



    private AyarSinifi(Context context){
        sharedPreferences=context.getSharedPreferences("ayarsinifi",Context.MODE_PRIVATE);
        editor=sharedPreferences.edit();
        loadValues();

    }
    public static AyarSinifi AyarSinifi(Context context){
        if(ayarSinifi==null){
            ayarSinifi=new AyarSinifi(context);
        }
        return ayarSinifi;
    }




    public void saveValues(){
        editor.putInt("upperLimit",upperLimit);
        editor.putInt("lowerLimit",lowerLimit);
        editor.putInt("currentValue",currentValue);
        editor.putBoolean("topVib",topVib);
        editor.putBoolean("topSound",topSound);
        editor.putBoolean("bottomVib",bottomVib);
        editor.putBoolean("bottomSound",bottomSound);
        editor.commit();
    }
    public void loadValues(){
        upperLimit=sharedPreferences.getInt("upperLimit",5);
        lowerLimit=sharedPreferences.getInt("lowerLimit",0);
        currentValue=sharedPreferences.getInt("currentValue",0);
        topVib=sharedPreferences.getBoolean("topVib",false);
        topSound=sharedPreferences.getBoolean("topSound",false);
        bottomVib=sharedPreferences.getBoolean("bottomVib",false);
        bottomSound=sharedPreferences.getBoolean("bottomSound",false);

    }





}
