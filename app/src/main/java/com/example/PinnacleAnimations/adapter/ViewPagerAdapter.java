package com.example.PinnacleAnimations.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.PinnacleAnimations.fragment.CartFragment;
import com.example.PinnacleAnimations.fragment.OrdersFragment;
import com.example.PinnacleAnimations.fragment.HomeFragment;
import com.example.PinnacleAnimations.fragment.ProfileFragment;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {

        switch (i){
            case 0:
                return new HomeFragment();
            case 1:
                return new CartFragment();
            case 2:
                return new OrdersFragment();
            case 3:
                return new ProfileFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 4;
    }


}
