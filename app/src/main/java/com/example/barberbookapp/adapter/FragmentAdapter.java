package com.example.barberbookapp.adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.barberbookapp.fragment.MapFragment;

public class FragmentAdapter extends FragmentPagerAdapter {

    Context context;

    public FragmentAdapter(@NonNull FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if(position==0)
            return MapFragment.getINSTANCE();
        else
            return null;
    }

    @Override
    public int getCount() {
        return 1;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch(position){
            case 0:
                return "Map FRAG";
            case 1:
                return "Other FRAG";
        }
        return "";
    }
}
