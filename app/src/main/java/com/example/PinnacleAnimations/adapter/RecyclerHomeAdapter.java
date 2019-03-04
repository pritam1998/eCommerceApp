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
import com.example.PinnacleAnimations.fragment.HomeFragment;
import com.example.PinnacleAnimations.listener.ProductClickListener;
import com.example.PinnacleAnimations.room.Product.Product;
import com.example.PinnacleAnimations.R;

import java.util.ArrayList;
import java.util.List;

public class RecyclerHomeAdapter extends RecyclerView.Adapter<RecyclerHomeAdapter.MyViewHolder> {

    private List<Product> productList = new ArrayList<>();
    HomeFragment context;
    ProductClickListener productClickListener;


    public RecyclerHomeAdapter(HomeFragment context) {
        this.context = context;
        productClickListener = context;
    }

    @NonNull
    @Override
    public RecyclerHomeAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).
                inflate(R.layout.home_item_view, viewGroup, false);
        return new RecyclerHomeAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerHomeAdapter.MyViewHolder myViewHolder, int i) {

        final Product product = productList.get(i);

        Glide.with(context).load(product.getImgId()).fitCenter().into(myViewHolder.productImg);
        myViewHolder.productImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                productClickListener.onClick(product);
            }
        });
        myViewHolder.productName.setText(product.getName());
        if (product.isInCart() == true){
            myViewHolder.addCartBtn.setText("ADDED TO CART");
        }
        else {
            myViewHolder.addCartBtn.setText("ADD TO CART");
        }
        myViewHolder.addCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                productClickListener.onClickBtn(v,product);
            }
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public void swap(List<Product> products) {
        productList.clear();
        productList = products;
        notifyDataSetChanged();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView productImg;
        TextView productName;
        Button addCartBtn;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            productImg = itemView.findViewById(R.id.productImgRecycler);
            productName = itemView.findViewById(R.id.productNameRecycler);
            addCartBtn = itemView.findViewById(R.id.addToCartBtnRecycler);
        }
    }
}
