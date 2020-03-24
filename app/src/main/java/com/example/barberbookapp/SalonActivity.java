package com.example.barberbookapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.barberbookapp.adapter.Barber1Adapter;
import com.example.barberbookapp.adapter.FragmentAdapter;
import com.example.barberbookapp.adapter.SalonAdapter;
import com.example.barberbookapp.api.ApiClient;
import com.example.barberbookapp.domain.Barber;
import com.example.barberbookapp.fragment.MapFragment;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SalonActivity extends AppCompatActivity{
    TextView textView;
    RecyclerView recyclerView;
    ArrayList<Barber> barbers;
    Barber1Adapter adapter;
    ViewPager viewPager;
    FragmentAdapter fragmentAdapter;
    String lat,lon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salon);
        String choosenSalon=getIntent().getStringExtra("salon id");
        lat=getIntent().getStringExtra("lat");
        lon=getIntent().getStringExtra("lon");
        loadData(choosenSalon);
        //showLoc();

    }
    public LatLng getCoors(){
        LatLng latLng=new LatLng(Double.parseDouble(lat),Double.parseDouble(lon));
        return latLng;
    }
    public void showLoc(){
        viewPager=(ViewPager) findViewById(R.id.viewPager);
        fragmentAdapter=new FragmentAdapter(getSupportFragmentManager(),this);
        viewPager.setAdapter(fragmentAdapter);
    }
    private void loadData(String salon){
        Call<ArrayList<Barber>> call= ApiClient.getInstance().getApi().getBarbersBySalon(salon);
        call.enqueue(new Callback<ArrayList<Barber>>() {
            @Override
            public void onResponse(Call<ArrayList<Barber>> call, Response<ArrayList<Barber>> response) {
                barbers=response.body();
                setContentView(R.layout.activity_salon);
                recyclerView= findViewById(R.id.recyclerBarbers);
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                adapter=new Barber1Adapter(SalonActivity.this,barbers);
                recyclerView.setAdapter(adapter);
                textView=(TextView) findViewById(R.id.salonTitle);
                String title=getIntent().getStringExtra("salon name");
                textView.setText(title);
                showLoc();
            }

            @Override
            public void onFailure(Call<ArrayList<Barber>> call, Throwable t) {
                Toast.makeText(SalonActivity.this,t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }
}
