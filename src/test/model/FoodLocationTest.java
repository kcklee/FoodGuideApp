package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FoodLocationTest {

    private FoodLocation fl1;
    private FoodLocation fl2;

    @BeforeEach
    public void setup() {
        fl1 = new FoodLocation("Aroma Eatery", "Crystal Mall",
                "Taiwanese", "https://www.facebook.com/profile.php?id=100063950746962");
        fl2 = new FoodLocation("Viet Noodle Guy", "Brentwood Mall",
                "Vietnamese", "https://www.thevietnoodleguy.ca/");
    }

    @Test
    public void testConstructor() {
        assertEquals("Aroma Eatery", fl1.getName());
        assertEquals("Crystal Mall", fl1.getNeighborhood());
        assertEquals("Taiwanese", fl1.getType());
        assertEquals("https://www.facebook.com/profile.php?id=100063950746962", fl1.getWebsite());
        assertFalse(fl1.getHaveVisited());

        assertEquals("Viet Noodle Guy", fl2.getName());
        assertEquals("Brentwood Mall", fl2.getNeighborhood());
        assertEquals("Vietnamese", fl2.getType());
        assertEquals("https://www.thevietnoodleguy.ca/", fl2.getWebsite());
        assertFalse(fl2.getHaveVisited());
    }

    @Test
    public void testSetHaveVisitedOnce() {
        assertFalse(fl1.getHaveVisited());
        fl1.setHaveVisited(true);
        assertTrue(fl1.getHaveVisited());
    }

    @Test
    public void testSetHaveVisitedMultipleTimes() {
        assertFalse(fl1.getHaveVisited());

        fl1.setHaveVisited(true);
        assertTrue(fl1.getHaveVisited());

        fl1.setHaveVisited(true);
        assertTrue(fl1.getHaveVisited());
    }
}