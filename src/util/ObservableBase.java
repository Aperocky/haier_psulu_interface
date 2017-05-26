package util;

import java.util.HashSet;
import java.util.Set;

/**
 * Provides a skeletal implementation of the <code>Observable</code> interface
 *
 */
public class ObservableBase<T> implements Observable<T> {
    
    private transient Set<Observer<T>> myObservers;

    public ObservableBase() {
        myObservers = new HashSet<>();
    }

    @Override
    public void addObserver(Observer<T> obs) {
        myObservers.add(obs);
    }
    
    public void addObservers(Observer<T>... obss) {
    	for(Observer<T> obs : obss) 
    		myObservers.add(obs);
    }

    @Override
    public void removeObserver(Observer<T> obs) {
        myObservers.remove(obs);
    }

    @Override
    public void notifyObservers(T arg) {
        for (Observer<T> obs : myObservers) {
            obs.update(arg);
        }
    }

    protected boolean containsObserver(Observer<T> obs) {
        return myObservers.contains(obs);
    }

    protected Object readResolve() {
        myObservers = new HashSet<>();
        return this;
    }

}
