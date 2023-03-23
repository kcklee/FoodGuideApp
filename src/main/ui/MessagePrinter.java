package ui;

import javax.swing.*;

public class MessagePrinter {

    public MessagePrinter() {

    }

    public void printConfirmationMessage(String message) {
        JOptionPane.showMessageDialog(null, message,
                "Confirmation", JOptionPane.PLAIN_MESSAGE);
    }

    public void printErrorMessage(String message) {
        JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.PLAIN_MESSAGE);
    }
}
