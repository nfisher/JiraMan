package ca.junctionbox.jiraman.ui.configuration;

import java.util.Properties;

/**
 * ca.junctionbox.jiraman.ui.models
 */
public class MailConfiguration {
    final Properties config = new Properties();

    public void update(final String emailAddress, final String emailPassword, final String emailSubjectFormat, final String emailBodyFormat, final String smtpServer, final int smtpPort) {
        config.put("emailAddress", emailAddress);
        config.put("emailPassword", emailPassword);
        config.put("emailSubjectFormat", emailSubjectFormat);
        config.put("emailBodyFormat", emailBodyFormat);
        config.put("smtpServer", smtpServer);
        config.put("smtpPort", Integer.toString(smtpPort));

    }

    MailConfiguration() {  }

    public String getEmailAddress() {
        return config.getProperty("emailAddress", "");
    }

    public String getEmailPassword() {
        return config.getProperty("emailPassword", "");
    }

    public String getEmailSubjectFormat() {
        return config.getProperty("emailSubjectFormat", "Subject");
    }

    public String getEmailBodyFormat() {
        return config.getProperty("emailBodyFormat", "Body");
    }

    public String getSmtpServer() {
        return config.getProperty("smtpServer", "");
    }

    public int getSmtpPort() {
        return Integer.parseInt(config.getProperty("smtpPort", "0"));
    }

    public String toString() {
        return getEmailAddress() + " " + getSmtpServer() + ":" + getSmtpPort();
    }
}
