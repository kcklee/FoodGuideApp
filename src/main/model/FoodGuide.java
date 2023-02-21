package model;

import java.util.ArrayList;
import java.util.List;

// Represents a collection of food locations to try
public class FoodGuide {

    private List<FoodLocation> foodLocations;

    // TODO: fill in specifications
    // EFFECTS: construct a FoodGuide with no food locations added
    public FoodGuide() {
        foodLocations = new ArrayList<>();
    }

    // TODO: fill in specifications
    // MODIFIES: this
    // EFFECTS: adds the food location into foodLocations,
    //          given a food location with the same name isn't already in the list
    //          return true if successful insertion, else return false
    public boolean insert(FoodLocation foodLocation) {
        for (FoodLocation fl : foodLocations) {
            if (fl.getName().equals(foodLocation.getName())) {
                return false;
            }
        }
        foodLocations.add(foodLocation);
        return true;
    }


    // TODO: fill in specifications
    // MODIFIES: this
    // EFFECTS: removes the given food location from foodLocations
    //          return true if successful removal, else return false
    public boolean remove(FoodLocation foodLocation) {
        for (FoodLocation fl : foodLocations) {
            if (fl.equals(foodLocation)) {
                foodLocations.remove(fl);
                return true;
            }
        }
        return false;
    }

    // EFFECTS: returns size of FoodGuide
    public int size() {
        return foodLocations.size();
    }

    // TODO: fill in specifications
    // EFFECTS: returns true if FoodGuide contains the given food location, else false
    public boolean contains(FoodLocation fl) {
        return foodLocations.contains(fl);
    }

    // getters

    // EFFECTS: returns the list of food locations in FoodGuide
    public List<FoodLocation> getFoodLocations() {
        return foodLocations;
    }

}
