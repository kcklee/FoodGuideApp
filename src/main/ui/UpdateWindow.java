package ui;

import model.FoodLocation;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static ui.FoodGuideGUI.SCREEN_HEIGHT;
import static ui.FoodGuideGUI.SCREEN_WIDTH;

public class UpdateWindow extends JFrame implements ActionListener {
    private JPanel panel;

    private JLabel introLabel;
    private JLabel nameLabel;
    private JLabel neighourhoodLabel;
    private JLabel typeLabel;
    private JLabel websiteLabel;
    private JLabel haveVisitedLabel;

    private JTextField nameText;
    private JTextField neighbourhoodText;
    private JTextField typeText;
    private JTextField websiteText;
    private JTextField haveVisitedText;

    private JButton updateButton;

    private FoodLocation selected;

    // TODO
    // REQUIRES:
    // MODIFIES:
    // EFFECTS:
    public UpdateWindow(FoodLocation selected)  {
        panel = new JPanel();
        panel.setLayout(null);

        this.selected = selected;

        introLabel =  new JLabel("Change details of the food location");
        nameLabel = new JLabel("Name");
        neighourhoodLabel = new JLabel("Neighbourhood");
        typeLabel = new JLabel("Type");
        websiteLabel = new JLabel("Website");
        haveVisitedLabel = new JLabel("Already visited?");

        nameText = new JTextField(20);
        neighbourhoodText = new JTextField(20);
        typeText = new JTextField(20);
        websiteText = new JTextField(20);
        haveVisitedText = new JTextField(20);

        nameText.setText(selected.getName());
        neighbourhoodText.setText(selected.getNeighborhood());
        typeText.setText(selected.getType());
        websiteText.setText(selected.getWebsite());
        haveVisitedText.setText(String.valueOf(selected.getHaveVisited()));

        updateButton = new JButton("Update Food Location");

        setUpDisplay();

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        setLocationRelativeTo(null);

        add(panel);

        setTitle("Update a Food Location");
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

        haveVisitedLabel.setBounds(10, 170, 100, 25);
        panel.add(haveVisitedLabel);

        haveVisitedText.setBounds(200, 170, 165, 25);
        panel.add(haveVisitedText);

        updateButton.setBounds(10, 210, 200, 25);
        updateButton.addActionListener(this);
        panel.add(updateButton);
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
        boolean userHaveVisitedInput = Boolean.parseBoolean(haveVisitedText.getText());

        selected.setName(userNameInput);
        selected.setNeighbourhood(userNeighbourhoodInput);
        selected.setType(userTypeInput);
        selected.setWebsite(userWebsiteInput);
        selected.setHaveVisited(userHaveVisitedInput);

        String confirmationMessage = "Location updated!";
        JOptionPane.showMessageDialog(null, confirmationMessage, "Confirmation", JOptionPane.PLAIN_MESSAGE);
    }
}
