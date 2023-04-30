package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;


// Represents a log of food guide events

public class EventLog implements Iterable<Event> {

    private static EventLog theLog;
    private Collection<Event> events;

    // EFFECTS: construct a private EventLog (to prevent external construction) with no events added
    private EventLog() {
        events = new ArrayList<Event>();
    }

    // MODIFIES: EventLog (the class)
    // EFFECTS: get instance of EventLog, creates it if it doesn't already exist
    public static EventLog getInstance() {
        if (theLog == null) {
            theLog = new EventLog();
        }

        return theLog;
    }

    // MODIFIES: this
    // EFFECTS: add given event to the event log
    public void logEvent(Event e) {
        events.add(e);
    }

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