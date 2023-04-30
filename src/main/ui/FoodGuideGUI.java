package ui;

import model.Event;
import model.EventLog;
import model.FoodGuide;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;

// Represents a GUI version of a food guide application
public class FoodGuideGUI extends JFrame implements ActionListener, WindowListener {

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

    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    private MessagePrinter mp;

    // EFFECTS: construct a FoodGuideGUI with a food guide and GUI components
    //          if file can't be found, throws FileNotFoundException
    public FoodGuideGUI() throws FileNotFoundException {

        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        setLayout(new BorderLayout());
        setTitle("Food Guide Application");
        setVisible(true);

        setUpPanels();

        setUpMenuBar();

        fg = new FoodGuide("Kevin's Food Guide");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        mp = new MessagePrinter();

        pack();

        addWindowListener(this);

    }

    // MODIFIES: this
    // EFFECTS: set up panels with backgrounds, preferred sizes and layout and the welcome page
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

    // MODIFIES: this
    // EFFECTS: set up the welcome page with an image and a welcome message
    // Photo from https://unsplash.com/photos/hrlvr2ZlUNk
    private void setUpMessage() {
        image = new ImageIcon("resources/foodtable.jpeg");
        welcomeMessage = new JLabel();
        welcomeMessage.setText(
                "<html>Welcome to your Food Guide!<br><br>To begin, go the menu bar and select an option</html>");
        welcomeMessage.setIcon(image);

        panel3.add(welcomeMessage);

        welcomeMessage.setVerticalAlignment(JLabel.CENTER);
        welcomeMessage.setHorizontalAlignment(JLabel.CENTER);
    }

    // MODIFIES: this
    // EFFECTS: set up the menu bar with options to load, save, add and view
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

    // MODIFIES: this
    // EFFECTS: processes user selection
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

    // MODIFIES: this
    // EFFECTS: - loads FoodGuide from file
    //          - displays confirmation pop-up message if successful, else displays error message
    private void loadFoodGuide() {
        try {
            fg = jsonReader.read();
            mp.printConfirmationMessage("Loaded " + fg.getName() + " from " + JSON_STORE);

        } catch (IOException e) {
            mp.printErrorMessage("Unable to read from file: " + JSON_STORE);
        }
    }

    // EFFECTS: - saves the FoodGuide to file
    //          - displays confirmation pop-up message if successful, else displays error message
    private void saveFoodGuide() {
        try {
            jsonWriter.open();
            jsonWriter.write(fg);
            jsonWriter.close();
            mp.printConfirmationMessage("Saved " + fg.getName() + " to " + JSON_STORE);

        } catch (FileNotFoundException e) {
            mp.printErrorMessage("Unable to write to file: " + JSON_STORE);
        }
    }

    // EFFECTS: opens up a new AddWindow that lets the user add a new food location
    private void addFoodLocation(FoodGuide fg) {
        new AddWindow(fg);
    }

    // EFFECTS: opens up a new ViewWindow that lets the user view already added food locations
    private void viewFoodLocations(FoodGuide fg) {
        new ViewWindow(fg);
    }

    // NOTE: method inherited from WindowListener interface but not needed/used so no specification added
    @Override
    public void windowOpened(WindowEvent e) {

    }

    // EFFECTS: close window
    @Override
    public void windowClosing(WindowEvent e) {
        dispose();
    }

    // EFFECTS: print out all events in the event log when window is closed
    @Override
    public void windowClosed(WindowEvent e) {
        EventLog events = EventLog.getInstance();
        for (Event el : events) {
            System.out.println(el.toString() + "\n");
        }
    }

    // NOTE: method inherited from WindowListener interface but not needed/used so no specification added
    @Override
    public void windowIconified(WindowEvent e) {

    }

    // NOTE: method inherited from WindowListener interface but not needed/used so no specification added
    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    // NOTE: method inherited from WindowListener interface but not needed/used so no specification added
    @Override
    public void windowActivated(WindowEvent e) {

    }

    // NOTE: method inherited from WindowListener interface but not needed/used so no specification added
    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
