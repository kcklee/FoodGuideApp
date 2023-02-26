package model;

import org.json.JSONObject;
import persistence.Writable;

// Represents a food location that has a name, neighbourhood, type, website
// and indicates whether it has been visited or not
public class FoodLocation implements Writable {

    private String name;
    private String neighbourhood;
    private String type;
    private String website;
    private boolean haveVisited;

    // REQUIRES: name, neighbourhood, type, and website are non-zero length
    // EFFECTS: constructs a food location with given name, neighbourhood, type and website
    public FoodLocation(String name, String neighbourhood, String type, String website, boolean haveVisited) {
        this.name = name;
        this.neighbourhood = neighbourhood;
        this.type = type;
        this.website = website;
        this.haveVisited = haveVisited;
    }

    // TODO
    // REQUIRES
    // MODIFIES
    // EFFECTS
    // using code adapted from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("neighbourhood", neighbourhood);
        json.put("type", type);
        json.put("website", website);
        json.put("haveVisited", haveVisited);
        return json;
    }

    // setters

    // MODIFIES: this
    // EFFECTS: set whether the food location has been visited already
    public void setHaveVisited(boolean haveVisited) {
        this.haveVisited = haveVisited;

    }

    // getters

    // EFFECTS: get name of the food location
    public String getName() {
        return this.name;
    }

    // EFFECTS: get neighbourhood of the food location
    public String getNeighborhood() {
        return this.neighbourhood;
    }

    // EFFECTS: get type of the food location
    public String getType() {
        return this.type;
    }

    // EFFECTS: get website of the food location
    public String getWebsite() {
        return this.website;
    }

    // EFFECTS: get status of whether or not the food location has already been visited
    public boolean getHaveVisited() {
        return this.haveVisited;
    }
}
