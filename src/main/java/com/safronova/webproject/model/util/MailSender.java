package com.safronova.webproject.model.util;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Properties;

public class MailSender {
    private static final Logger logger = LogManager.getLogger();
    private static final String MAIL_SUBJECT = "Confectionery SignUp";
    private static final String MAIN_MESSAGE = "Hello, %s! \n" + "Welcome to Confectionery. \n" + "Your password is %s.";


    public static void send(String emailTo, String messageText){
        Properties properties = MailPropertiesReader.getProperties();
        final String userKey = "mail.smtp.user";
        final String passwordKey = "mail.smtp.password";
        final String user = properties.getProperty(userKey);
        final String password = properties.getProperty(passwordKey);

        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, password);
            }
        });

        try{
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailTo));
            message.setSubject(MAIL_SUBJECT);
            message.setText(messageText);
            Transport.send(message);
        } catch (MessagingException | UnsupportedOperationException e) {
            logger.error("Can not send message to email: {}", emailTo, e);
        }
    }

    public static String messageEmailUser(String username, String password) {
        return String.format(MAIN_MESSAGE,
                username,
                password
        );
    }

}
