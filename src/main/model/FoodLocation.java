package model;

// Represents a food location that has a name, neighbourhood, type, website and a rating
public class FoodLocation {

    private String name;
    private String neighbourhood;
    private String type;
    private String website;
    private boolean haveVisited;

    // TODO: fill in specifications

    // REQUIRES: name is non-zero length
    // EFFECTS: construct a FoodLocation object

    public FoodLocation(String name, String neighbourhood, String type, String website) {
        this.name = name;
        this.neighbourhood = neighbourhood;
        this.type = type;
        this.website = website;
    }

    // setters

    // TODO: fill in specifications
    // REQUIRES: neighbourhood is non-zero length
    // MODIFIES: this
    // EFFECTS: set the neighbourhood of the FoodLocation object

    public void setNeighbourhood(String neighbourhood) {
        this.neighbourhood = neighbourhood;

    }

    // TODO: fill in specifications
    // REQUIRES: type is non-zero length
    // MODIFIES: this
    // EFFECTS: set the type of the FoodLocation object

    public void setType(String type) {
        this.type = type;

    }

    // TODO: fill in specifications
    // REQUIRES: website is non-zero length
    // MODIFIES: this
    // EFFECTS: set the website of the FoodLocation object

    public void setWebsite(String website) {
        this.website = website;

    }

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
