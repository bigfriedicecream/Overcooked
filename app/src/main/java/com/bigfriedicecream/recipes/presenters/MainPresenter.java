package com.bigfriedicecream.recipes.presenters;


import com.bigfriedicecream.recipes.interfaces.IMainContract;
import com.bigfriedicecream.recipes.observables.EventsDispatcher;

import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

public class MainPresenter implements IMainContract.Presenter, Observer {

    private IMainContract.View view;
    private EventsDispatcher eventsRepository;

    public MainPresenter(IMainContract.View v) {
        view = v;
    }

    public void start() {
        eventsRepository = EventsDispatcher.getInstance();
        eventsRepository.addObserver(this);
        view.render();
    }

    public void stop() {
        eventsRepository.deleteObserver(this);
    }

    @Override
    public void update(Observable observable, Object o) {
        HashMap<String, String> hashMap = (HashMap<String, String>)o;
        if (observable instanceof EventsDispatcher) {
            System.out.println(hashMap.get("name") + hashMap.get("id"));
        }
    }
}
