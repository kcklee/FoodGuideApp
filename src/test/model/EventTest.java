package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Event class
 */
// code adapted from https://github.students.cs.ubc.ca/CPSC210/AlarmSystem
public class EventTest {
    private Event e;
    private Date d;

    @BeforeEach
    public void runBefore() {
        e = new Event("Food location added");
        d = Calendar.getInstance().getTime();
    }

    @Test
    public void testEvent() {
        assertEquals("Food location added", e.getDescription());
        assertEquals(d.getTime(), e.getDate().getTime(), 1);
    }

    @Test
    public void testToString() {
        assertEquals(d.toString() + "\n" + "Food location added", e.toString());
    }
}

