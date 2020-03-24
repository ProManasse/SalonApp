package com.example.barberbookapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.barberbookapp.R;
import com.example.barberbookapp.SalonActivity;
import com.example.barberbookapp.domain.Salon;
import com.example.barberbookapp.fragment.MapFragment;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

public class SalonAdapter  extends RecyclerView.Adapter<SalonHolder>{
    Context context;
    ArrayList<Salon> model;
    MapFragment mapFragment;
    public SalonAdapter(final Context context,final ArrayList<Salon> data) {
        this.context = context;
        this.model=data;
    }

    @NonNull
    @Override
    public SalonHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.salon_row,null);
        return new SalonHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SalonHolder holder, int position) {
        Salon salon = model.get(position);
        //Log.d("onBindViewHolder:",salon.getSalon_loc());
        System.out.println(salon.getSalon_location());
        final String sid=String.valueOf(salon.getSalon_id());
        final String sname=String.valueOf(salon.getSalon_name());
        holder.sName.setText(sname);
        String sloc=String.valueOf(salon.getSalon_location());
        holder.sLoc.setText(sloc);
        final String sstat=String.valueOf(salon.getSalon_status());
        holder.sStatus.setText(sstat);
        final String lat=String.valueOf(salon.getLatitude());
        final String lon=String.valueOf(salon.getLongitude());
        mapFragment=new MapFragment();
        holder.setItemClickListener(new ItemClick() {
            @Override
            public void onClickListener(View v, int layoutPosition) {
                //Toast.makeText(context,sstat,Toast.LENGTH_LONG).show();
                Intent intent=new Intent(context, SalonActivity.class);
                intent.putExtra("salon name",sname);
                intent.putExtra("salon id",sid);
                intent.putExtra("lat",lat);
                intent.putExtra("lon",lon);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return model.size();
    }


    public double distance(double lat1, double lat2, double lon1, double lon2) {
        // The math module contains a function
        // named toRadians which converts from
        // degrees to radians.
        lon1 = Math.toRadians(lon1);
        lon2 = Math.toRadians(lon2);
        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);
        // Haversine formula
        double dlon = lon2 - lon1;
        double dlat = lat2 - lat1;
        double a = Math.pow(Math.sin(dlat / 2), 2) + Math.cos(lat1) * Math.cos(lat2) * Math.pow(Math.sin(dlon / 2),2);
        double c = 2 * Math.asin(Math.sqrt(a));
        // Radius of earth in kilometers. Use 3956
        // for miles
        double r = 6371;

        // calculate the result
        return(c * r);
    }
}
