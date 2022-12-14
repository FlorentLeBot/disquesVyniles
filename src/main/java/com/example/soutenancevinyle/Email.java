package com.example.soutenancevinyle;

import sendinblue.ApiClient;
import sendinblue.Configuration;
import sendinblue.auth.ApiKeyAuth;
import sibApi.TransactionalEmailsApi;
import sibModel.*;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Email {
    public void sendMail(String mail) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();

        ApiKeyAuth apiKey = (ApiKeyAuth) defaultClient.getAuthentication("api-key");

        apiKey.setApiKey("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");

        try {
            TransactionalEmailsApi api = new TransactionalEmailsApi();
            SendSmtpEmailSender sender = new SendSmtpEmailSender();

            sender.setEmail("florent.lebot@greta-bretagne-sud.fr"); // adresse email du compte
            sender.setName("Florent");
            List<SendSmtpEmailTo> toList = new ArrayList<>();
            SendSmtpEmailTo to = new SendSmtpEmailTo();

            // mail = "florent.lebot@live.fr";
            to.setEmail(mail);
            to.setName("greta");
            toList.add(to);
            SendSmtpEmailAttachment attachment = new SendSmtpEmailAttachment();
            attachment.setName("test.txt");

            //byte[] encode = Files.readAllBytes(Paths.get("C:\\Users\\greta2022\\Desktop\\"+filename+".txt")); // chemin du fichier
            byte[] encode = Files.readAllBytes(Paths.get("C:\\Users\\greta2022\\Desktop\\test.txt")); // chemin du fichier

            attachment.setContent(encode);
            List<SendSmtpEmailAttachment> attachmentList = new ArrayList<SendSmtpEmailAttachment>();
            attachmentList.add(attachment);
            Properties headers = new Properties();
            headers.setProperty("Some-Custom-Name", "unique-id-1234");
            Properties params = new Properties();
            params.setProperty("parameter", "for your search");
            params.setProperty("subject", "New Search");
            SendSmtpEmail sendSmtpEmail = new SendSmtpEmail();
            sendSmtpEmail.setSender(sender);
            sendSmtpEmail.setTo(toList);
            sendSmtpEmail.setHtmlContent("<html><body><h1>Here are the results {{params.parameter}}</h1></body></html>");
            sendSmtpEmail.setSubject("{{params.subject}}");
            sendSmtpEmail.setAttachment(attachmentList);
            sendSmtpEmail.setHeaders(headers);
            sendSmtpEmail.setParams(params);
            CreateSmtpEmail response = api.sendTransacEmail(sendSmtpEmail);
            System.out.println(response.toString());
        } catch (Exception e) {
            System.out.println("Exception occurred:- " + e.getMessage());
        }
    }
}

