package com.example.PinnacleAnimations.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.PinnacleAnimations.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

    View view;
    ImageView profileImg;
    Button loginBtn;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =inflater.inflate(R.layout.fragment_profile, container, false);
        profileImg = view.findViewById(R.id.profileImg);
        loginBtn = view.findViewById(R.id.loginBtn);
        Glide.with(this).load(R.drawable.pinacle).into(profileImg);

        return view;
    }

}
