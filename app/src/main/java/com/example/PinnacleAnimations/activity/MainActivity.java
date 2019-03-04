package com.example.PinnacleAnimations.activity;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.example.PinnacleAnimations.R;
import com.example.PinnacleAnimations.adapter.ViewPagerAdapter;
import com.example.PinnacleAnimations.room.Product.Product;
import com.example.PinnacleAnimations.room.Product.Repository;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavView;
    Toolbar toolbar;
    FloatingActionButton fab;
    ViewPager viewPager;
    MenuItem prevMenuItem;
    DrawerLayout drawerLayout;
    NavigationView navView;
    Repository repository;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawerLayout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);

        if (drawerLayout != null){
            drawerLayout.addDrawerListener(toggle);
        }
        toggle.syncState();

        navView = findViewById(R.id.navView);

        bottomNavView = findViewById(R.id.bottomNav);

        fab = findViewById(R.id.fab);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));

        bottomNavView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()){
                    case R.id.home:
                        viewPager.setCurrentItem(0);
                        return true;
                    case R.id.cart:
                        viewPager.setCurrentItem(1);
                        return true;
                    case R.id.orders:
                        viewPager.setCurrentItem(2);
                        return true;
                    case R.id.profile:
                        viewPager.setCurrentItem(3);
                        return true;
                }
                return false;
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoActivity(getApplicationContext(), SearchActivity.class);
            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {

                    if (prevMenuItem != null)
                        prevMenuItem.setChecked(false);
                    else
                        bottomNavView.getMenu().getItem(0).setChecked(false);

                    bottomNavView.getMenu().getItem(i).setChecked(true);
                    prevMenuItem = bottomNavView.getMenu().getItem(i);

                }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        repository = Repository.getInstance(this);
        prepareProductData();

    }

    public void gotoActivity(Context context, Class<?> activity){
        Intent intent = new Intent(context, activity);
        startActivity(intent);
    }

    private void prepareProductData() {
        List<Product> products = new ArrayList<>();
        products.add(new Product(R.drawable.tshirt, "Jack & Jones Crew Neck", "Desciption", false, false));
        products.add(new Product(R.drawable.jacket, "Natty India Woolen", "Desciption", false, false));
        products.add(new Product(R.drawable.bag, "American Tourister", "Desciption", true, false));
        products.add(new Product(R.drawable.cargo, "Roadster Cargo", "Desciption", false, false));
        products.add(new Product(R.drawable.laptop, "Hp Laptop", "Desciption", false, false));
        products.add(new Product(R.drawable.shoes, "Adidas Sneakers", "Desciption", false, false));
        products.add(new Product(R.drawable.speaker, "Mi Bluetooth Speakers", "Desciption", false, false));
        products.add(new Product(R.drawable.watch, "FasTrack watch", "Desciption", false, false));

        for (int i = 0, size = products.size(); i < size; i++) {
            repository.insertProduct(products.get(i));
        }

    }

}
