package ui;

import java.io.FileNotFoundException;

// Main class to begin app
// using code adapted from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
public class Main {

    // EFFECTS: runs a GUI program that shows food locations that user wants to try (with data persistence)
    public static void main(String[] args) {
        try {
//            new FoodGuideApp();
            new FoodGuideGUI();
        } catch (FileNotFoundException e) {
            MessagePrinter mp = new MessagePrinter();
            mp.printErrorMessage("Unable to run application: file not found");
        }
    }
}
