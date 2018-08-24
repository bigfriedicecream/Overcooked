package com.bigfriedicecream.recipes.presenters;


import com.bigfriedicecream.recipes.interfaces.IMainContract;

public class MainPresenter implements IMainContract.Presenter {

    private IMainContract.View view;

    public MainPresenter(IMainContract.View v) {
        view = v;
    }
}
