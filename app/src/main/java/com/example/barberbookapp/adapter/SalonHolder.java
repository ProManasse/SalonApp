package com.example.barberbookapp.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.barberbookapp.R;

public class SalonHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    TextView sName;
    TextView sLoc;
    TextView sStatus;
    ItemClick itemClickListener;
    SalonHolder(@NonNull View itemView) {
        super(itemView);
        this.sName=itemView.findViewById(R.id.txtSalonName);
        this.sLoc=itemView.findViewById(R.id.txtSalonLoc);
        this.sStatus=itemView.findViewById(R.id.txtSalonStatus);
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

