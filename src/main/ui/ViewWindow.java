package ui;

import model.FoodGuide;
import model.FoodLocation;

import javax.swing.*;
import java.awt.*;

import static ui.FoodGuideGUI.SCREEN_HEIGHT;
import static ui.FoodGuideGUI.SCREEN_WIDTH;

// TODO
// REQUIRES:
// MODIFIES:
// EFFECTS:
public class ViewWindow extends JFrame {

    private JLabel introLabel;

    private JList<FoodLocation> list;
    private DefaultListModel<FoodLocation> model;
    private FoodGuide fg;

    private JTextArea textArea;
    private JPanel panel;
    private JSplitPane splitPane;

    private JPanel introPane;
    private JPanel buttonPane;

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
        introPane.setPreferredSize(new Dimension(200, 30));
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
            textArea.setTabSize(2);
            textArea.setPreferredSize(new Dimension(350, 350));
            textArea.setText("Here are the details of " + selected.getName()
                    + "\n\t Neighbourhood: " + selected.getNeighborhood()
                    + "\n\t Type: " + selected.getType()
                    + "\n\t Website: " + selected.getWebsite()
                    + "\n\t Already visited?: " + selected.getHaveVisited());
        });

        splitPane.setLeftComponent(new JScrollPane(list));
        panel.add(textArea);
        splitPane.setRightComponent(panel);

        splitPane.setDividerLocation(100);
        splitPane.setPreferredSize(new Dimension(500, 440));

    }

    // TODO
    // REQUIRES:
    // MODIFIES
    // EFFECTS
    private void setUpButtonPane() {
        buttonPane.setPreferredSize(new Dimension(200, 30));
        buttonPane.setBackground(Color.white);
    }

    // TODO
    // REQUIRES:
    // MODIFIES
    // EFFECTS
    // add remove button
    // add edit button
    // add listlistener?

}
