package Services;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.io.Serializable;
import java.util.Properties;

public class SendMailService implements Serializable {
    private static final String username = "19130169@st.hcmuaf.edu.vn";
    private static final String password = "nhom4atbm";

    public SendMailService() {
    }

    public static boolean sendMail(String toEmail,String subject, String msg) {
        boolean result = false;
        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "465");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.starttls.required", "true");
        prop.put("mail.smtp.ssl.protocols", "TLSv1.2");
        prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        Session session = Session.getInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject(subject);
            message.setText(msg);
            Transport.send(message);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static boolean sendMailwithFile(String toEmail,String subject, String msg,String filePath) {
        boolean result = false;
        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "465");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.starttls.required", "true");
        prop.put("mail.smtp.ssl.protocols", "TLSv1.2");
        prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        Session session = Session.getInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject(subject);
//            message.setText(msg);
            //add content
            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText(msg);

            //add file
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);

            messageBodyPart = new MimeBodyPart();
            File f = new File(filePath);
            DataSource source = new FileDataSource(f);
            messageBodyPart.setDataHandler(new DataHandler(source));
//            messageBodyPart.setFileName(f.filename);
            messageBodyPart.setFileName(f.getName());
            multipart.addBodyPart(messageBodyPart);

            message.setContent(multipart);

            Transport.send(message);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}