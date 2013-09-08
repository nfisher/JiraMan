package ca.junctionbox.jiraman.ui;

import ca.junctionbox.jiraman.ui.messages.Message;

/**
 * ca.junctionbox.jiraman.ui
 */
public interface Transportable {
    boolean sendTo(final Message message, final String recipient);
}
