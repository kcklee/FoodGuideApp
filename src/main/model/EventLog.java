package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;


/**
 * Represents a log of food guide events.
 * We use the Singleton Design Pattern to ensure that there is only
 * one EventLog in the system and that the system has global access
 * to the single instance of the EventLog.
 */
// Represents a log of food guide events
// code taken from https://github.students.cs.ubc.ca/CPSC210/AlarmSystem
public class EventLog implements Iterable<Event> {

    /** the only EventLog in the system (Singleton Design Pattern) */
    private static EventLog theLog;
    private Collection<Event> events;

    /**
     * Prevent external construction.
     * (Singleton Design Pattern).
     */
    // EFFECTS: construct a private EventLog (to prevent external construction) with no events added
    private EventLog() {
        events = new ArrayList<Event>();
    }

    /**
     * Gets instance of EventLog - creates it
     * if it doesn't already exist.
     * (Singleton Design Pattern)
     * @return  instance of EventLog
     */
    // MODIFIES: EventLog (the class)
    // EFFECTS: get instance of EventLog, creates it if it doesn't already exist
    public static EventLog getInstance() {
        if (theLog == null) {
            theLog = new EventLog();
        }

        return theLog;
    }

    /**
     * Adds an event to the event log.
     * @param e the event to be added
     */
    // MODIFIES: this
    // EFFECTS: add given event to the event log
    public void logEvent(Event e) {
        events.add(e);
    }

    /**
     * Clears the event log and logs the event.
     */
    // MODIFIES: this
    // EFFECTS: clears the event log and logs the event
    public void clear() {
        events.clear();
        logEvent(new Event("Event log cleared."));
    }

    // EFFECTS: return events' iterator
    @Override
    public Iterator<Event> iterator() {
        return events.iterator();
    }
}