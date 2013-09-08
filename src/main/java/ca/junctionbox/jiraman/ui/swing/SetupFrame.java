package ca.junctionbox.jiraman.ui.swing;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * ca.junctionbox.jiraman.ui.swing
 */
public class SetupFrame {
    private JTextField emailTextField;
    private JPasswordField emailPasswordField;
    private JTextField smtpServerTextField;
    private JTextField smtpPortTextField;
    private JTextField emailSubjectTextField;
    private JButton saveButton;
    private JTextArea emailBodyFormatTextArea;

    public SetupFrame() {
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Hello World");
            }
        });
    }
}
