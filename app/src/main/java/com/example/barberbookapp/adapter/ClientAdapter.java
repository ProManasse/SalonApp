package com.example.barberbookapp.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.barberbookapp.R;
import com.example.barberbookapp.api.ApiClient;
import com.example.barberbookapp.domain.Client;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ClientAdapter extends RecyclerView.Adapter<ClientHolder> {
    Context context;
    ArrayList<Client> model;
    public ClientAdapter(Context context, ArrayList<Client> model) {
        this.context = context;
        this.model = model;
    }
    @NonNull
    @Override
    public ClientHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.clients_row,null);
        return new ClientHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClientHolder holder, int position) {
        final Client client= model.get(position);
        Log.d("onBindViewHolder:",client.getDate());
        final String cid=String.valueOf(client.getId());
        final String fnm=String.valueOf(client.getfName());
        String lnm=String.valueOf(client.getlName());
        holder.tName.setText(fnm+" "+lnm);
        final String cStat=String.valueOf(client.getStatus());
        holder.tStatus.setText(cStat);
        String cDate=String.valueOf(client.getDate());
        holder.tDate.setText(cDate);
        String cTime=String.valueOf(client.getTime());
        holder.tTime.setText(cTime);

        holder.setItemClickListener(new ItemClick() {
            @Override
            public void onClickListener(View v, int layoutPosition) {
                 updateOneClient(cid);
                 Toast.makeText(context,cid+"updted successfully!",Toast.LENGTH_LONG).show();
             }
        });
    }

    @Override
    public int getItemCount() {
        return model.size();
    }

    public void updateOneClient(String cid){
        Call<String> book= ApiClient.getInstance().getApi().updateClient(cid);
        book.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
//                String resp= null;
//                if (response.body() != null) {
//                    resp = response.body();
//                    Toast.makeText(context,resp,Toast.LENGTH_LONG).show();
//                }else{
//                    //Toast.makeText(ClientActivity.this,"wrong",Toast.LENGTH_LONG).show();
//                }
            }
            @Override
            public void onFailure(Call<String> call, Throwable t) {
                //Toast.makeText(ClientActivity.this,t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }
}

