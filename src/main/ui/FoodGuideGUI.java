package ui;

import model.FoodGuide;
import model.FoodLocation;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

// TODO
// REQUIRES:
// MODIFIES:
// EFFECTS:
public class FoodGuideGUI extends JFrame implements ActionListener {

    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;

    private JMenuBar menuBar;

    private JMenu fileMenu;
    private JMenu editMenu;
    private JMenu viewMenu;

    private JMenuItem loadItem;
    private JMenuItem saveItem;
    private JMenuItem addItem;
    private JMenuItem viewItems;

    private JLabel welcomeMessage;

    private ImageIcon image;
    public static final int SCREEN_WIDTH = 500;
    public static final int SCREEN_HEIGHT = SCREEN_WIDTH;

    private static final String JSON_STORE = "./data/foodguide.json";

    private FoodGuide fg;
    private Scanner input;

    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // TODO
    // REQUIRES:
    // MODIFIES:
    // EFFECTS:
    public FoodGuideGUI() throws FileNotFoundException {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(SCREEN_WIDTH,SCREEN_HEIGHT);
        setLayout(new BorderLayout());
        setTitle("Food Guide Application");
        setVisible(true);

        setUpPanels();

        setUpMenuBar();

        fg = new FoodGuide("Kevin's Food Guide");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        pack();

    }

    // TODO
    // REQUIRES:
    // MODIFIES:
    // EFFECTS:
    private void setUpPanels() {
        panel1 = new JPanel();
        panel2 = new JPanel();
        panel3 = new JPanel();

        panel1.setBackground(Color.white);
        panel2.setBackground(Color.white);
        panel3.setBackground(Color.lightGray);

        panel1.setPreferredSize(new Dimension(500, 50));
        panel2.setPreferredSize(new Dimension(500, 50));
        panel3.setPreferredSize(new Dimension(500, 400));

        panel3.setLayout(new BorderLayout());

        add(panel1, BorderLayout.NORTH);
        add(panel2, BorderLayout.SOUTH);
        add(panel3, BorderLayout.CENTER);

        setUpMessage();
    }


    // TODO
    // REQUIRES:
    // MODIFIES:
    // EFFECTS:
    private void setUpMessage() {
        image = new ImageIcon("resources/foodtable.jpeg");
        welcomeMessage = new JLabel();
        welcomeMessage.setText(
                "<html>Welcome to your Food Guide!<br><br>To begin, go the menu bar and select an option </html>");
        welcomeMessage.setIcon(image);

        panel3.add(welcomeMessage);

        welcomeMessage.setVerticalAlignment(JLabel.CENTER);
        welcomeMessage.setHorizontalAlignment(JLabel.CENTER);
    }

    // TODO
    // REQUIRES:
    // MODIFIES:
    // EFFECTS:
    private void setUpMenuBar() {
        menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        fileMenu = new JMenu("File");
        editMenu = new JMenu("Edit");
        viewMenu = new JMenu("View");

        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(viewMenu);

        loadItem = new JMenuItem("Load");
        saveItem = new JMenuItem("Save");
        addItem = new JMenuItem("Add");
        viewItems = new JMenuItem("View");

        fileMenu.add(loadItem);
        fileMenu.add(saveItem);
        editMenu.add(addItem);
        viewMenu.add(viewItems);

        loadItem.addActionListener(this);
        saveItem.addActionListener(this);
        addItem.addActionListener(this);
        viewItems.addActionListener(this);
    }

    // TODO
    // REQUIRES:
    // MODIFIES:
    // EFFECTS:
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loadItem) {
            loadFoodGuide();
        }
        if (e.getSource() == saveItem) {
            saveFoodGuide();
        }

        if (e.getSource() == addItem) {
            addFoodLocation(fg);
        }

        if (e.getSource() == viewItems) {
            viewFoodLocations(fg);
        }

    }

    // TODO
    // REQUIRES:
    // MODIFIES:
    // EFFECTS:
    private void loadFoodGuide() {
        try {
            fg = jsonReader.read();
            String confirmationMessage = "Loaded " + fg.getName() + " from " + JSON_STORE;
            JOptionPane.showMessageDialog(null, confirmationMessage, "Confirmation", JOptionPane.PLAIN_MESSAGE);
        } catch (IOException e) {
            String errorMessage = "Unable to read from file: " + JSON_STORE;
            JOptionPane.showMessageDialog(null, errorMessage, "Error", JOptionPane.PLAIN_MESSAGE);
        }
    }

    // TODO
    // REQUIRES:
    // MODIFIES:
    // EFFECTS:
    private void saveFoodGuide() {
        try {
            jsonWriter.open();
            jsonWriter.write(fg);
            jsonWriter.close();
            String confirmationMessage = "Saved " + fg.getName() + " to " + JSON_STORE;
            JOptionPane.showMessageDialog(null, confirmationMessage, "Confirmation", JOptionPane.PLAIN_MESSAGE);
        } catch (FileNotFoundException e) {
            String errorMessage = "Unable to write to file: " + JSON_STORE;
            JOptionPane.showMessageDialog(null, errorMessage, "Error", JOptionPane.PLAIN_MESSAGE);
        }
    }

    // TODO
    // REQUIRES:
    // MODIFIES:
    // EFFECTS:
    private void addFoodLocation(FoodGuide fg) {
        new AddWindow(fg);
    }

    // TODO
    // REQUIRES:
    // MODIFIES:
    // EFFECTS:
    private void viewFoodLocations(FoodGuide fg) {
        new ViewWindow(fg);
    }
}
