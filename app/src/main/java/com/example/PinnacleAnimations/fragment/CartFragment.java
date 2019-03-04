package com.example.PinnacleAnimations.fragment;


import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.PinnacleAnimations.R;
import com.example.PinnacleAnimations.activity.ProductActivity;
import com.example.PinnacleAnimations.adapter.RecyclerCartAdapter;
import com.example.PinnacleAnimations.listener.ProductClickListener;
import com.example.PinnacleAnimations.room.Product.Product;
import com.example.PinnacleAnimations.room.Product.Repository;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class CartFragment extends Fragment  implements ProductClickListener {

    View view;
    Repository repository;
    RecyclerView recyclerView;
    RecyclerCartAdapter adapter;

    public CartFragment() {
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
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_cart, container, false);
        recyclerView = view.findViewById(R.id.recyclerViewCart);
        adapter = new RecyclerCartAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        repository.getCartProducts().observe(this, new Observer<List<Product>>() {
            @Override
            public void onChanged(@Nullable List<Product> products) {
                adapter.swap(products);
            }
        });
    }


    @Override
    public void onClick(Product product) {
        repository.deleteFromCart(product.getImgId());

    }

    @Override
    public void onClickBtn(View view, Product product) {
        Intent intent = new Intent(getContext(), ProductActivity.class);
        intent.putExtra("PRODUCT", product);
        startActivity(intent);
    }
}
