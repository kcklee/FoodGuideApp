package ui;

import javax.swing.*;
import java.awt.*;

import static ui.FoodGuideGUI.SCREEN_HEIGHT;
import static ui.FoodGuideGUI.SCREEN_WIDTH;

public class NewWindow extends JFrame {

    public NewWindow() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(SCREEN_WIDTH,SCREEN_HEIGHT);
        setLayout(new BorderLayout());
        setVisible(true);
    }
}
