package com.example.barberbookapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.barberbookapp.AdminClientActivity;
import com.example.barberbookapp.R;
import com.example.barberbookapp.domain.Barber;

import java.util.ArrayList;

public class BarberAdapter extends RecyclerView.Adapter<BarberHolder>{
    Context context;
    ArrayList<Barber> model;
    public BarberAdapter(Context context, ArrayList<Barber> model) {
        this.context = context;
        this.model = model;
    }
    @NonNull
    @Override
    public BarberHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.row,null);
        return new BarberHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BarberHolder holder, int position) {
        Barber barber = model.get(position);
        Log.d("onBindViewHolder:",barber.getfName());
        final String bid=String.valueOf(barber.getBarber_id());
        final String fnm=String.valueOf(barber.getfName());
        holder.tName.setText(fnm);
        String lnm=String.valueOf(barber.getlName());
        holder.tStatus.setText(lnm);
        String pn=String.valueOf(barber.getPhoneNo());
        holder.tPhone.setText(pn);

        holder.setItemClickListener(new ItemClick() {
            @Override
            public void onClickListener(View v, int layoutPosition) {
                Intent intent=new Intent(context, AdminClientActivity.class);
                intent.putExtra("barber id",bid);
                intent.putExtra("barber name",fnm);
                context.startActivity(intent);
                //context.startActivity(new Intent(context, AdminClientActivity.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return model.size();
    }
}
