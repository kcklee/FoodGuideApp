package persistence;

import org.json.JSONObject;

// Defines behaviour that Writable must implement
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}

