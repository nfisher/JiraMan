package ca.junctionbox.jiraman.ui;

import ca.junctionbox.jiraman.ui.configuration.MailConfiguration;
import ca.junctionbox.jiraman.ui.configuration.MailConfigurationIO;
import ca.junctionbox.jiraman.ui.messages.MessageEnvelope;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * ca.junctionbox.jiraman.ui
 */
public class ApplicationContext {
    public static final MailConfiguration mailConfiguration = MailConfigurationIO.read();
    public static final SmtpSession smtpSession = SmtpSession.aNew(mailConfiguration);
    public static final LinkedBlockingQueue<MessageEnvelope> messages = new LinkedBlockingQueue<MessageEnvelope>();

    private ApplicationContext() {}
}
