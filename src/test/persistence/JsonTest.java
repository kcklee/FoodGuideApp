package persistence;

import model.FoodLocation;

import static org.junit.jupiter.api.Assertions.assertEquals;

// using code adapted from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
public class JsonTest {

    // using code adapted from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    protected void checkFoodLocation(String name, String neighbourhood, String type,
                                     String website, boolean hasVisited, FoodLocation fl) {
        assertEquals(name, fl.getName());
        assertEquals(neighbourhood, fl.getNeighborhood());
        assertEquals(type, fl.getType());
        assertEquals(website, fl.getWebsite());
        assertEquals(hasVisited, fl.getHaveVisited());
    }
}
