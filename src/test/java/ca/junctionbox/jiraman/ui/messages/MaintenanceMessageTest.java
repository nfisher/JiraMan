package ca.junctionbox.jiraman.ui.messages;

import org.junit.Test;

import java.util.Date;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

/**
 * ca.junctionbox.jiraman.ui
 */
public class MaintenanceMessageTest {
   @Test
    public void should_format_message_correctly() {
        Message msg = new MaintenanceMessage("comment", new Date(0), "num", "subject", "state", "%1$s %2$s %3$s", "%4$s %5$s");

        String expectedSubject = "comment 1970-01-01 num";
        String expectedBody = "subject state";
        assertThat("subject should be", msg.getSubject(), equalTo(expectedSubject));
        assertThat("body should be", msg.getBody(), equalTo(expectedBody));
    }
}
