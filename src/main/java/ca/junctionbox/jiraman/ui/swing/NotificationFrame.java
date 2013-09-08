package ca.junctionbox.jiraman.ui.swing;

import ca.junctionbox.jiraman.ui.ApplicationContext;
import ca.junctionbox.jiraman.ui.MailerThread;
import ca.junctionbox.jiraman.ui.messages.MaintenanceMessage;
import ca.junctionbox.jiraman.ui.messages.Message;
import ca.junctionbox.jiraman.ui.messages.MessageEnvelope;

import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.JTextComponent;
import javax.swing.text.PlainDocument;
import java.awt.event.*;
import java.util.Enumeration;

import static javax.swing.JToggleButton.ToggleButtonModel;

/**
 * ca.junctionbox.jiraman.ui
 */
public class NotificationFrame {
    public static final String WINDOW_TITLE = "Jira Man";

    private JPanel notifyPanel;
    private JComboBox issueComboBox;
    private JTextField subjectTextField;
    private JTextField notifyTextField;
    private JButton sendButton;
    private JTextArea commentTextArea;
    private JLabel subjectLabel;
    private JLabel issueLabel;
    private JLabel notifyLabel;
    private JRadioButton startedRadioButton;
    private JRadioButton successfulRadioButton;
    private JRadioButton rollbackRadioButton;
    private JRadioButton delayedRadioButton;
    private JRadioButton resumedRadioButton;

    private ButtonGroup statusGroup;

    public NotificationFrame() {
        JTextComponent editor = (JTextComponent) issueComboBox.getEditor().getEditorComponent();
        editor.setDocument(new PlainDocument() {
            @Override
            public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
                super.insertString(offs, str, a);

                System.out.println(super.getText(0, super.getLength()));
            }

            @Override
            public void remove(int offs, int len) throws BadLocationException {
                super.remove(offs, len);
                System.out.println(super.getText(0, super.getLength()));
            }
        });
        final NotificationFrame parent = this;


        sendButton.addActionListener(new ActionListener() {
            private final NotificationFrame notificationFrame = parent;
            @Override
            public void actionPerformed(ActionEvent e) {
                String comment = notificationFrame.commentTextArea.getText();
                String issueSubject = notificationFrame.subjectTextField.getText();
                String toAddress = notificationFrame.notifyTextField.getText();
                JTextComponent issueTextComponent= (JTextComponent) notificationFrame.issueComboBox.getEditor().getEditorComponent();
                String issueNumber = issueTextComponent.getText();
                String state = "Started";
                Enumeration<AbstractButton> buttonEnumeration = statusGroup.getElements();
                while (buttonEnumeration.hasMoreElements()) {
                    AbstractButton current = buttonEnumeration.nextElement();
                    if (current.isSelected()) {
                        state = current.getText();
                        break;
                    }
                }

                Message message = MaintenanceMessage.aNew(comment, issueNumber, issueSubject, state);
                MessageEnvelope envelope = MessageEnvelope.aNew(toAddress, message);

                try {
                    ApplicationContext.messages.put(envelope);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }

            }
        });
    }

    public static void main(String[] args) {
        MailerThread mailer = new MailerThread(ApplicationContext.messages);
        mailer.start();

        JFrame frame = new JFrame(WINDOW_TITLE);
        frame.setContentPane(new NotificationFrame().notifyPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public void setData(NotificationBean data) {
        subjectTextField.setText(data.getJiraSubject());
        notifyTextField.setText(data.getNotify());
        commentTextArea.setText(data.getNotes());
    }

    public void getData(NotificationBean data) {
        data.setJiraSubject(subjectTextField.getText());
        data.setNotify(notifyTextField.getText());
        data.setNotes(commentTextArea.getText());
    }

    public boolean isModified(NotificationBean data) {
        if (subjectTextField.getText() != null ? !subjectTextField.getText().equals(data.getJiraSubject()) : data.getJiraSubject() != null)
            return true;
        if (notifyTextField.getText() != null ? !notifyTextField.getText().equals(data.getNotify()) : data.getNotify() != null)
            return true;
        if (commentTextArea.getText() != null ? !commentTextArea.getText().equals(data.getNotes()) : data.getNotes() != null)
            return true;
        return false;
    }
}
