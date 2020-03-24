package com.example.barberbookapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.barberbookapp.adapter.ClientAdapter;
import com.example.barberbookapp.api.ApiClient;
import com.example.barberbookapp.domain.Client;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminClientActivity extends AppCompatActivity {
    private RecyclerView clientRecycler;
    private ArrayList<Client> adminClients;
    private ClientAdapter clientAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_client);
        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        final String barber=bundle.getString("barber id");
        //Toast.makeText(AdminClientActivity.this,barber,Toast.LENGTH_LONG).show();
        loadData(barber);

    }
    private void loadData(String barber){
        Call<ArrayList<Client>> call= ApiClient.getInstance().getApi().getClients(barber);
        call.enqueue(new Callback<ArrayList<Client>>() {
            @Override
            public void onResponse(Call<ArrayList<Client>> call, Response<ArrayList<Client>> response) {
                adminClients=response.body();
                setContentView(R.layout.activity_home_page);
                clientRecycler= findViewById(R.id.recyclerVSalons);
                clientRecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                clientAdapter=new ClientAdapter(AdminClientActivity.this,adminClients);
                clientRecycler.setAdapter(clientAdapter);
            }


            @Override
            public void onFailure(Call<ArrayList<Client>> call, Throwable t) {
                //Toast.makeText(HomePageActivity.this,t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }
}
