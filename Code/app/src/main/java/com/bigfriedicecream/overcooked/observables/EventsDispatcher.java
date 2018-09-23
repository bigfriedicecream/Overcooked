package com.bigfriedicecream.overcooked.observables;

import java.util.HashMap;
import java.util.Observable;

public class EventsDispatcher extends Observable {

    private static EventsDispatcher instance;

    private EventsDispatcher() {}

    public static EventsDispatcher getInstance() {
        if (instance == null) {
            instance = new EventsDispatcher();
        }
        return instance;
    }

    public void dispatch(String name, String id) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("name", name);
        hashMap.put("id", id);
        setChanged();
        notifyObservers(hashMap);
    }
}
