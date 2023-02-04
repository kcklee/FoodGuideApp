package model;

import java.util.List;

// Represents a collection of food locations to try
public class FoodGuide {

    private List<FoodLocation> foodLocations;

    // TODO: fill in specifications
    // EXECUTES: create FoodGuide object
    public FoodGuide() {

    }

    // TODO: fill in specifications
    // REQUIRES: foodLocations contains the food location with the given name
    // EXECUTES: returns the food location with given name
    public FoodLocation retrieve(String name) {
        return null;
    }

    // TODO: fill in specifications
    // MODIFIES: this
    // EXECUTES: adds the given food location into foodLocations, given the food location is not already in the list
    //           return true if successful insertion, else return false
    public boolean insert(FoodLocation fl) {
        return false;

    }

    // TODO: fill in specifications
    // REQUIRES: foodLocations contains the given food location with given name
    // MODIFIES: this
    // EXECUTES: removes the food location with given name from foodLocations
    //           return true if successful removal, else return false
    public boolean remove(String name) {
        return false;

    }

    // TODO: fill in specifications


    // EXECUTES: returns size of FoodGuide
    public int size() {
        return 0;
    }

    // TODO: fill in specifications


    // EXECUTES: returns true if list contains the given food location, else false
    public boolean contains(FoodLocation fl) {
        return false;
    }


    // TODO: fill in specifications
    // EXECUTES: prints all the food locations that have already been tried
    public void printAlreadyTried() {
    }

    // TODO: fill in specifications
    // EXECUTES: prints all the food locations in the food guide
    public void printAll() {
    }


}
