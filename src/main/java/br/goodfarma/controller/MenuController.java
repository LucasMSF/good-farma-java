package br.goodfarma.controller;


import br.goodfarma.MainApplication;
import br.goodfarma.enumerable.Views;

public class MenuController {
    public void onBtnProductClick() {
        MainApplication.navigate(Views.PRODUCT);
    }

    public void onBtnProductTableClick() {
        MainApplication.navigate(Views.PRODUCT_TABLE);
    }

    public void onBtnEmployClick() {
        MainApplication.navigate(Views.EMPLOY);
    }

    public void onBtnSupplierClick() {
        MainApplication.navigate(Views.SUPPLIER);
    }
}