package ca.junctionbox.jiraman.ui;

import ca.junctionbox.jiraman.ui.messages.MessageEnvelope;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * ca.junctionbox.jiraman.ui
 */
public class MailerThread extends Thread {
    private final LinkedBlockingQueue<MessageEnvelope> queue;

    public MailerThread(LinkedBlockingQueue<MessageEnvelope> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                System.out.println("Waiting for message");
                MessageEnvelope envelope = queue.take();
                envelope.deliver(ApplicationContext.smtpSession);
                // TODO: (NF 2013-09-06) Verify the message was sent sleep if it was not and queue again.
            } catch (InterruptedException e) {
                System.out.println("Queue interrupted exiting mailer thread.");
                break;
            }
        }
    }
}
