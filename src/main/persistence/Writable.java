package persistence;

import org.json.JSONObject;

// Defines behaviour that writables must implement
// using code adapted from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}

