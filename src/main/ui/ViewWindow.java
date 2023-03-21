package ui;

import model.FoodGuide;
import model.FoodLocation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static ui.FoodGuideGUI.SCREEN_HEIGHT;
import static ui.FoodGuideGUI.SCREEN_WIDTH;

// TODO
// REQUIRES:
// MODIFIES:
// EFFECTS:
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

    // TODO
    // REQUIRES:
    // MODIFIES:
    // EFFECTS:
    public ViewWindow(FoodGuide fg) {
        this.fg = fg;
        instantiateFields();
        setUpDisplay();
        setUpFrame();
    }


    // TODO
    // REQUIRES:
    // MODIFIES:
    // EFFECTS:
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
    }

    // TODO
    // REQUIRES:
    // MODIFIES:
    // EFFECTS:
    private void setUpDisplay() {

        setUpIntroPane();
        setUpSplitPane();
        setUpButtonPane();

    }

    // TODO
    // REQUIRES:
    // MODIFIES:
    // EFFECTS:
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

    // TODO
    // REQUIRES:
    // MODIFIES
    // EFFECTS
    private void setUpIntroPane() {
        introPane.setPreferredSize(new Dimension(500, 30));
        introPane.setBackground(Color.white);

        introLabel.setText("Number of locations: " + fg.length());

        introPane.add(introLabel);
    }

    // TODO
    // REQUIRES:
    // MODIFIES
    // EFFECTS
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

    // TODO
    // REQUIRES:
    // MODIFIES
    // EFFECTS
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

    // TODO
    // REQUIRES:
    // MODIFIES
    // EFFECTS
    private void removeFromList(int index, FoodLocation selected) {
        model.remove(index);
        fg.remove(selected);
        textArea.setText(null);
    }

    // TODO
    // REQUIRES:
    // MODIFIES
    // EFFECTS
    private void makeVisited(FoodLocation selected) {
        selected.setHaveVisited(true);

        String confirmationMessage = selected.getName() + " has been updated!";
        JOptionPane.showMessageDialog(null, confirmationMessage, "Confirmation", JOptionPane.PLAIN_MESSAGE);
    }

    // TODO
    // REQUIRES:
    // MODIFIES
    // EFFECTS
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
