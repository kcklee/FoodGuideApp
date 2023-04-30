package ui;

import exceptions.BackException;
import exceptions.DuplicateNameException;
import model.FoodGuide;
import model.FoodLocation;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

// Food guide application

public class FoodGuideApp {
    private static final String JSON_STORE = "./data/foodguide.json";

    private FoodGuide fg;
    private Scanner input;

    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: construct FoodGuide and runs the food guide app
    //          if file can't be found, throws FileNotFoundException
    public FoodGuideApp() throws FileNotFoundException {
        fg = new FoodGuide("Kevin's Food Guide");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runFoodGuide();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runFoodGuide() {
        boolean keepGoing = true;
        String command = null;
        input = new Scanner(System.in);
        input.useDelimiter("\n");

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("quit")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\n Goodbye!");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("view")) {
            viewFoodLocations();
        } else if (command.equals("add")) {
            addFoodLocation();
        } else if (command.equals("save")) {
            saveFoodGuide();
        } else if (command.equals("load")) {
            loadFoodGuide();
        } else {
            System.out.println("Not valid, try again...");
        }
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tView");
        System.out.println("\tAdd");
        System.out.println("\tSave (to save food guide to file)");
        System.out.println("\tLoad (to load food guide to file)");
        System.out.println("\tQuit");
    }

    // MODIFIES: this
    // EFFECTS: - displays the names of the food locations and the total number of food locations
    //          - displays option to get details about food location if name is inputted or to go back to previous menu
    //          - if name is found in food guide, print details about food location
    //          and process user command to update visit status of food location or remove the food location
    //          - if name isn't found in food guide, prompt user to enter another one
    private void viewFoodLocations() {
        while (true) {
            System.out.println("Number of locations: " + fg.length());
            for (FoodLocation fl : fg.getFoodLocations()) {
                System.out.println(fl.getName());
            }

            System.out.println("\nEnter a food location's name to get more details");
            System.out.println("Or enter 'back' to return to menu");

            FoodLocation selected;

            try {
                selected = selectFoodLocation();
            } catch (BackException e) {
                break;
            }

            if (selected == null) {
                System.out.println("Location not found, enter another one \n");
            } else {
                printDetails(selected);
                visitOrRemove(selected);
            }
        }

    }

    // MODIFIES: this
    // EFFECTS: - prompts user to enter details of a food location to add the food location into the food guide
    //          or to go back to menu
    //          - if addition of food location was successful, display confirmation message and return to menu
    private void addFoodLocation() {
        while (true) {
            System.out.println("Enter the name of food location you want to add \nOr enter 'back' to return to menu");

            String name;

            try {
                name = getInputNameToAdd();
            } catch (BackException e) {
                break;
            } catch (DuplicateNameException e) {
                System.out.println("Location already exists, try again");
                break;
            }

            System.out.println("Enter the neighbourhood that the food location is in");

            String neighbourhood = input.next();

            System.out.println("Enter the type of food that the food location serves");

            String type = input.next();

            System.out.println("Enter the website of the food location");

            String website = input.next();

            FoodLocation fl = new FoodLocation(name, neighbourhood, type, website, false);

            if (fg.insert(fl)) {
                System.out.println("Location added!");
                break;
            }
        }
    }

    // EFFECTS: saves the FoodGuide to file
    private void saveFoodGuide() {
        try {
            jsonWriter.open();
            jsonWriter.write(fg);
            jsonWriter.close();
            System.out.println("Saved " + fg.getName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads FoodGuide from file
    private void loadFoodGuide() {
        try {
            fg = jsonReader.read();
            System.out.println("Loaded " + fg.getName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    // EFFECTS: return food location if given input matches a food location's name
    //          else return null
    //          if 'back' is entered, throws BackException
    private FoodLocation selectFoodLocation() throws BackException {
        String selection = "";

        selection = input.next();
        selection = selection.toLowerCase();

        if (selection.equals("back")) {
            throw new BackException();
        }

        for (FoodLocation fl : fg.getFoodLocations()) {
            if (fl.getName().toLowerCase().equals(selection)) {
                return fl;
            }
        }

        return null;
    }

    // REQUIRES: selected is not null
    // EFFECTS: displays details of given food location
    private void printDetails(FoodLocation selected) {
        System.out.println("Here are the details of " + selected.getName());
        System.out.println("\t Neighbourhood: " + selected.getNeighborhood());
        System.out.println("\t Type: " + selected.getType());
        System.out.println("\t Website: " + selected.getWebsite());
        System.out.println("\t Already visited?: " + selected.getHaveVisited());
    }

    // REQUIRES: selected is not null
    // MODIFIES: this
    // EFFECTS: - displays command options to user
    //          - marks food location as visited if 'visited' is entered when prompted
    //          - removes food location if 'remove' is entered when prompted
    private void visitOrRemove(FoodLocation selected) {
        System.out.println("\nTo mark as visited, enter 'visited'");
        System.out.println("To remove the food location, enter 'remove'");
        System.out.println("To go back to the list of food locations, press any key");

        String nextStep = input.next();

        if (nextStep.equals("visited")) {
            makeVisited(selected);
        } else if (nextStep.equals("remove")) {
            removeFoodLocation(selected);
        }
    }

    // EFFECTS: returns name that user inputs
    //          if 'back' is entered, throws BackException
    //          if a name that already exists is entered, throws DuplicateNameException
    private String getInputNameToAdd() throws BackException, DuplicateNameException {
        String name = input.next();

        if (name.equals("back")) {
            throw new BackException();
        } else if (isDuplicateName(name)) {
            throw new DuplicateNameException();
        }

        return name;
    }

    // EFFECTS: returns true if food location name already exists in food guide
    //          else returns false
    private boolean isDuplicateName(String name) {
        name = name.toLowerCase();

        for (FoodLocation fl : fg.getFoodLocations()) {
            if (fl.getName().toLowerCase().equals(name)) {
                return true;
            }
        }
        return false;
    }

    // REQUIRES: selected is not null
    // MODIFIES: this
    // EFFECTS: removes food location from the food guide and displays confirmation message
    private void removeFoodLocation(FoodLocation selected) {
        fg.remove(selected);
        System.out.println(selected.getName() + " has been removed!\n");
    }

    // REQUIRES: selected is not null
    // MODIFIES: this
    // EFFECTS: sets haveVisited to true and displays confirmation message
    private void makeVisited(FoodLocation selected) {
        selected.setHaveVisited(true);
        System.out.println(selected.getName() + " has been updated!\n");
    }

}
