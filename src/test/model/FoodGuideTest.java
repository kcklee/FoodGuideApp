package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FoodGuideTest {

    private FoodGuide foodGuide;
    private FoodLocation fl1;
    private FoodLocation fl2;
    private FoodLocation fl3;


    @BeforeEach
    public void setup() {
        foodGuide = new FoodGuide();
        fl1 = new FoodLocation("Aroma Eatery");
        fl2 = new FoodLocation("Viet Noodle Guy");
        fl3 = new FoodLocation("Superbaba");
    }

    @Test
    public void testConstructor() {
        assertEquals(0, foodGuide.size());
    }

    @Test
    public void testInsertOne() {
        assertTrue(foodGuide.insert(fl1));
        assertEquals(1, foodGuide.size());
        assertTrue(foodGuide.contains(fl1));

    }

    @Test
    public void testInsertMultiple() {
        assertTrue(foodGuide.insert(fl1));
        assertTrue(foodGuide.contains(fl1));

        assertTrue(foodGuide.insert(fl2));
        assertTrue(foodGuide.contains(fl2));

        assertEquals(2, foodGuide.size());
    }

    @Test
    public void testInsertDuplicate() {
        assertTrue(foodGuide.insert(fl1));
        assertEquals(1, foodGuide.size());
        assertFalse(foodGuide.insert(fl1));
        assertEquals(1, foodGuide.size());
    }

    @Test
    public void testRemoveOne() {
        foodGuide.insert(fl1);
        foodGuide.insert(fl2);

        assertTrue(foodGuide.contains(fl1));
        assertTrue(foodGuide.contains(fl2));

        assertTrue(foodGuide.remove(fl1));
        assertFalse(foodGuide.contains(fl1));
        assertTrue(foodGuide.contains(fl2));
    }

    @Test
    public void testRemoveMultiple() {
        foodGuide.insert(fl1);
        foodGuide.insert(fl2);

        assertTrue(foodGuide.remove(fl1));
        assertEquals(1, foodGuide.size());
        assertFalse(foodGuide.contains(fl1));

        assertTrue(foodGuide.remove(fl2));
        assertEquals(0, foodGuide.size());
        assertFalse(foodGuide.contains(fl2));
    }

    @Test
    public void testRemoveNotInList() {
        foodGuide.insert(fl1);
        foodGuide.insert(fl3);

        assertFalse(foodGuide.remove(fl2));
        assertEquals(2, foodGuide.size());
    }

    @Test
    public void testRetrieveOne() {
        foodGuide.insert(fl1);
        foodGuide.insert(fl2);

        assertEquals(fl1, foodGuide.retrieve("Aroma Eatery"));
    }

    @Test
    public void testRetrieveMultiple() {
        foodGuide.insert(fl1);
        foodGuide.insert(fl2);

        assertEquals(fl1, foodGuide.retrieve("Aroma Eatery"));
        assertEquals(fl2, foodGuide.retrieve("Viet Noodle Guy"));
    }



}
