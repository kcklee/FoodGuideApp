package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

// Represents a collection of food locations to try
public class FoodGuide {

    private List<FoodLocation> foodLocations;

    // TODO: fill in specifications
    // EFFECTS: create FoodGuide object
    public FoodGuide() {
        foodLocations = new ArrayList<>();
    }

    // TODO: fill in specifications
    // MODIFIES: this
    // EFFECTS: adds the given food location into foodLocations, given the food location is not already in the list
    //           return true if successful insertion, else return false
    public boolean insert(FoodLocation foodLocation) {
        for (FoodLocation fl : foodLocations) {
            if (Objects.equals(fl.getName(), foodLocation.getName())) {
                return false;
            }
        }
        foodLocations.add(foodLocation);
        return true;
    }


    // TODO: fill in specifications
    // REQUIRES:
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

//    public boolean remove(String name) {
////        for (FoodLocation fl : foodLocations) {
////            if (fl.getName().equals(name)) {
////                foodLocations.remove(fl);
////                return true;
////            }
////        }
////        return false;
//    }

    ;

//}


//    // TODO: fill in specifications
//    // REQUIRES: foodLocations contains the food location with the given name
//    // EFFECTS: returns the food location with given name
//    public FoodLocation retrieve(String name) {
//        return null;
//    }

    // EFFECTS: returns size of FoodGuide
    public int size() {
        return foodLocations.size();
    }

    // TODO: fill in specifications


    // EFFECTS: returns true if list contains the given food location, else false
    public boolean contains(FoodLocation fl) {
        return foodLocations.contains(fl);
    }

    // getters
    public List<FoodLocation> getFoodLocations() {
        return foodLocations;
    }

}
