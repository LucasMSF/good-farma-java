package br.goodfarma.controller;


import br.goodfarma.MainApplication;
import br.goodfarma.enumerable.Views;
import br.goodfarma.helper.Message;

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

    public void onBtnBackClick() {
        MainApplication.navigate(Views.MENU);
    }

    public void onBtnExitClick() {
        if(!Message.confirm("Deseja realmente sair?")) {
            return;
        }
        MainApplication.navigate(Views.AUTH);
    }
}