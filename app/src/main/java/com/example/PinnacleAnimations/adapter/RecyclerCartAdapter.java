package com.example.PinnacleAnimations.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.PinnacleAnimations.R;
import com.example.PinnacleAnimations.fragment.CartFragment;
import com.example.PinnacleAnimations.listener.ProductClickListener;
import com.example.PinnacleAnimations.room.Product.Product;

import java.util.ArrayList;
import java.util.List;

public class RecyclerCartAdapter extends RecyclerView.Adapter<RecyclerCartAdapter.MyViewHolder>  {

    List<Product> cartProductList = new ArrayList<>();
    CartFragment context;
    ProductClickListener listener;

    public RecyclerCartAdapter(CartFragment context) {
        this.context = context;
        listener = context;
    }


    @NonNull
    @Override
    public RecyclerCartAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.cart_item_view, viewGroup, false);
        return new RecyclerCartAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerCartAdapter.MyViewHolder myViewHolder, int i) {
        final Product product = cartProductList.get(i);

        Glide.with(context).load(product.getImgId()).into(myViewHolder.productImgCart);
        myViewHolder.productNameCart.setText(product.getName());
        myViewHolder.orderBtnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClickBtn(v, product);
            }
        });
        myViewHolder.removeBtnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(product);
            }
        });
    }

    @Override
    public int getItemCount() {
        return cartProductList.size();
    }

    public void swap(List<Product> products) {
        cartProductList.clear();
        cartProductList = products;
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView productImgCart;
        TextView productNameCart;
        Button orderBtnCart;
        Button removeBtnCart;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            productImgCart = itemView.findViewById(R.id.productImgCart);
            productNameCart = itemView.findViewById(R.id.productNameCart);
            orderBtnCart = itemView.findViewById(R.id.orderBtnCart);
            removeBtnCart = itemView.findViewById(R.id.removeBtnCart);
        }
    }
}
