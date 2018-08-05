package com.antony.myshop;

import android.app.Application;
import android.content.Context;

import com.antony.myshop.injection.component.DaggerApplicationComponent;

import com.crashlytics.android.Crashlytics;
import io.fabric.sdk.android.Fabric;

import timber.log.Timber;

import com.antony.myshop.injection.component.ApplicationComponent;

import com.antony.myshop.injection.module.ApplicationModule;

public class MyShopApplication extends Application  {

    ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
            Fabric.with(this, new Crashlytics());
        }
    }

    public static MyShopApplication get(Context context) {
        return (MyShopApplication) context.getApplicationContext();
    }

    public ApplicationComponent getComponent() {
        if (mApplicationComponent == null) {
            mApplicationComponent = DaggerApplicationComponent.builder()
                    .applicationModule(new ApplicationModule(this))
                    .build();
        }
        return mApplicationComponent;
    }

    // Needed to replace the component with a test specific one
    public void setComponent(ApplicationComponent applicationComponent) {
        mApplicationComponent = applicationComponent;
    }
}
