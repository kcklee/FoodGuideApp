package ui;

import model.FoodGuide;
import model.FoodLocation;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
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

    // TODO
    // REQUIRES:
    // MODIFIES:
    // EFFECTS:
    public ViewWindow(FoodGuide fg) {
        initializeFields(fg);
        setUpDisplay();

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
    // MODIFIES:
    // EFFECTS:
    private void initializeFields(FoodGuide fg) {
        this.fg = fg;

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

//    @Override
//    public void valueChanged(ListSelectionEvent e) {
//        if (e.getValueIsAdjusting() == false) {
//
//            if (list.getSelectedIndex() == -1) {
//                //No selection, disable fire button.
//                removeButton.setEnabled(false);
//
//            } else {
//                //Selection, enable the fire button.
//                removeButton.setEnabled(true);
//            }
//        }
//    }
//
//    // TODO
//    // REQUIRES:
//    // MODIFIES
//    // EFFECTS
//    // adapted from ListDemoProject
//    public class RemoveListener implements ActionListener {
//
//        // TODO
//        // REQUIRES:
//        // MODIFIES
//        // EFFECTS
//        // adapted from ListDemoProject
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            int index = list.getSelectedIndex();
//            removedFoodLocation = model.remove(index);
//
//
//            fg.remove(removedFoodLocation);
//
//            int size = model.getSize();
//
//            if (size == 0) {
//                removeButton.setEnabled(false);
//
//            } else {
//                if (index == model.getSize()) {
//                    index--;
//                }
//
//                list.setSelectedIndex(index);
//                list.ensureIndexIsVisible(index);
//            }
//
//        }
//    }

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

        if (model.getSize() == 0) {
            removeButton.setEnabled(false);
            visitedButton.setEnabled(false);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int index = list.getSelectedIndex();

        if (index != -1) {
            FoodLocation selected = model.getElementAt(index);

            if (e.getSource() == removeButton) {
                model.remove(index);
                fg.remove(selected);
                textArea.setText(null);
            }

            if (e.getSource() == visitedButton) {
                selected.setHaveVisited(true);

                String confirmationMessage = selected.getName() + " has been updated!";
                JOptionPane.showMessageDialog(null, confirmationMessage, "Confirmation", JOptionPane.PLAIN_MESSAGE);
            }
        }

        int modelSize = model.getSize();

        if (modelSize == 0) {
            removeButton.setEnabled(false);
            visitedButton.setEnabled(false);
        } else {
            if (index == modelSize) {
                index--;
            }

            list.setSelectedIndex(index);
            list.ensureIndexIsVisible(index);
        }
    }

    // TODO
    // REQUIRES:
    // MODIFIES
    // EFFECTS
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        if (e.getSource() == removeButton) {
//            removeFoodLocation();
//        }
//    }
//
//    private void removeFoodLocation() {
//        int index = list.getSelectedIndex();
//        model.removeElementAt(index);
//
//        FoodLocation selected = model.getElementAt(index);
//        fg.remove(selected);
//
//        int size = model.getSize();
//
//        if (size == 0) {
//            removeButton.setEnabled(false);
//
//        } else {
//            if (index == model.getSize()) {
//                index--;
//            }
//
//            list.setSelectedIndex(index);
//            list.ensureIndexIsVisible(index);
//        }
//    }

//    @Override
//    public void valueChanged(ActionListener e) {
//        if (e.getValueIsAdjusting() == false) {
//
//            if (list.getSelectedIndex() == -1) {
//                //No selection, disable fire button.
//                removeButton.setEnabled(false);
//
//            } else {
//                //Selection, enable the fire button.
//                removeButton.setEnabled(true);
//            }
//        }
//    }


    // TODO
    // REQUIRES:
    // MODIFIES
    // EFFECTS
    // add remove button
    // add edit button for visited
    // add listlistener?


}
