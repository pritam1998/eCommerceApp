package com.example.PinnacleAnimations.fragment;


import android.app.AlertDialog;
import android.arch.lifecycle.Observer;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.PinnacleAnimations.activity.ProductActivity;
import com.example.PinnacleAnimations.room.Product.Repository;
import com.example.PinnacleAnimations.listener.ProductClickListener;
import com.example.PinnacleAnimations.room.Product.Product;
import com.example.PinnacleAnimations.R;
import com.example.PinnacleAnimations.adapter.RecyclerHomeAdapter;

import java.util.List;

public class HomeFragment extends Fragment implements ProductClickListener {

    View view;
    RecyclerView recyclerView;
    RecyclerHomeAdapter adapter;
    Repository repository;
    Button cartBtn;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        repository = Repository.getInstance(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = view.findViewById(R.id.recyclerViewHome);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        adapter = new RecyclerHomeAdapter(this);
        recyclerView.setAdapter(adapter);

        return view;

    }


    @Override
    public void onStart() {
        super.onStart();

        repository.getProducts().observe(this, new Observer<List<Product>>() {
            @Override
            public void onChanged(@Nullable List<Product> products) {
                adapter.swap(products);
            }
        });

    }

    @Override
    public void onClick(Product product) {

        Intent intent = new Intent(getContext(), ProductActivity.class);
        intent.putExtra("PRODUCT", product);
//        intent.putExtra("isHome", true);
        startActivity(intent);
    }

    @Override
    public void onClickBtn(View view, Product product) {
        cartBtn = (Button) view;
        final int imgId = product.getImgId();

        if (!product.isInCart()){
            repository.addToCart(product.getImgId());
            cartBtn.setText("Added to Cart");
        }
        else {
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setTitle("Remove from Cart?");
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    repository.deleteFromCart(imgId);
                    cartBtn.setText("Add to Cart");
                }
            });
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            builder.show();
        }
    }
}
