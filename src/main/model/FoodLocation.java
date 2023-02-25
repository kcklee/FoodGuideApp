package model;

// Represents a food location that has a name, neighbourhood, type, website
// and indicates whether it has been visited or not
public class FoodLocation {

    private String name;
    private String neighbourhood;
    private String type;
    private String website;
    private boolean haveVisited;

    // REQUIRES: name, neighbourhood, type, and website are non-zero length
    // EFFECTS: constructs a food location with given name, neighbourhood, type and website
    public FoodLocation(String name, String neighbourhood, String type, String website) {
        this.name = name;
        this.neighbourhood = neighbourhood;
        this.type = type;
        this.website = website;
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
