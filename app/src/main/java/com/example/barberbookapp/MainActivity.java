package com.example.barberbookapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import com.example.barberbookapp.adapter.BarberAdapter;
import com.example.barberbookapp.api.ApiClient;
import com.example.barberbookapp.domain.Barber;
import com.example.barberbookapp.domain.Salon;
import com.example.barberbookapp.sharedpreferences.SharedPreferencesConfig;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    BarberAdapter adapter;
    ArrayList<Barber> barbers;
    //Switch aSwitch;
    Button chStatus;
    private SharedPreferencesConfig preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        preferences=new SharedPreferencesConfig(getApplicationContext());
        Intent intent=getIntent();
        Bundle intentExtras=intent.getExtras();
        String ownerId=intentExtras.getString("o_id");
        Toast.makeText(MainActivity.this,ownerId,Toast.LENGTH_LONG).show();
        loadData(ownerId);
    }

    private void loadData(String salon){
        Call<ArrayList<Barber>> call= ApiClient.getInstance().getApi().getBarbersBySalon(salon);
        call.enqueue(new Callback<ArrayList<Barber>>() {
            @Override
            public void onResponse(Call<ArrayList<Barber>> call, Response<ArrayList<Barber>> response) {
                barbers=response.body();
                setContentView(R.layout.activity_main);
                recyclerView= findViewById(R.id.recyclerV);
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                adapter=new BarberAdapter(MainActivity.this,barbers);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<ArrayList<Barber>> call, Throwable t) {
                Toast.makeText(MainActivity.this,t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }
    public void changeSalonStatus(Salon salon){
        Call<ResponseBody> book= ApiClient.getInstance().getApi().updateSalon(salon);
        book.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                String resp= null;
                if (response.body() != null) {
                    //resp = response.body();
                    //Toast.makeText(ClientActivity.this,resp.toString(),Toast.LENGTH_LONG).show();
                }else{
                    //Toast.makeText(ClientActivity.this,"wrong",Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                //Toast.makeText(ClientActivity.this,t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }

    public void logout(){
        preferences.login_status(false);
        startActivity(new Intent(MainActivity.this,LoginActivity.class));
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menus,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int it=item.getItemId();
        switch (it){
            case R.id.logout:
                logout();
                break;
        }
       return true;
    }
}
