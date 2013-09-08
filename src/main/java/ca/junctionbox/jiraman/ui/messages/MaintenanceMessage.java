package ca.junctionbox.jiraman.ui.messages;

import ca.junctionbox.jiraman.ui.ApplicationContext;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ca.junctionbox.jiraman.ui.models
 */
public class MaintenanceMessage implements Message {
    private final String comments;
    private final Date date;
    private final String issueNumber;
    private final String issueSubject;
    private final String state;
    private final String subject;
    private final String body;

    public static Message aNew(final String comments, final String issueNumber, final String issueSubject, final String state) {
        String subjectFormat = ApplicationContext.mailConfiguration.getEmailSubjectFormat();
        String bodyFormat = ApplicationContext.mailConfiguration.getEmailBodyFormat();
        return new MaintenanceMessage(comments, new Date(), issueNumber, issueSubject, state, subjectFormat, bodyFormat);
    }

    protected MaintenanceMessage(final String comments, final Date date, final String issueNumber, final String issueSubject, final String state, final String subjectFormat, final String bodyFormat) {
        this.comments = comments;
        this.date = date;
        this.issueNumber = issueNumber;
        this.issueSubject = issueSubject;
        this.state = state;

        this.subject = fieldsToString(subjectFormat);
        this.body = fieldsToString(bodyFormat);

    }

    @Override
    public String getSubject() {
        return subject;
    }

    @Override
    public Date getDate() {
        return date;
    }

    @Override
    public String getBody() {
        return body;
    }

    String fieldsToString(final String format) {
        return String.format(format, comments, getDate("yyyy-MM-dd"), issueNumber, issueSubject, state);
    }

    private String getDate(final String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(this.date);
    }
}
