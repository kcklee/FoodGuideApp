package persistence;

import model.FoodGuide;
import model.FoodLocation;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// using code adapted from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
public class JsonWriterTest extends JsonTest {

    @Test
    // using code adapted from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    public void testWriterInvalidFile() {
        try {
            FoodGuide fg = new FoodGuide("Kevin's Food Guide");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {

        }
    }

    @Test
    // using code adapted from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    public void testWriterEmptyFoodGuide() {
        try {
            FoodGuide fg = new FoodGuide("Kevin's Food Guide");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyFoodGuide.json");
            writer.open();
            writer.write(fg);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyFoodGuide.json");
            fg = reader.read();
            assertEquals("Kevin's Food Guide", fg.getName());
            assertEquals(0, fg.length());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    // using code adapted from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    public void testWriterGeneralFoodGuide() {
        try {
            FoodGuide fg = new FoodGuide("Kevin's Food Guide");
            fg.insert(new FoodLocation("Aroma Eatery", "Crystal Mall", "Taiwanese",
                            "https://www.facebook.com/profile.php?id=100063950746962", false));
            fg.insert(new FoodLocation("Viet Noodle Guy", "Brentwood Mall", "Vietnamese",
                    "https://www.thevietnoodleguy.ca/", false));

            JsonWriter writer = new JsonWriter("./data/testWriterGeneralFoodGuide.json");
            writer.open();
            writer.write(fg);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralFoodGuide.json");
            fg = reader.read();
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
            fail("Exception should not have been thrown");
        }
    }
}
