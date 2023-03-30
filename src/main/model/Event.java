package model;

import java.util.Calendar;
import java.util.Date;

// TODO
/**
 * Represents an food guide event.
 */
// code taken from https://github.students.cs.ubc.ca/CPSC210/AlarmSystem
public class Event {
    private static final int HASH_CONSTANT = 13;
    private Date dateLogged;
    private String description;

    // TODO
    /**
     * Creates an event with the given description
     * and the current date/time stamp.
     *
     * @param description a description of the event
     */
    public Event(String description) {
        dateLogged = Calendar.getInstance().getTime();
        this.description = description;
    }

    // TODO
    /**
     * Gets the date of this event (includes time).
     *
     * @return the date of the event
     */
    public Date getDate() {
        return dateLogged;
    }

    // TODO
    /**
     * Gets the description of this event.
     *
     * @return the description of the event
     */
    public String getDescription() {
        return description;
    }

    // TODO
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

    // TODO
    @Override
    public int hashCode() {
        return (HASH_CONSTANT * dateLogged.hashCode() + description.hashCode());
    }

    // TODO
    @Override
    public String toString() {
        return dateLogged.toString() + "\n" + description;
    }
}
