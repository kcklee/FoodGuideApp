package persistence;

import model.FoodGuide;
import model.FoodLocation;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonReaderTest extends JsonTest {

    @Test
    public void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");

        try {
            FoodGuide fg = reader.read();
            fail("IOException expected");
        } catch (IOException e) {

        }
    }

    @Test
    public void testReaderEmptyWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyFoodGuide.json");

        try {
            FoodGuide fg = reader.read();
            assertEquals("Kevin's Food Guide", fg.getName());
            assertEquals(0, fg.length());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    public void testReaderGeneralFoodGuide() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralFoodGuide.json");

        try {
            FoodGuide fg = reader.read();
            assertEquals("Kevin's Food Guide", fg.getName());
            assertEquals(2, fg.length());

            List<FoodLocation> foodLocations = fg.getFoodLocations();
            assertEquals(2, foodLocations.size());
            checkFoodLocation("Aroma Eatery", "Crystal Mall", "Taiwanese",
                    "https://www.facebook.com/profile.php?id=100063950746962", false,
                    foodLocations.get(0));
            checkFoodLocation("Viet Noodle Guy", "Brentwood Mall", "Vietnamese",
                    "https://www.thevietnoodleguy.ca/", false,
                    foodLocations.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
