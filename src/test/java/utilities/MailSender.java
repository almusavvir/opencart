package utilities;

import jakarta.mail.*;
import jakarta.mail.internet.*;
import java.io.File;
import java.util.Properties;

public class MailSender {

    public void sendReport() {
        // Sender's email & password
        final String fromEmail = "test.qa.01a@gmail.com";
        final String password = "durr bapx uvac rmdc"; // app-specific password for Gmail

        // Receiver's email
        String toEmail = "sonumsvr@gmail.com";

        // SMTP configuration
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        // Create a session with authenticator
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        });

        try {
            // Create email message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromEmail, "QA 1"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(toEmail)
            );
            message.setSubject("Opencart Suite Execution Report - dry run");

            // ---- Part 1: Email text ----
            MimeBodyPart textPart = new MimeBodyPart();
            textPart.setText("Hello,\n\nThis is an automated mail triggered after test execution.\n\nPFA Emailable Report\n\nPLEASE DO NOT REPLY");

            // ---- Part 2: Attachment ----
            MimeBodyPart attachmentPart = new MimeBodyPart();
            File file = new File("./test-output/emailable-report.html"); // <-- update with your file path
            attachmentPart.attachFile(file);

            // ---- Combine parts into a multipart ----
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(textPart);
            multipart.addBodyPart(attachmentPart);

            // Set the full message content
            message.setContent(multipart);

            // Send the email
            Transport.send(message);

            System.out.println("✅ Email sent successfully to " + toEmail + " with attachment: " + file.getName());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
