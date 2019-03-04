package com.example.PinnacleAnimations.fragment;


import android.app.AlertDialog;
import android.arch.lifecycle.Observer;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.PinnacleAnimations.R;
import com.example.PinnacleAnimations.adapter.RecyclerOrderAdapter;
import com.example.PinnacleAnimations.listener.ProductClickListener;
import com.example.PinnacleAnimations.room.Product.Product;
import com.example.PinnacleAnimations.room.Product.Repository;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class OrdersFragment extends Fragment implements ProductClickListener {

    View view;
    RecyclerView recyclerView;
    RecyclerOrderAdapter adapter;
    Repository repository;


    public OrdersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_orders, container, false);
        recyclerView = view.findViewById(R.id.recyclerViewOrder);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new RecyclerOrderAdapter(this);
        recyclerView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        repository = Repository.getInstance(getContext());
    }

    @Override
    public void onStart() {
        super.onStart();

        repository.getOrderProducts().observe(this, new Observer<List<Product>>() {
            @Override
            public void onChanged(@Nullable List<Product> products) {
                adapter.swap(products);
            }
        });
    }

    @Override
    public void onClick(Product product) {

        final int imgId = product.getImgId();

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Are you sure?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                repository.cancelOrder(imgId);
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.show();
    }

    @Override
    public void onClickBtn(View view, Product product) {

    }
}
