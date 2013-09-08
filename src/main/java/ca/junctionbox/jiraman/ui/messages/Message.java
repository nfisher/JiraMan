package ca.junctionbox.jiraman.ui.messages;

import java.util.Date;

/**
 * ca.junctionbox.jiraman.ui.messages
 */
public interface Message {
    String getSubject();

    Date getDate();

    String getBody();
}
