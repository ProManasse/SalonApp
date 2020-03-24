package com.example.barberbookapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StartPageActivity extends AppCompatActivity {
    private Button adminPath;
    private Button clientPath;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_page);
        adminPath=(Button) findViewById(R.id.adminPath);
        clientPath=(Button) findViewById(R.id.clientPath);
        adminPath.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StartPageActivity.this,LoginActivity.class));
            }
        });
        clientPath.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StartPageActivity.this,HomePageActivity.class));
            }
        });
    }
}
