package com.example.PinnacleAnimations.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.PinnacleAnimations.R;
import com.example.PinnacleAnimations.room.Product.Product;
import com.example.PinnacleAnimations.room.Product.Repository;

public class ProductActivity extends AppCompatActivity {

    ImageView imgView;
    TextView nameView;
    TextView descView;
    Button orderBtn;
    Repository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        imgView = findViewById(R.id.productImg);
        nameView = findViewById(R.id.productName);
        descView = findViewById(R.id.productDesc);
        orderBtn = findViewById(R.id.orderBtn);
        repository = Repository.getInstance(this);

        Intent intent = getIntent();
        final Product product = (Product) intent.getSerializableExtra("PRODUCT");
        Glide.with(this).load(product.getImgId()).into(imgView);
        nameView.setText(product.getName());
        descView.setText(product.getProductDesc());

        orderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(ProductActivity.this);
                builder.setTitle("Confirm Order?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(ProductActivity.this, "Succesfully Ordered", Toast.LENGTH_SHORT).show();
                        repository.addToOrder(product.getImgId());
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();
            }
        });
    }
}
