package com.example.barberbookapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.widget.Toast;

import com.example.barberbookapp.adapter.SalonAdapter;
import com.example.barberbookapp.api.ApiClient;
import com.example.barberbookapp.domain.Salon;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomePageActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    SalonAdapter adapter;
    ArrayList<Salon> salons;
    FusedLocationProviderClient client;
    double[] coords;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        client= LocationServices.getFusedLocationProviderClient(this);
        requestPermission();
        userPosition();
        coords=new double[2];
        userPosition();
        //loadData();
    }
    private void requestPermission(){
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},1);
    }
    public void userPosition(){
//        if(ActivityCompat.checkSelfPermission(HomePageActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_DENIED)
//            return;
        client.getLastLocation().addOnSuccessListener(HomePageActivity.this, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                coords[0]=location.getLatitude();
                coords[1]=location.getLongitude();
                loadData(coords[0],coords[1]);
                //loadData();
                Toast.makeText(HomePageActivity.this,coords[0]+" of longitude",Toast.LENGTH_LONG).show();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(HomePageActivity.this,"no data",Toast.LENGTH_LONG).show();
            }
        });
    }

    private void loadData(double lat,double lon){
       // private void loadData(){
        Call<ArrayList<Salon>> call= ApiClient.getInstance().getApi().getNearSalons(lat,lon);
        call.enqueue(new Callback<ArrayList<Salon>>() {
            @Override
            public void onResponse(Call<ArrayList<Salon>> call, Response<ArrayList<Salon>> response) {
                salons=response.body();
                setContentView(R.layout.activity_home_page);
                recyclerView= findViewById(R.id.recyclerVSalons);
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                adapter=new SalonAdapter(HomePageActivity.this,salons);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<ArrayList<Salon>> call, Throwable t) {
                Toast.makeText(HomePageActivity.this,t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }

}
