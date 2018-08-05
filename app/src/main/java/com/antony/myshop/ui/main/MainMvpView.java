package com.antony.myshop.ui.main;

import java.util.List;

import com.antony.myshop.data.model.Product;
import com.antony.myshop.ui.base.MvpView;

public interface MainMvpView extends MvpView {

    void showProduct(List<Product> products);

    void showProductEmpty();

    void showError();

}
