package ui;

import javax.swing.*;
import java.io.FileNotFoundException;

// Main class to begin app
// using code adapted from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
public class Main {

    // EFFECTS: runs a program that shows food locations that user wants to try with console-based user interaction
    //          and data persistence
    // TODO
    // update README too
    // update EFFECTS For this
    public static void main(String[] args) {
        try {
            new FoodGuideGUI();
        } catch (FileNotFoundException e) {
            String errorMessage = "Unable to run application: file not found";
            JOptionPane.showMessageDialog(null, errorMessage, "Error", JOptionPane.PLAIN_MESSAGE);
        }
    }
}
