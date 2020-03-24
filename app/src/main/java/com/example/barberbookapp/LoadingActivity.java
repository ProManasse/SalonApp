package com.example.barberbookapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class LoadingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        Thread thread=new Thread(){
            public void run(){
                try{
                    super.run();
                    sleep(10000);
                }
                catch (Exception e){

                }
                finally {
                    Intent i=new Intent(getApplicationContext(),StartPageActivity.class);
                    startActivity(i);
                    finish();
                }
            }
        };
        thread.start();
    }
}
