package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FoodLocationTest {

    private FoodLocation fl1;
    private FoodLocation fl2;

    @BeforeEach
    public void setup() {
        fl1 = new FoodLocation("Aroma Eatery");
        fl2 = new FoodLocation("Viet Noodle Guy");
    }

    @Test
    public void testConstructor() {
        assertEquals("Aroma Eatery", fl1.getName());
        assertEquals("Viet Noodle Guy", fl2.getName());
    }
}