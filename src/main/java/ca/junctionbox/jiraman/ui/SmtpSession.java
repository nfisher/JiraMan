package ca.junctionbox.jiraman.ui;

import ca.junctionbox.jiraman.ui.configuration.MailConfiguration;
import ca.junctionbox.jiraman.ui.messages.MessageEnvelope;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * ca.junctionbox.jiraman
 */
public class SmtpSession implements Transportable {
    private final MailConfiguration smtpConfig;

    SmtpSession(MailConfiguration smtpConfig) {
        this.smtpConfig = smtpConfig;
    }

    public static SmtpSession aNew(final MailConfiguration mailConfiguration) {
        return new SmtpSession(mailConfiguration);
    }

    public Message createMimeMessage(final String to, final Session session, final ca.junctionbox.jiraman.ui.messages.Message message) throws MessagingException {
        Message sessionMessage = new MimeMessage(session);

        sessionMessage.setFrom(new InternetAddress(smtpConfig.getEmailAddress()));
        sessionMessage.setSubject(message.getSubject());
        sessionMessage.setText(message.getBody());
        sessionMessage.setSentDate(message.getDate());
        sessionMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(to));

        return sessionMessage;
    }

    private Properties getConfig() {
        Properties props = System.getProperties();

        props.put("mail.smtp.host", smtpConfig.getSmtpServer());
        props.put("mail.smtp.port", Integer.toString(smtpConfig.getSmtpPort()));
        props.put("mail.smtp.auth", true);
        props.put("mail.smtp.starttls.enable", true);

        return props;
    }

    private Authenticator getAuth() {
        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(smtpConfig.getEmailAddress(), smtpConfig.getEmailPassword());
            }
        };

        return auth;
    }

    @Override
    public boolean sendTo(final ca.junctionbox.jiraman.ui.messages.Message message, final String recipient) {
        try {
            final Session session = Session.getInstance(getConfig(), getAuth());
            final Message msg = createMimeMessage(recipient, session, message);
            Transport.send(msg);
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }
}
