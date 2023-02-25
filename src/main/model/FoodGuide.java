package model;

import java.util.ArrayList;
import java.util.List;

// Represents a collection of food locations to try
public class FoodGuide {

    private List<FoodLocation> foodLocations;

    // EFFECTS: constructs a food guide with no food locations added
    public FoodGuide() {
        foodLocations = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: - adds given food location into food locations list, if a food location with a matching name
    //          isn't already in food locations list
    //          - return true if successful insertion, else return false
    public boolean insert(FoodLocation foodLocation) {
        for (FoodLocation fl : foodLocations) {
            if (fl.getName().equals(foodLocation.getName())) {
                return false;
            }
        }
        foodLocations.add(foodLocation);
        return true;
    }

    // MODIFIES: this
    // EFFECTS: - removes the given food location from food locations list
    //          - return true if successful removal, else return false
    public boolean remove(FoodLocation foodLocation) {
        for (FoodLocation fl : foodLocations) {
            if (fl.equals(foodLocation)) {
                foodLocations.remove(fl);
                return true;
            }
        }
        return false;
    }

    // EFFECTS: returns number of food locations in the food guide
    public int length() {
        return foodLocations.size();
    }

    // EFFECTS: returns true if the food guide contains the given food location, else false
    public boolean contains(FoodLocation fl) {
        return foodLocations.contains(fl);
    }

    // getters

    // EFFECTS: returns the list of food locations in the food guide
    public List<FoodLocation> getFoodLocations() {
        return foodLocations;
    }

}
