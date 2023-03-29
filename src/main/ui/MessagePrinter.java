package ui;

import javax.swing.*;

// Represents a message printer to show pop up messages
public class MessagePrinter {

    // constructs a message printer
    public MessagePrinter() {

    }

    // EFFECTS: displays a confirmation pop-up window that shows the given message
    public void printConfirmationMessage(String message) {
        JOptionPane.showMessageDialog(null, message,
                "Confirmation", JOptionPane.PLAIN_MESSAGE);
    }

    // EFFECTS: displays a error pop-up window that shows the given message
    public void printErrorMessage(String message) {
        JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.PLAIN_MESSAGE);
    }
}
