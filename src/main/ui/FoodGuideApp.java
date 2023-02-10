package ui;

import model.FoodGuide;
import model.FoodLocation;

import java.util.Scanner;

// Food guide application
public class FoodGuideApp {
    private FoodGuide fg;
    private Scanner input;

    // EFFECTS: run the food guide app
    public FoodGuideApp() {
        runFoodGuide();
    }

    // MODIFIES: this
    // EFFECTS: processes user input

    // using code adapted from AccountNotRobust starter
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

    // using code adapted from AccountNotRobust starter
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

    // using code adapted from AccountNotRobust starter
    private void init() {
        fg = new FoodGuide();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // TODO
    // REQUIRES:
    // MODIFIES:
    // EFFECTS:
    // using code adapted from AccountNotRobust starter

    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tView");
        System.out.println("\tAdd");
        System.out.println("\tQuit");
    }

    // REQUIRES:
    // MODIFIES:
    // EFFECTS: displays the names of the food locations and the total number of food locations
    // using code adapted from AccountNotRobust starter
    private void viewFoodLocations() {
        while (true) {
            System.out.println("There are " + fg.size() + " locations: ");
            for (FoodLocation fl : fg.getFoodLocations()) {
                System.out.println(fl.getName());
            }

            System.out.println("Enter the name of the food location to get more details");
//        System.out.println("To go back to menu, type back");

//        if (Objects.equals(input.next(), "back")) {
//
//        }
            FoodLocation selected = selectFoodLocation();
            if (selected == null) {
                System.out.println("invalid");
                break;
            } else {
                printDetails(selected);
                break;
            }
        }

    }

    // TODO
    // REQUIRES: selected is not null
    // MODIFIES:
    // EFFECTS:
    // using code adapted from AccountNotRobust starter
    private void printDetails(FoodLocation selected) {
            System.out.println("Here are the details of " + selected.getName());
            System.out.println("\t Neighborhood: " + selected.getNeighborhood());
            System.out.println("\t Type: " + selected.getType());
            System.out.println("\t Website: " + selected.getWebsite());
            System.out.println("\t Already visited?: " + selected.getHaveVisited());

        System.out.println("To update the visit status, type update");
        System.out.println("To remove the food location, type remove");
        System.out.println("To go back to menu, press any key");

        String nextStep = input.next();

        if (nextStep.equals("update")) {
            makeVisited(selected);
        } else {
            if (nextStep.equals("remove")) {
                removeFoodLocation(selected);
            }
        }
    }
    // TODO
    // REQUIRES:
    // MODIFIES:
    // EFFECTS:
    // using code adapted from AccountNotRobust starter
    private FoodLocation selectFoodLocation() {
        String selection = "";

        selection = input.next();
        selection = selection.toLowerCase();

        for (FoodLocation fl : fg.getFoodLocations()) {
            if (fl.getName().toLowerCase().equals(selection)) {
                return fl;
            }
        }

        return null;
    }

    // TODO
    // REQUIRES: given food location not already in FoodGuide
    // MODIFIES:
    // EFFECTS:
    private void addFoodLocation() {
        System.out.println("Enter the name of the food location you want to add");

        String name = input.next();

        System.out.println("Enter the neighbourhood that the food location is in");

        String neighbourhood = input.next();

        System.out.println("Enter the type of food that the food location serves");

        String type = input.next();

        System.out.println("Enter the website of the food location");

        String website = input.next();

        FoodLocation fl = new FoodLocation(name, neighbourhood, type, website);

        if (!fg.insert(fl)) {
            System.out.println("Already in the list, enter a new location");
            addFoodLocation();
        }
        System.out.println("Location added!");
    }

    //TODO

    // TODO
    // REQUIRES:
    // MODIFIES:
    // EFFECTS:
    // using code adapted from AccountNotRobust starter

    private void removeFoodLocation(FoodLocation selected) {
        fg.remove(selected);
        System.out.println(selected.getName() + " has been removed!");
    }

    // TODO
    // REQUIRES:
    // MODIFIES:
    // EFFECTS:
    // using code adapted from AccountNotRobust starter

    private void makeVisited(FoodLocation selected) {
        selected.setHaveVisited(true);
        System.out.println(selected.getName() + " has been updated!");
    }

}
