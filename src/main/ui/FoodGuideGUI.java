package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FoodGuideGUI extends JFrame implements ActionListener {

    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;

    private JButton loadButton;
    private JButton saveButton;
    private JButton viewButton;
    private JButton addButton;

    private JLabel welcomeMessage;

    private ImageIcon image;
    public static final int SCREEN_WIDTH = 500;
    public static final int SCREEN_HEIGHT = SCREEN_WIDTH;

    public FoodGuideGUI() {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(SCREEN_WIDTH,SCREEN_HEIGHT);
        setLayout(new BorderLayout());
        setTitle("Food Guide Application");
        setVisible(true);

        setUpPanels();

        pack();

    }

    public void setUpPanels() {
        panel1 = new JPanel();
        panel2 = new JPanel();
        panel3 = new JPanel();

        panel1.setBackground(Color.white);
        panel2.setBackground(Color.white);
        panel3.setBackground(Color.lightGray);

        panel1.setPreferredSize(new Dimension(500, 50));
        panel2.setPreferredSize(new Dimension(500, 50));
        panel3.setPreferredSize(new Dimension(500, 400));

        add(panel1, BorderLayout.NORTH);
        add(panel2, BorderLayout.SOUTH);
        add(panel3, BorderLayout.CENTER);

        setUpButtons();
        setUpMessage();
    }

    public void setUpButtons() {

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

    public void setUpMessage() {
        image = new ImageIcon("resources/foodtable.jpeg");
        welcomeMessage = new JLabel();
        welcomeMessage.setText(
                "<html>Welcome to your Food Guide!<br>To begin, press any of the following buttons</html>");
        welcomeMessage.setIcon(image);

        panel3.add(welcomeMessage);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
