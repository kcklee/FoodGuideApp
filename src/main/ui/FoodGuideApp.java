package ui;

import exceptions.DuplicateNameException;
import exceptions.BackException;
import model.FoodGuide;
import model.FoodLocation;

import java.util.Scanner;

// Food guide application
// using code adapted from https://github.students.cs.ubc.ca/CPSC210/TellerApp where indicated
public class FoodGuideApp {

    private FoodGuide fg;
    private Scanner input;

    // EFFECTS: run the food guide app
    // using code adapted from https://github.students.cs.ubc.ca/CPSC210/TellerApp
    public FoodGuideApp() {
        runFoodGuide();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    // using code adapted from https://github.students.cs.ubc.ca/CPSC210/TellerApp
    private void runFoodGuide() {
        boolean keepGoing = true;
        String command = null;

        init();

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
    // using code adapted from https://github.students.cs.ubc.ca/CPSC210/TellerApp
    private void processCommand(String command) {
        if (command.equals("view")) {
            viewFoodLocations();
        } else if (command.equals("add")) {
            addFoodLocation();
        } else {
            System.out.println("Not valid, try again...");
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes food guides
    // using code adapted from https://github.students.cs.ubc.ca/CPSC210/TellerApp
    private void init() {
        fg = new FoodGuide();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // TODO
    // EFFECTS: displays menu of options to user
    // using code adapted from https://github.students.cs.ubc.ca/CPSC210/TellerApp
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tView");
        System.out.println("\tAdd");
        System.out.println("\tQuit");
    }

    // EFFECTS: displays the names of the food locations and the total number of food locations
    //          display details if food location's name is given when prompted
    //          update visit status of food location
    //          or remove food location depending on user input when prompted
    //          goes back to menu if 'back' is entered when prompted
    // using code adapted from https://github.students.cs.ubc.ca/CPSC210/TellerApp
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

    // TODO
    // REQUIRES: selected is not null
    // MODIFIES: this
    // EFFECTS: prints details of given food location
    // using code adapted from https://github.students.cs.ubc.ca/CPSC210/TellerApp
    private void printDetails(FoodLocation selected) {
        System.out.println("Here are the details of " + selected.getName());
        System.out.println("\t Neighborhood: " + selected.getNeighborhood());
        System.out.println("\t Type: " + selected.getType());
        System.out.println("\t Website: " + selected.getWebsite());
        System.out.println("\t Already visited?: " + selected.getHaveVisited());
    }

    // TODO
    // REQUIRES: selected is not null
    // MODIFIES: this
    // EFFECTS: marks food location as visited if 'visited' is entered when prompted
    //          removes food location if 'remove' is entered when prompted
    private void visitOrRemove(FoodLocation selected) {
        System.out.println("\nTo mark as visited, enter 'visited'");
        System.out.println("To remove the food location, enter 'remove'");
        System.out.println("To go back to the list of food locations, press any key");

        String nextStep = input.next();

        if (nextStep.equals("visited")) {
            makeVisited(selected);
        } else {
            if (nextStep.equals("remove")) {
                removeFoodLocation(selected);
            }
        }
    }

    // TODO
    // EFFECTS: return food location if given input matches a food location's name
    //          else return null
    // using code adapted from https://github.students.cs.ubc.ca/CPSC210/TellerApp
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

    // TODO
    // MODIFIES: this
    // EFFECTS: prompts user to enter details of a food location to add the food location into FoodGuide
    private void addFoodLocation() {
        while (true) {
            System.out.println("Enter the name of food location you want to add \nOr enter 'back' to return to menu");

            String name;

            try {
                name = getInputNameToAdd();
            } catch (BackException e) {
                break;
            } catch (DuplicateNameException e) {
                System.out.println("Name already exists, try again");
                break;
            }

            System.out.println("Enter the neighbourhood that the food location is in");

            String neighbourhood = input.next();

            System.out.println("Enter the type of food that the food location serves");

            String type = input.next();

            System.out.println("Enter the website of the food location");

            String website = input.next();

            FoodLocation fl = new FoodLocation(name, neighbourhood, type, website);

            if (fg.insert(fl)) {
                System.out.println("Location added!");
                break;
            }
        }
    }

    // TODO

    // EFFECTS: returns name that user inputs
    private String getInputNameToAdd() throws BackException, DuplicateNameException {
        String name = input.next();

        if (name.equals("back")) {
            throw new BackException();
        } else if (isDuplicateName(name)) {
            throw new DuplicateNameException();
        }

        return name;
    }

    // EFFECTS: returns true if food location name already exists in FoodGuide
    //          else returns false
    private boolean isDuplicateName(String name) {
        for (FoodLocation fl : fg.getFoodLocations()) {
            return fl.getName().toLowerCase().equals(name);
        }
        return false;
    }

    // TODO
    // MODIFIES: this
    // EFFECTS: removes food location from FoodGuide
    private void removeFoodLocation(FoodLocation selected) {
        fg.remove(selected);
        System.out.println(selected.getName() + " has been removed!\n");
    }

    // TODO
    // REQUIRES: selected is not null
    // MODIFIES: this
    // EFFECTS: sets haveVisited to true
    private void makeVisited(FoodLocation selected) {
        selected.setHaveVisited(true);
        System.out.println(selected.getName() + " has been updated!\n");
    }
}
