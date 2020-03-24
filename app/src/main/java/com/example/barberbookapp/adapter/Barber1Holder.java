package com.example.barberbookapp.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.barberbookapp.R;

public class Barber1Holder extends RecyclerView.ViewHolder implements View.OnClickListener{
    TextView tName;
    TextView tPhone;
    TextView tStatus;
    ItemClick itemClickListener;
    Barber1Holder(@NonNull View itemView) {
        super(itemView);
        this.tName=itemView.findViewById(R.id.txtName);
        this.tPhone=itemView.findViewById(R.id.txtPhone);
        this.tStatus=itemView.findViewById(R.id.txtStatus);
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
