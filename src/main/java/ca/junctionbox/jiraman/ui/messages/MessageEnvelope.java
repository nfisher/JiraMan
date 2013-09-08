package ca.junctionbox.jiraman.ui.messages;

import ca.junctionbox.jiraman.ui.Transportable;

/**
 * ca.junctionbox.jiraman.ui
 */
public class MessageEnvelope {
    private final Message message;
    private final String to;

    public static MessageEnvelope aNew(final String to, final Message message) {
        return new MessageEnvelope(to, message);
    }

    public MessageEnvelope(String to, Message message) {
        this.to = to;
        this.message = message;
    }

    public boolean deliver(Transportable via) {
        System.out.println("Sending message to: " + to);
        return via.sendTo(message, to);
    }
}
