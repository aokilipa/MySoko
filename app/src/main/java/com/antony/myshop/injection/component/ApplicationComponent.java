package com.antony.myshop.injection.component;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import com.antony.myshop.data.DataManager;
import com.antony.myshop.data.SyncService;
import com.antony.myshop.data.local.DatabaseHelper;
import com.antony.myshop.data.local.PreferencesHelper;
import com.antony.myshop.data.remote.STKPushService;
import com.antony.myshop.injection.ApplicationContext;
import com.antony.myshop.injection.module.ApplicationModule;
import com.antony.myshop.util.RxEventBus;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(SyncService syncService);

    @ApplicationContext
    Context context();
    Application application();
    STKPushService stkPushService();
    PreferencesHelper preferencesHelper();
    DatabaseHelper databaseHelper();
    DataManager dataManager();
    RxEventBus eventBus();

}
