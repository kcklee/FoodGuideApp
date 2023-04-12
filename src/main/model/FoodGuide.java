package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

// Represents a collection of food locations to try
// using code adapted from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo where indicated
public class FoodGuide implements Writable {

    private String name;

    private List<FoodLocation> foodLocations;

    // EFFECTS: constructs a food guide with a name and no food locations added
    public FoodGuide(String name) {
        this.name = name;
        foodLocations = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: - adds given food location into food locations list, if a food location with a matching name
    //          isn't already in food locations list
    //          - return true if successful insertion, else return false
    //          - add this insertion event into the event log
    public boolean insert(FoodLocation foodLocation) {
        for (FoodLocation fl : foodLocations) {
            if (fl.getName().equals(foodLocation.getName())) {
                return false;
            }
        }
        foodLocations.add(foodLocation);
        EventLog.getInstance().logEvent(new Event(foodLocation.getName() + " added to Food Guide"));
        return true;
    }

    // MODIFIES: this
    // EFFECTS: - removes the given food location from food locations list
    //          - return true if successful removal, else return false
    //          - add this removal event into the event log
    public boolean remove(FoodLocation foodLocation) {
        for (FoodLocation fl : foodLocations) {
            if (fl.equals(foodLocation)) {
                foodLocations.remove(fl);
                EventLog.getInstance().logEvent(new Event(fl.getName() + " removed from Food Guide"));
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

    // EFFECTS: constructs a JSON Object with the name of the food guide and food locations (as a JSON array)
    //          and returns the JSON object
    // using code adapted from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("foodLocations", foodLocationsToJson());
        return json;
    }

    // EFFECTS: returns food locations in this food guide as a JSON array
    // using code adapted from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    private JSONArray foodLocationsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (FoodLocation fl : foodLocations) {
            jsonArray.put(fl.toJson());
        }

        return jsonArray;
    }

    // getters

    // EFFECTS: returns the list of food locations in the food guide
    public List<FoodLocation> getFoodLocations() {
        return foodLocations;
    }

    // EFFECTS: returns the name of the food guide
    public String getName() {
        return this.name;
    }

}
