package ui;

import java.io.FileNotFoundException;

// Main class to begin app
// using code adapted from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
public class Main {

    // EFFECTS: runs a program that shows food locations that user wants to try with console-based user interaction
    //          and data persistence
    public static void main(String[] args) {
        try {
            new FoodGuideApp();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application: file not found");
        }
    }
}
