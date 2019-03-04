package com.example.PinnacleAnimations.room.Product;

import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.os.AsyncTask;

import java.util.List;

public class Repository {

    private ProductDao productDao;
    private static Repository INSTANCE;
    private LiveData<List<Product>> productList;
    private LiveData<List<Product>> cartProductList;


    public static Repository getInstance(Context context){
        if(INSTANCE == null){
            INSTANCE = new Repository(context.getApplicationContext());
        }
        return INSTANCE;
    }

    private Repository (Context application){
        ProductDatabase database = ProductDatabase.getInstance(application);
        productDao = database.productDao();
    }

    public void insertProduct(Product product){
        new InsertProductData(productDao).execute(product);
    }

    public LiveData<List<Product>> getProducts() {
        return productDao.getAllProducts();
    }

    public void addToCart(int imgId){
        new InsertCartProduct(productDao).execute(imgId);
    }

    public void deleteFromCart(int imgId){
        new DeleteCartProduct(productDao).execute(imgId);
    }

    public LiveData<List<Product>> getCartProducts(){
        return productDao.getCartProducts();
    }

    public void addToOrder(int imgId){
        new InsertOrderProduct(productDao).execute(imgId);
    }

    public LiveData<List<Product>> getOrderProducts(){
        return productDao.getOrderProducts();
    }

    public void cancelOrder(int imgId){
        new RemoveOrderProduct(productDao).execute(imgId);
    }

    class InsertProductData extends AsyncTask<Product, Void, Void > {

        ProductDao productDao;

        public InsertProductData(ProductDao productDao) {
            this.productDao = productDao;
        }

        @Override
        protected Void doInBackground(Product... product) {
            productDao.insertProduct(product[0]);
            return null;
        }
    }


    class InsertCartProduct extends AsyncTask<Integer, Void, Void>{

        ProductDao productDao;
        public InsertCartProduct(ProductDao productDao) {
            this.productDao = productDao;
        }

        @Override
        protected Void doInBackground(Integer... integers) {
            productDao.addToCart(integers[0]);
            return null;
        }
    }

    private class DeleteCartProduct extends AsyncTask<Integer, Void, Void> {

        ProductDao productDao;
        public DeleteCartProduct(ProductDao productDao) {
            this.productDao = productDao;
        }

        @Override
        protected Void doInBackground(Integer... integers) {
            productDao.deleteFromCart(integers[0]);
            return null;
        }
    }

    private class InsertOrderProduct extends AsyncTask<Integer, Void,Void>{

        ProductDao productDao;
        public InsertOrderProduct(ProductDao productDao) {
            this.productDao = productDao;
        }

        @Override
        protected Void doInBackground(Integer... integers) {
            productDao.addToOrder(integers[0]);
            return null;
        }
    }


    private class RemoveOrderProduct extends AsyncTask<Integer, Void, Void>{

        ProductDao productDao;
        public RemoveOrderProduct(ProductDao productDao) {
            this.productDao = productDao;
        }

        @Override
        protected Void doInBackground(Integer... integers) {
            productDao.cancelOrder(integers[0]);
            return null;
        }
    }
}
