package persistence;

import model.FoodGuide;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

// Represents a writer that writes JSON representation of FoodGuide to file
// using code adapted from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo throughout entire class
public class JsonWriter {
    private static final int TAB = 4;
    private PrintWriter writer;
    private String destination;

    // TODO
    // EFFECTS: constructs writer to write to destination file
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    // TODO
    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination file cannot
    // be opened for writing
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    // TODO
    // MODIFIES: this
    // EFFECTS: writes JSON representation of FoodGuide to file
    public void write(FoodGuide fg) {
        JSONObject json = fg.toJson();
        saveToFile(json.toString(TAB));
    }

    // TODO
    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        writer.close();
    }

    // TODO
    // MODIFIES: this
    // EFFECTS: writes string to file
    private void saveToFile(String json) {
        writer.print(json);
    }
}
