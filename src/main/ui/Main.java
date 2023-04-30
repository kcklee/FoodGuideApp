package ui;

import java.io.FileNotFoundException;

// Main class to begin app
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
