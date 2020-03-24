package com.example.barberbookapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.barberbookapp.api.ApiClient;
import com.example.barberbookapp.domain.Account;
import com.example.barberbookapp.domain.Owner;
import com.example.barberbookapp.sharedpreferences.SharedPreferencesConfig;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    List<Owner> owners=new ArrayList<>();
    EditText edtPhone;
    EditText edtPassword;
    Button btnLogin;
    //login
    SharedPreferencesConfig preferencesConfig;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edtPhone=(EditText) findViewById(R.id.editTPhone);
        edtPassword=(EditText) findViewById(R.id.editTPassword);
        btnLogin=(Button) findViewById(R.id.buttonLogin);
        preferencesConfig=new SharedPreferencesConfig(getApplicationContext());
        if(preferencesConfig.read_login_status()) {
            startActivity(new Intent(this, MainActivity.class));
        }

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edtPhone.getText().toString().length()!=0 && edtPassword.getText().toString().length()!=0){
                    String phoneNo=edtPhone.getText().toString();
                    String password=edtPassword.getText().toString();
                    checkCridentials(phoneNo,password);
                }else {
                    Toast.makeText(LoginActivity.this,"Fill above fields!",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void checkCridentials(final String ph, final String ps){
        Call<ArrayList<Owner>> call= ApiClient.getInstance().getApi().getOwners();
        call.enqueue(new Callback<ArrayList<Owner>>() {
            @Override
            public void onResponse(Call<ArrayList<Owner>> call, Response<ArrayList<Owner>> response) {
                owners=response.body();
                for (Owner o:owners){
                    if(o.getPhoneNo().equals(ph) && o.getPassword().equals(ps)){
                        Toast.makeText(LoginActivity.this,o.getOwner_id()+""+o.getfName(),Toast.LENGTH_LONG).show();
                        startActivity(new Intent(getApplicationContext(),AdminDashBoardActivity.class).putExtra("owner_nm",o.getfName()+" "+o.getlName()).putExtra("owner_id",o.getOwner_id()+""));
 //                       preferencesConfig.login_status(true);
//                        finish();
                        break;
                    }else {
                        //Toast.makeText(LoginActivity.this,"wrong cridential!",Toast.LENGTH_LONG).show();
                    }
                }
            }
            @Override
            public void onFailure(Call<ArrayList<Owner>> call, Throwable t) {
                Toast.makeText(LoginActivity.this,t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
