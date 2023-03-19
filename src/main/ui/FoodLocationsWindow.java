package ui;

import model.FoodGuide;
import model.FoodLocation;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;

import static ui.FoodGuideGUI.SCREEN_HEIGHT;
import static ui.FoodGuideGUI.SCREEN_WIDTH;

public class FoodLocationsWindow extends JFrame {

    private JList<FoodLocation> list;
    private DefaultListModel<FoodLocation> model;
    private FoodGuide fg;

    private JTextArea textArea;
    private JPanel panel;
    private JSplitPane splitPane;

    public FoodLocationsWindow() {
        list = new JList<>();
        model = new DefaultListModel<>();
        textArea = new JTextArea();
        panel = new JPanel();
        splitPane = new JSplitPane();

        setUpDisplay();

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        setLocationRelativeTo(null);

        add(splitPane);
        setTitle("List of Food Locations");
        setVisible(true);

        pack();
    }

    private void setUpDisplay() {
        //        fg = new FoodGuide("Kevin's Food Guide");
//
//        for (FoodLocation fl : fg.getFoodLocations()) {
//            model.addElement(fl);
//        }

        list.setModel(model);

        model.addElement(new FoodLocation("test", "test", "test', "
                + "test", "test", false));

        list.getSelectionModel().addListSelectionListener(e -> {
            FoodLocation fl = list.getSelectedValue();
            textArea.setTabSize(2);
            textArea.setPreferredSize(new Dimension(350, 350));
            textArea.setText("Here are the details of " + fl.getName()
                    + "\n\t Neighbourhood: " + fl.getNeighborhood()
                    + "\n\t Type: " + fl.getType()
                    + "\n\t Website: " + fl.getWebsite()
                    + "\n\t Already visited?: " + fl.getHaveVisited());
        });


        splitPane.setLeftComponent(new JScrollPane(list));
        panel.add(textArea);
        splitPane.setRightComponent(panel);

        splitPane.setDividerLocation(100);
        splitPane.setPreferredSize(new Dimension(500, 500));

    }

}
