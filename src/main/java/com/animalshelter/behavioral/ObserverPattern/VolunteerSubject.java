package com.animalshelter.patterns.observer;

public interface VolunteerSubject {
    void registerObserver(VolunteerObserver observer);
    void removeObserver(VolunteerObserver observer);
    void notifyObservers(String task);
}
