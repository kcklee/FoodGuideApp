package ui;

import model.FoodGuide;
import model.FoodLocation;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static ui.FoodGuideGUI.SCREEN_HEIGHT;
import static ui.FoodGuideGUI.SCREEN_WIDTH;

// Represents a GUI window that allows the user to add a food location
// using code adapted from https://www.youtube.com/watch?v=iE8tZ0hn2Ws where indicated
public class AddWindow extends JFrame implements ActionListener {

    private JPanel panel;

    private JLabel introLabel;
    private JLabel nameLabel;
    private JLabel neighourhoodLabel;
    private JLabel typeLabel;
    private JLabel websiteLabel;

    private JTextField nameText;
    private JTextField neighbourhoodText;
    private JTextField typeText;
    private JTextField websiteText;

    private JButton addButton;

    private FoodGuide fg;

    private MessagePrinter mp;

    // EFFECTS: constructs an add window with a food guide and GUI components
    public AddWindow(FoodGuide fg) {
        this.fg = fg;

        instantiateFields();
        setUpDisplay();
        setUpFrame();

    }

    // MODIFIES: this
    // EFFECTS: instantiate the fields of AddWindow
    private void instantiateFields() {
        panel = new JPanel();

        introLabel =  new JLabel("Enter the details of the food location you want to add");
        nameLabel = new JLabel("Name");
        neighourhoodLabel = new JLabel("Neighbourhood");
        typeLabel = new JLabel("Type");
        websiteLabel = new JLabel("Website");

        nameText = new JTextField(20);
        neighbourhoodText = new JTextField(20);
        typeText = new JTextField(20);
        websiteText = new JTextField(20);

        addButton = new JButton("Add to Food Guide");

        mp = new MessagePrinter();
    }

    // MODIFIES: this
    // EFFECTS: sets up the display of the AddWindow by setting its layout and
    //          adding labels, text fields and a button to a panel
    // using code adapted from https://www.youtube.com/watch?v=iE8tZ0hn2Ws
    private void setUpDisplay() {
        panel.setLayout(null);

        introLabel.setBounds(10, 20, 500, 25);
        panel.add(introLabel);

        nameLabel.setBounds(10, 50, 100, 25);
        panel.add(nameLabel);

        nameText.setBounds(200, 50, 165, 25);
        panel.add(nameText);

        neighourhoodLabel.setBounds(10, 80, 100, 25);
        panel.add(neighourhoodLabel);

        neighbourhoodText.setBounds(200, 80, 165, 25);
        panel.add(neighbourhoodText);

        typeLabel.setBounds(10, 110, 100, 25);
        panel.add(typeLabel);

        typeText.setBounds(200, 110, 165, 25);
        panel.add(typeText);

        websiteLabel.setBounds(10, 140, 100, 25);
        panel.add(websiteLabel);

        websiteText.setBounds(200, 140, 165, 25);
        panel.add(websiteText);

        addButton.setBounds(10, 180, 200, 25);
        addButton.addActionListener(this);
        panel.add(addButton);
    }

    // MODIFIES: this
    // EFFECTS: sets up the frame of the AddWindow by customizing it and adding a panel to the frame
    private void setUpFrame() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        setLocationRelativeTo(null);

        add(panel);

        setTitle("Add a Food Location");
        setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: - when the add button is pressed,
    //            adds a food location and its details (provided by user) to the food guide if the given food location
    //            name doesn't already exist in the food guide
    //          - provides a confirmation pop-up message if addition was successful,
    //            else provides an error pop-up message
    @Override
    public void actionPerformed(ActionEvent e) {
        String userNameInput = nameText.getText();
        String userNeighbourhoodInput = neighbourhoodText.getText();
        String userTypeInput = typeText.getText();
        String userWebsiteInput = websiteText.getText();

        if (isDuplicateName(userNameInput)) {
            mp.printErrorMessage("Location already exists, try again");
        }

        FoodLocation fl = new FoodLocation(userNameInput, userNeighbourhoodInput,
                userTypeInput, userWebsiteInput, false);

        if (fg.insert(fl)) {
            mp.printConfirmationMessage("Location added!");
        }
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
}

