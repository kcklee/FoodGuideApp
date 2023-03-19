package ui;

import model.FoodGuide;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

// TODO
// REQUIRES:
// MODIFIES:
// EFFECTS:
public class FoodGuideGUI extends JFrame implements ActionListener {

    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;

    private JButton loadButton;
    private JButton saveButton;
    private JButton viewButton;
    private JButton addButton;

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
    public FoodGuideGUI() {

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

        setUpButtons();
        setUpMessage();
    }

    // TODO
    // REQUIRES:
    // MODIFIES:
    // EFFECTS:
    private void setUpButtons() {

        loadButton = new JButton("Load");
        loadButton.setFocusable(false);

        saveButton = new JButton("Save");
        saveButton.setFocusable(false);

        viewButton = new JButton("View");
        viewButton.setFocusable(false);

        addButton = new JButton("Add");
        addButton.setFocusable(false);

        panel1.add(loadButton);
        panel1.add(saveButton);

        panel2.add(viewButton);
        panel2.add(addButton);
    }

    // TODO
    // REQUIRES:
    // MODIFIES:
    // EFFECTS:
    private void setUpMessage() {
        image = new ImageIcon("resources/foodtable.jpeg");
        welcomeMessage = new JLabel();
        welcomeMessage.setText(
                "<html>Welcome to your Food Guide!<br><br>To begin, press any of the following buttons</html>");
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
            addFoodLocation();
        }

        if (e.getSource() == viewItems) {
            viewFoodLocations();
        }

    }

    // TODO
    // REQUIRES:
    // MODIFIES:
    // EFFECTS:
    private void loadFoodGuide() {

    }

    // TODO
    // REQUIRES:
    // MODIFIES:
    // EFFECTS:
    private void saveFoodGuide() {

    }

    // TODO
    // REQUIRES:
    // MODIFIES:
    // EFFECTS:
    private void addFoodLocation() {

    }

    // TODO
    // REQUIRES:
    // MODIFIES:
    // EFFECTS:
    private void viewFoodLocations() {
        FoodLocationsWindow foodLocations = new FoodLocationsWindow();
    }
}
