package com.mycompany.observerDesignPatterns;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author prashant.adhikari
 */
public class MyTopic implements Subject {

    private List<Observer> observers;
    private String message;
    private boolean changed;
    private final Object MUTEX = new Object();

    public MyTopic() {
        this.observers = new ArrayList<Observer>();
    }

    public void register(Observer obj) {
        if (obj == null) {
            throw new NullPointerException("Null Observer");
        }
        synchronized (MUTEX) {
            if (!observers.contains(obj)) {
                observers.add(obj);
            }
        }
    }

    public void unregister(Observer obj) {
        synchronized (MUTEX) {
            observers.remove(obj);
        }
    }

    public void notifyObservers() {
        List<Observer> observersLocal = null;
        //synchronization is used to make sure any observer registered after message is received is not notified
        synchronized (MUTEX) {
            if (!changed) {
                return;
            }
            observersLocal = new ArrayList<Observer>(this.observers);
            this.changed = false;

            for (Observer observer : observersLocal) {
                observer.update();
            }

        }
    }

    public Object getUpdate(Observer obj) {
        return this.message;
    }
    
    
    //method to post message to the topic
    public void postMessage(String message){
        System.out.println("Message posted to topic: "+ message);
        this.message = message;
        this.changed = true; 
        notifyObservers();
    }
    
    
}