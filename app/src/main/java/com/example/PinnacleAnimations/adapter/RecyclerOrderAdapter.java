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
import com.example.PinnacleAnimations.fragment.OrdersFragment;
import com.example.PinnacleAnimations.listener.ProductClickListener;
import com.example.PinnacleAnimations.room.Product.Product;

import java.util.ArrayList;
import java.util.List;

public class RecyclerOrderAdapter extends RecyclerView.Adapter<RecyclerOrderAdapter.MyViewHolder> {

    List<Product> orderProductList = new ArrayList<>();
    OrdersFragment context;
    ProductClickListener listener;

    public RecyclerOrderAdapter(OrdersFragment context) {
        this.context = context;
        listener = context;
    }

    @NonNull
    @Override
    public RecyclerOrderAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.order_item_view, viewGroup, false);

        return new RecyclerOrderAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerOrderAdapter.MyViewHolder myViewHolder, int i) {

        final Product product = orderProductList.get(i);

        Glide.with(context).load(product.getImgId()).into(myViewHolder.productImgOrder);
        myViewHolder.productNameOrder.setText(product.getName());
        myViewHolder.removeBtnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(product);
            }
        });
    }

    @Override
    public int getItemCount() {
        return orderProductList.size();
    }

    public void swap(List<Product> products) {
        orderProductList.clear();
        orderProductList = products;
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView productImgOrder;
        TextView productNameOrder;
        Button removeBtnOrder;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            productImgOrder = itemView.findViewById(R.id.productImgOrder);
            productNameOrder = itemView.findViewById(R.id.productNameOrder);
            removeBtnOrder = itemView.findViewById(R.id.removeBtnOrder);
        }
    }
}
