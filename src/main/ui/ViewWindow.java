package ui;

import model.FoodGuide;
import model.FoodLocation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static ui.FoodGuideGUI.SCREEN_HEIGHT;
import static ui.FoodGuideGUI.SCREEN_WIDTH;

// Represents a GUI window that allows the user to view already added food locations
// using code adapted from https://docs.oracle.com/javase/tutorial/uiswing/examples/components/index.html
//       (List Demo Project)
// and using code adapted from https://www.youtube.com/watch?v=KOI1WbkKUpQ
public class ViewWindow extends JFrame implements ActionListener {

    private JLabel introLabel;

    private JList<FoodLocation> list;
    private DefaultListModel<FoodLocation> model;
    private FoodGuide fg;

    private JTextArea textArea;
    private JPanel panel;
    private JSplitPane splitPane;

    private JPanel introPane;
    private JPanel buttonPane;

    private JButton removeButton;
    private JButton visitedButton;
    private JButton updateButton;

    private MessagePrinter mp;

    // EFFECTS: constructs an view window with a given food guide and GUI components
    public ViewWindow(FoodGuide fg) {
        this.fg = fg;
        instantiateFields();
        setUpDisplay();
        setUpFrame();
    }

    // MODIFIES: this
    // EFFECTS: instantiate the fields of ViewWindow
    private void instantiateFields() {
        introLabel = new JLabel();

        list = new JList<>();
        model = new DefaultListModel<>();
        textArea = new JTextArea();
        panel = new JPanel();
        splitPane = new JSplitPane();

        buttonPane = new JPanel();
        introPane = new JPanel();

        removeButton = new JButton("Remove");
        visitedButton = new JButton("Visited");
        updateButton = new JButton("Update");

        mp = new MessagePrinter();
    }

    // MODIFIES: this
    // EFFECTS: sets up the display of the ViewWindow by customizing the different panels in the frame
    private void setUpDisplay() {

        setUpIntroPane();
        setUpSplitPane();
        setUpButtonPane();

    }

    // MODIFIES: this
    // EFFECTS: sets up the frame of the AddWindow by customizing it and adding panels to the frame
    private void setUpFrame() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        add(introPane, BorderLayout.NORTH);
        add(splitPane, BorderLayout.CENTER);
        add(buttonPane, BorderLayout.SOUTH);
        setTitle("List of Food Locations");
        setVisible(true);

        pack();
    }

    // MODIFIES: this
    // EFFECTS: set up the top panel by setting its preferred size, background colour, text and adding a label
    private void setUpIntroPane() {
        introPane.setPreferredSize(new Dimension(500, 30));
        introPane.setBackground(Color.white);

        introLabel.setText("Number of locations: " + fg.length());

        introPane.add(introLabel);
    }

    // MODIFIES: this
    // EFFECTS: set up the split pane so that it displays all food locations already in the food guide on the left
    //          of the split pane and all of the corresponding details of a selected food location on the right side
    // using code adapted from https://www.youtube.com/watch?v=KOI1WbkKUpQ
    private void setUpSplitPane() {
        list.setModel(model);

        for (FoodLocation fl : fg.getFoodLocations()) {
            model.addElement(fl);
        }

        list.getSelectionModel().addListSelectionListener(e -> {
            FoodLocation selected = list.getSelectedValue();
            if (selected != null) {
                textArea.setTabSize(2);
                textArea.setPreferredSize(new Dimension(350, 350));
                textArea.setText("Here are the details of " + selected.getName()
                        + "\n\t Neighbourhood: " + selected.getNeighborhood()
                        + "\n\t Type: " + selected.getType()
                        + "\n\t Website: " + selected.getWebsite()
                        + "\n\t Already visited?: " + selected.getHaveVisited());
            }

        });

        splitPane.setLeftComponent(new JScrollPane(list));
        panel.add(textArea);
        panel.setBackground(Color.white);
        splitPane.setRightComponent(panel);

        splitPane.setDividerLocation(100);
        splitPane.setPreferredSize(new Dimension(500, 440));

    }

    // MODIFIES: this
    // EFFECTS: - set up the bottom panel by customizing it and adding the remove, visited and update buttons
    //          - make buttons disabled when there are no food locations in the window
    private void setUpButtonPane() {
        buttonPane.setPreferredSize(new Dimension(500, 30));
        buttonPane.setBackground(Color.white);

        buttonPane.setLayout(new BoxLayout(buttonPane,
                BoxLayout.LINE_AXIS));

        removeButton.addActionListener(this);
        buttonPane.add(removeButton);

        buttonPane.add(Box.createHorizontalStrut(5));

        visitedButton.addActionListener(this);
        buttonPane.add(visitedButton);

        buttonPane.add(Box.createHorizontalStrut(5));

        updateButton.addActionListener(this);
        buttonPane.add(updateButton);

        if (model.getSize() == 0) {
            removeButton.setEnabled(false);
            visitedButton.setEnabled(false);
            updateButton.setEnabled(false);
        }
    }

    // MODIFIES: this
    // EFFECTS: process user command and updates screen accordingly
    @Override
    public void actionPerformed(ActionEvent e) {
        int index = list.getSelectedIndex();

        if (index != -1) {
            FoodLocation selected = model.getElementAt(index);

            if (e.getSource() == removeButton) {
                removeFromList(index, selected);
            }
            if (e.getSource() == visitedButton) {
                makeVisited(selected);
            }
            if (e.getSource() == updateButton) {
                new UpdateWindow(selected);
            }
        }

        updateScreen(index);
    }

    // MODIFIES: this
    // EFFECTS: - remove selected food location and its details from the window and from the food guide
    //          - displays confirmation pop-up message if successful
    private void removeFromList(int index, FoodLocation selected) {
        model.remove(index);
        fg.remove(selected);
        textArea.setText(null);

        mp.printConfirmationMessage(selected.getName() + " has been removed!");
    }

    // MODIFIES: this
    // EFFECTS: - mark selected food location as visited in the food guide
    //          - displays confirmation pop-up message if successful
    private void makeVisited(FoodLocation selected) {
        selected.setHaveVisited(true);

        mp.printConfirmationMessage(selected.getName() + " has been updated!");
    }

    // MODIFIES: this
    // EFFECTS: - update split pane so the next default selected food location is the one at the bottom
    //          - make buttons disabled if there are no more food locations in the window
    // using code adapted from https://docs.oracle.com/javase/tutorial/uiswing/examples/components/index.html
    //       (List Demo Project)
    private void updateScreen(int index) {
        int modelSize = model.getSize();

        if (modelSize == 0) {
            removeButton.setEnabled(false);
            visitedButton.setEnabled(false);
            updateButton.setEnabled(false);
        } else {
            if (index == modelSize) {
                index--;
            }

            list.setSelectedIndex(index);
            list.ensureIndexIsVisible(index);
        }
    }
}
