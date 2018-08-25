package com.bigfriedicecream.recipes.presenters;


import com.bigfriedicecream.recipes.interfaces.IMainContract;
import com.bigfriedicecream.recipes.observables.EventsDispatcher;
import com.bigfriedicecream.recipes.observables.RecipeRepository;

import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

public class MainPresenter implements IMainContract.Presenter, Observer {

    private IMainContract.View view;
    private EventsDispatcher eventsDispatcher;

    public MainPresenter(IMainContract.View v) {
        view = v;
        eventsDispatcher = EventsDispatcher.getInstance();
    }

    public void start() {
        eventsDispatcher.addObserver(this);
        view.renderList();
    }

    public void stop() {
        eventsDispatcher.deleteObserver(this);
    }

    @Override
    public void update(Observable observable, Object o) {
        HashMap<String, String> hashMap = (HashMap<String, String>)o;
        if (observable instanceof EventsDispatcher) {
            // System.out.println(hashMap.get("name") + hashMap.get("id"));
            if (hashMap.get("name").equals("NAVIGATION_RECIPE")) {
                view.renderRecipe();
            }
        }
    }
}
