package com.example.barberbookapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.barberbookapp.api.ApiClient;
import com.example.barberbookapp.domain.Client;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ClientActivity extends AppCompatActivity {
    private EditText fname;
    private EditText lname;
    private Button btnBook;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);
        fname=(EditText) findViewById(R.id.cfname);
        lname=(EditText) findViewById(R.id.clname);
        btnBook=(Button) findViewById(R.id.btnBook);
        btnBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=getIntent();
                Bundle bundle=intent.getExtras();
                if(fname.getText().toString().length()!=0 && lname.getText().toString().length()!=0){
                    Client c=new Client();
                    c.setfName(fname.getText().toString());
                    c.setlName(lname.getText().toString());
                    c.setbNumber(bundle.getString("barber id"));
                    bookBarber(c);
                }
            }
        });
    }

    public void bookBarber(Client client){
        Call<String> book= ApiClient.getInstance().getApi().bookBarber(client);
        book.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String resp= null;
                if (response.body() != null) {
                    resp = response.body();
                    Toast.makeText(ClientActivity.this,resp.toString(),Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(ClientActivity.this,"wrong",Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(ClientActivity.this,t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }
}
