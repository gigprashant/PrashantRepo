package com.mycompany.observerDesignPatterns;



/**
 *
 * @author prashant.adhikari
 */
public interface Subject {
    //methods to register and unregister services
    public void register(Observer obj);
    public void unregister(Observer obj);

    //method to notify observers of change
    public void notifyObservers();

    //method to get updates from subject
    public Object getUpdate(Observer obj);
}
