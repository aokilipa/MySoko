package com.antony.myshop.data;

import com.antony.myshop.R;
import com.antony.myshop.data.local.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.antony.myshop.data.local.PreferencesHelper;
import com.antony.myshop.data.model.Product;
import com.antony.myshop.data.remote.STKPushService;

@Singleton
public class DataManager {

    private final STKPushService mSTKPushService;
    private final DatabaseHelper mDatabaseHelper;
    private final PreferencesHelper mPreferencesHelper;

    List<Product> mProduct;

    @Inject
    public DataManager(STKPushService stkPushService, PreferencesHelper preferencesHelper,
                       DatabaseHelper databaseHelper) {
        mSTKPushService = stkPushService;
        mPreferencesHelper = preferencesHelper;
        mDatabaseHelper = databaseHelper;
    }

    public PreferencesHelper getPreferencesHelper() {
        return mPreferencesHelper;
    }


    public void getProducts(){
        mProduct  = new ArrayList<>();
        mProduct.add(new Product("Tile","Description",100.00, R.drawable.ic_canon));
        mProduct.add(new Product("Tile","Description",100.00, R.drawable.ic_canon));
    }

}
