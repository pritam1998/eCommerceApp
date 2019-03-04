package com.example.PinnacleAnimations.listener;

import android.view.View;

import com.example.PinnacleAnimations.room.Product.Product;

public interface ProductClickListener {
    void onClick(Product product);
    void onClickBtn(View view, Product product);
}
