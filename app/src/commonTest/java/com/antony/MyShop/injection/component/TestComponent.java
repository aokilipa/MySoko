package com.antony.MyShop.injection.component;

import javax.inject.Singleton;

import dagger.Component;

import com.antony.MyShop.injection.module.ApplicationTestModule;
import com.antony.myshop.injection.component.ApplicationComponent;

@Singleton
@Component(modules = ApplicationTestModule.class)
public interface TestComponent extends ApplicationComponent {

}
