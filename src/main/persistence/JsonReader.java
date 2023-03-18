package persistence;

import model.FoodGuide;
import model.FoodLocation;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Represents a reader that reads FoodGuide from JSON data stored in file
// using code adapted from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo for all
// methods throughout this class
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    // using code adapted from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads FoodGuide from file and returns it;
    // throws IOException if an error occurs reading data from file
    // using code adapted from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    public FoodGuide read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseFoodGuide(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    // using code adapted from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses FoodGuide from JSON object and returns it
    // using code adapted from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    private FoodGuide parseFoodGuide(JSONObject jsonObject) {
        String name = jsonObject.getString("name");

        FoodGuide fg = new FoodGuide(name);
        addFoodLocations(fg, jsonObject);
        return fg;
    }

    // MODIFIES: fg
    // EFFECTS: parses foodLocations from JSON object and adds them to FoodGuide
    // using code adapted from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    private void addFoodLocations(FoodGuide fg, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("foodLocations");
        for (Object json : jsonArray) {
            JSONObject nextFoodLocation = (JSONObject) json;
            addFoodLocation(fg, nextFoodLocation);
        }
    }

    // MODIFIES: fg
    // EFFECTS: parses foodLocation from JSON object and adds it to FoodGuide
    // using code adapted from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    private void addFoodLocation(FoodGuide fg, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String neighbourhood = jsonObject.getString("neighbourhood");
        String type = jsonObject.getString("type");
        String website = jsonObject.getString("website");
        boolean haveVisited = jsonObject.getBoolean("haveVisited");
        FoodLocation fl = new FoodLocation(name, neighbourhood, type, website, haveVisited);
        fg.insert(fl);
    }
}

