package com.safronova.webproject.model.util.mail;


import com.safronova.webproject.controller22.command2222.CommandDefiner;
import com.safronova.webproject.controller22.command2222.RequestParameter;
import com.safronova.webproject.model.util.localization.LocaleAttribute;
import com.safronova.webproject.model.util.localization.LocaleKey;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Properties;

public class MailSender {
    private static final Logger logger = LogManager.getLogger();
    private MailSender(){}

    public enum Function {
        SIGN_UP,
        CHANGE_EMAIL
    }

    public static boolean sendEmail(String emailTo, String token, Function function, LocaleAttribute locale){
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
            prepareMessage(message, token, function, locale);
            Transport.send(message);
            return true;
        } catch (MessagingException | UnsupportedOperationException e) {
            logger.error("Can not send message to email: {}", emailTo, e);
            return false;
        }
    }

    private static void prepareMessage(Message message, String token, Function function, LocaleAttribute locale)throws MessagingException{
        final String contentType = "text/html; charset=UTF-8";
        message.setHeader("Content-Type","text/plain; charset=UTF-8");
        switch (function){
            case SIGN_UP:
                String subjectSignUp = locale.getLocalizedMessage(LocaleKey.EMAIL_SUBJECT_SIGN_UP.getValue());
                String contentSignUp = locale.getLocalizedMessage(LocaleKey.EMAIL_CONTENT_SIGN_UP.getValue());
                message.setSubject(subjectSignUp);
                String signUpLink = buildSignUpLink(token);
                String signUpContent = buildMessage(contentSignUp, signUpLink);
                message.setContent(signUpContent, contentType);
                break;
            case CHANGE_EMAIL:
                String subjectChangeEmail = locale.getLocalizedMessage(LocaleKey.EMAIL_SUBJECT_CHANGE_EMAIL.getValue());
                String contentChangeEmail = locale.getLocalizedMessage(LocaleKey.EMAIL_CONTENT_CHANGE_EMAIL.getValue());
                message.setSubject(subjectChangeEmail);
                String emailUpdateLink = buildEmailUpdateLink(token);
                String emailUpdateContent = buildMessage(contentChangeEmail, emailUpdateLink);
                message.setContent(emailUpdateContent, contentType);
                break;
            default:
                logger.error("Current function unsupported: {}", function.name());
                throw new UnsupportedOperationException("Current function unsupported: " + function.name());
        }
    }

    private static String buildMessage(String content, String link){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(content);
        stringBuilder.append(" <a href='");
        stringBuilder.append(link);
        stringBuilder.append("'>");
        stringBuilder.append("link");
        return stringBuilder.append("</a>").toString();
    }

    private static String buildSignUpLink(String token){
        StringBuilder stringBuilder = new StringBuilder();

//        stringBuilder.append(DeploymentPropertiesReader.Deployment.CURRENT_DEPLOYMENT.getValue());
        stringBuilder.append("http://localhost:8080/final_project_war");
        stringBuilder.append("/");
        stringBuilder.append(RequestParameter.CONTROLLER.getValue());
        stringBuilder.append("?");
        stringBuilder.append(RequestParameter.COMMAND.getValue());
        stringBuilder.append("=");
        stringBuilder.append(CommandDefiner.GO_TO_SIGNIN_PAGE.getValue());
//        stringBuilder.append("&");
//        stringBuilder.append(RequestParameter.TOKEN.getValue());
//        stringBuilder.append("=");
//        stringBuilder.append(token);
        return stringBuilder.toString();
    }

    private static String buildEmailUpdateLink(String token) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("http://localhost:8080/final_project_war");
        stringBuilder.append("/");
        stringBuilder.append(RequestParameter.CONTROLLER.getValue());
        stringBuilder.append("?");
        stringBuilder.append(RequestParameter.COMMAND.getValue());
        stringBuilder.append("=");
        stringBuilder.append(CommandDefiner.GO_TO_USER_PROFILE_PAGE.getValue());
        stringBuilder.append("&");
        stringBuilder.append(RequestParameter.TOKEN.getValue());
        stringBuilder.append("=");
        stringBuilder.append(token);
        return stringBuilder.toString();
    }


}
