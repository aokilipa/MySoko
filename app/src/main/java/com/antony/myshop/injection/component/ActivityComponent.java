package com.antony.myshop.injection.component;

import dagger.Subcomponent;
import com.antony.myshop.injection.PerActivity;
import com.antony.myshop.injection.module.ActivityModule;
import com.antony.myshop.ui.main.MainActivity;

/**
 * This component inject dependencies to all Activities across the application
 */
@PerActivity
@Subcomponent(modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(MainActivity mainActivity);

}
