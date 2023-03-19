package ui;

import exceptions.BackException;
import exceptions.DuplicateNameException;
import model.FoodGuide;
import model.FoodLocation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static ui.FoodGuideGUI.SCREEN_HEIGHT;
import static ui.FoodGuideGUI.SCREEN_WIDTH;

public class AddWindow extends JFrame implements ActionListener {

    private JPanel panel;

    private JLabel introLabel;
    private JLabel nameLabel;
    private JLabel neighourhoodLabel;
    private JLabel typeLabel;
    private JLabel websiteLabel;
    private JLabel success;

    private JTextField nameText;
    private JTextField neighbourhoodText;
    private JTextField typeText;
    private JTextField websiteText;

    private JButton addButton;

    private FoodGuide fg;

    // TODO
    // REQUIRES:
    // MODIFIES:
    // EFFECTS:
    public AddWindow(FoodGuide fg) {
        this.fg = fg;
        panel = new JPanel();
        panel.setLayout(null);

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

        success = new JLabel("");

        setUpDisplay();

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        setLocationRelativeTo(null);

        add(panel);

        setTitle("Add a Food Location");
        setVisible(true);
    }

    // TODO
    // REQUIRES:
    // MODIFIES:
    // EFFECTS:
    private void setUpDisplay() {
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

        success.setBounds(10, 210, 300, 25);
        panel.add(success);
    }

    // TODO
    // REQUIRES:
    // MODIFIES:
    // EFFECTS:
    @Override
    public void actionPerformed(ActionEvent e) {
        String userNameInput = nameText.getText();
        String userNeighbourhoodInput = neighbourhoodText.getText();
        String userTypeInput = typeText.getText();
        String userWebsiteInput = websiteText.getText();

        if (isDuplicateName(userNameInput)) {
            success.setText("Location already exists, try again");
        }

        FoodLocation fl = new FoodLocation(userNameInput, userNeighbourhoodInput,
                userTypeInput, userWebsiteInput, false);

        if (fg.insert(fl)) {
            success.setText("Location added!");
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

