package com.example.barberbookapp.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.barberbookapp.R;

public class ClientHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    TextView tName;
    TextView tDate;
    TextView tStatus;
    TextView tTime;

    ItemClick itemClickListener;
    ClientHolder(@NonNull View itemView) {
        super(itemView);
        this.tName=itemView.findViewById(R.id.txtName);
        this.tDate=itemView.findViewById(R.id.txtDate);
        this.tStatus=itemView.findViewById(R.id.txtStatus);
        this.tTime=itemView.findViewById(R.id.txtTime);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        this.itemClickListener.onClickListener(v,getLayoutPosition());
    }

    public void setItemClickListener(ItemClick itc){
        this.itemClickListener=itc;
    }
}
