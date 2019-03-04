package com.example.PinnacleAnimations.room.Product;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.PinnacleAnimations.room.Product.Product;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.IGNORE;

@Dao
public interface ProductDao {

    @Insert(onConflict = IGNORE)
    void insertProduct(Product product);

    @Query("SELECT * from products")
    LiveData<List<Product>> getAllProducts();

    @Query("UPDATE products set isInCart = 1 where imgId = :imgId")
    void addToCart(int imgId);

    @Query("SELECT * from products where isInCart IS 1")
    LiveData<List<Product>> getCartProducts();

    @Query("UPDATE products set isInCart = 0 where imgId = :imgId")
    void deleteFromCart(int imgId);

    @Query("UPDATE products set ordered = 1 where imgId = :imgId")
    void addToOrder(int imgId);

    @Query("SELECT * from products where ordered is 1")
    LiveData<List<Product>> getOrderProducts();

    @Query("UPDATE products set ordered = 0 where imgId = :imgId")
    void cancelOrder(int imgId);
}
