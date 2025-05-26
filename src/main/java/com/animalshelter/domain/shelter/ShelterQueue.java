package com.animalshelter.domain.adoptions;

import com.animalshelter.domain.animals.Animal;

import java.util.LinkedList;
import java.util.Queue;

public class ShelterQueue {
    private Queue<Animal> animalQueue;

    /**
     * Default constructor without parameters.
     */
    public ShelterQueue() {
        this.animalQueue = new LinkedList<>();
    }

    /**
     * Adds an animal to the end of the queue.
     * @param animal
     */
    public void enqueueAnimal(Animal animal) {
        if (animal != null && !animal.isAdopted()) {
            animalQueue.add(animal);
        } else {
            throw new IllegalArgumentException("Animal must be valid and cannot be adopted");
        }
    }

    /**
     * Removes and returns the animal at the front of the queue.
     * @return animal at the front of the queue, or null if the queue is empty
     */
    public Animal dequeueAnimal() {
        return animalQueue.poll();
    }

    /**
     * Returns the animal at the front of the queue.
     * @return animal at the front of the queue, or null if the queue is empty
     */
    public Animal peekNext() {
        return animalQueue.peek();
    }

    /**
     * Checks if the queue is empty.
     * @return boolean of if the queue is empty
     */
    public boolean isEmpty() {
        return animalQueue.isEmpty();
    }

    /**
     * Gets the current size of the queue.
     * @return animalQueue size
     */
    public int size() {
        return animalQueue.size();
    }
}
