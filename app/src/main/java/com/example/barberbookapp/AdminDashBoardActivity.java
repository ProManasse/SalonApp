package com.example.barberbookapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.barberbookapp.adapter.BarberAdapter;
import com.example.barberbookapp.api.ApiClient;
import com.example.barberbookapp.domain.Barber;
import com.example.barberbookapp.domain.Salon;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminDashBoardActivity extends AppCompatActivity {
    private ArrayList<Salon> salons;
    private Button viewAs,manageClients,changeStatus;
    private TextView salonTt;
    public static String nm;
    public static String oid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dash_board);
        viewAs=(Button) findViewById(R.id.buttonview_as_client);
        manageClients=(Button) findViewById(R.id.button_manage_clients);
        salonTt=(TextView) findViewById(R.id.salonName);
        salons=new ArrayList<>();
        Intent intent=getIntent();
        Bundle currIntent=intent.getExtras();
        nm=currIntent.getString("owner_nm");
        salonTt.setText(nm+"");
        final Bundle intentExtras=intent.getExtras();
        manageClients.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                oid=intentExtras.getString("owner_id");
                loadSalons(oid);
                //startActivity(new Intent(AdminDashBoardActivity.this,MainActivity.class).putExtra("o_id",oid));
                //Toast.makeText(AdminDashBoardActivity.this,oid+"",Toast.LENGTH_LONG).show();
            }
        });
    }
    private void loadSalons(final String salon){
        Call<ArrayList<Salon>> call= ApiClient.getInstance().getApi().getSalon(salon);
        call.enqueue(new Callback<ArrayList<Salon>>() {
            @Override
            public void onResponse(Call<ArrayList<Salon>> call, Response<ArrayList<Salon>> response) {
                salons=response.body();
                if(!salons.isEmpty()){
                    setContentView(R.layout.activity_admin_dash_board);
                    //Toast.makeText(AdminDashBoardActivity.this,salons.get(0).getSalon_id()+"",Toast.LENGTH_LONG).show();
                    startActivity(new Intent(AdminDashBoardActivity.this,MainActivity.class).putExtra("o_id",salons.get(0).getSalon_id()+""));
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Salon>> call, Throwable t) {
                //Toast.makeText(MainActivity.this,t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }
}
