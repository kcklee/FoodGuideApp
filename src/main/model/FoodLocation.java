package model;

// Represents a food location that has a name, neighbourhood, type, website and whether it has been visited or not
public class FoodLocation {

    private String name;
    private String neighbourhood;
    private String type;
    private String website;
    private boolean haveVisited;

    // TODO: fill in specifications

    // REQUIRES: name is non-zero length
    // EFFECTS: construct a FoodLocation
    public FoodLocation(String name, String neighbourhood, String type, String website) {
        this.name = name;
        this.neighbourhood = neighbourhood;
        this.type = type;
        this.website = website;
    }

    // setters

    // TODO: fill in specifications

    // MODIFIES: this
    // EFFECTS: set whether the food location has been visited already
    public void setHaveVisited(boolean haveVisited) {
        this.haveVisited = haveVisited;

    }

    // getters

    // TODO: fill in specifications

    // EFFECTS: get name of the FoodLocation object
    public String getName() {
        return this.name;

    }

    // TODO: fill in specifications
    // EFFECTS: get neighbourhood of the FoodLocation object
    public String getNeighborhood() {
        return this.neighbourhood;
    }

    // TODO: fill in specifications
    // EFFECTS: get type of the FoodLocation object
    public String getType() {
        return this.type;
    }

    // TODO: fill in specifications
    // EFFECTS: get website of the FoodLocation object
    public String getWebsite() {
        return this.website;
    }

    // TODO: fill in specifications
    // EFFECTS: get status of whether have already visited the FoodLocation object
    public boolean getHaveVisited() {
        return this.haveVisited;
    }
}
