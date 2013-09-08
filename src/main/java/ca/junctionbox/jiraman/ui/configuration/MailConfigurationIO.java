package ca.junctionbox.jiraman.ui.configuration;

import java.io.*;

/**
 * ca.junctionbox.jiraman.ui
 */
public class MailConfigurationIO {
    public static final String SETTINGS_FILENAME = System.getProperty("user.home") + File.separator + "jiraman.properties";

    public final static boolean exists() {
        File file = new File(SETTINGS_FILENAME);
        return file.exists();
    }

    public final static MailConfiguration read() {
        final MailConfiguration configuration = new MailConfiguration();

        try {
            File file = new File(SETTINGS_FILENAME);

            InputStream ios = new FileInputStream(file);
            configuration.config.load(ios);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return configuration;
    }

    public final static boolean write(final MailConfiguration configuration) {
        try {
            File file = new File(SETTINGS_FILENAME);
            File tmp = new File(SETTINGS_FILENAME + "~");

            OutputStream fos = new FileOutputStream(tmp);
            configuration.config.store(fos, "jiraman - v1.0");

            tmp.renameTo(file);
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
