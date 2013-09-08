package ca.junctionbox.jiraman.ui.swing;

public class NotificationBean {
    private String jiraIssue;
    private String jiraSubject;
    private String notify;
    private String notes;

    public NotificationBean() {
    }

    public String getJiraIssue() {
        return jiraIssue;
    }

    public void setJiraIssue(final String jiraIssue) {
        this.jiraIssue = jiraIssue;
    }

    public String getJiraSubject() {
        return jiraSubject;
    }

    public void setJiraSubject(final String jiraSubject) {
        this.jiraSubject = jiraSubject;
    }

    public String getNotify() {
        return notify;
    }

    public void setNotify(final String notify) {
        this.notify = notify;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(final String notes) {
        this.notes = notes;
    }
}