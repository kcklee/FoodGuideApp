package model;

import java.util.Calendar;
import java.util.Date;


/**
 * Represents a food guide event.
 */
// Represents a food guide event
// code taken from https://github.students.cs.ubc.ca/CPSC210/AlarmSystem
public class Event {
    private static final int HASH_CONSTANT = 13;
    private Date dateLogged;
    private String description;

    /**
     * Creates an event with the given description
     * and the current date/time stamp.
     * @param description  a description of the event
     */
    // EFFECTS: creates an event with the given description and the current date/time stamp.
    public Event(String description) {
        dateLogged = Calendar.getInstance().getTime();
        this.description = description;
    }

    /**
     * Gets the date of this event (includes time).
     * @return  the date of the event
     */
    // EFFECTS: gets the date of this event (includes time)
    public Date getDate() {
        return dateLogged;
    }

    /**
     * Gets the description of this event.
     * @return  the description of the event
     */
    // EFFECTS: gets the description of this event
    public String getDescription() {
        return description;
    }

    // EFFECTS: return true when the two events being compared have the same date, description, and actual type
    //          else return false
    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }

        if (other.getClass() != this.getClass()) {
            return false;
        }


        Event otherEvent = (Event) other;

        return (this.dateLogged.equals(otherEvent.dateLogged)
                && this.description.equals(otherEvent.description));
    }

    // EFFECTS: return the hashcode for the Event object
    @Override
    public int hashCode() {
        return (HASH_CONSTANT * dateLogged.hashCode() + description.hashCode());
    }

    // EFFECTS: return the string representation of the Event object
    @Override
    public String toString() {
        return dateLogged.toString() + "\n" + description;
    }
}
