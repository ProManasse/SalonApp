package com.example.barberbookapp.sharedpreferences;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.barberbookapp.R;

public class SharedPreferencesConfig {
    private SharedPreferences preferences;
    private Context context;

    public SharedPreferencesConfig(Context context){
        this.context=context;
        preferences=context.getSharedPreferences(context.getResources().getString(R.string.login_preferences), 0);
    }
    public void login_status(boolean status){
        SharedPreferences.Editor editor=preferences.edit();
        editor.putBoolean(context.getResources().getString(R.string.login_preferences),status);
        editor.commit();
    }
    public boolean read_login_status(){
        boolean status=false;
        status=preferences.getBoolean(context.getResources().getString(R.string.login_preferences),status);
        return status;
    }
}
