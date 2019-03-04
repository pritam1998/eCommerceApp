package com.example.PinnacleAnimations.room.Product;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {Product.class}, version = 1, exportSchema = false)
public abstract class ProductDatabase extends RoomDatabase {

    public abstract ProductDao productDao();

    private static ProductDatabase INSTANCE;

    public static ProductDatabase getInstance(Context context){

        if (INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    ProductDatabase.class, "product-database")
                    .build();
        }

        return INSTANCE;
    }

    public void destroyINSTANCE(){
        INSTANCE = null;
    }

}
