package fr.eni.enchere.bll;

import javax.mail.*;
import javax.mail.internet.*;

import fr.eni.enchere.bo.Utilisateur;

import java.util.Properties;

public class EmailManager {
	
	private static String HOST = "smtp-mail.outlook.com"; // Serveur SMTP de votre fournisseur de messagerie
	private static String PORT = "587"; // Port SMTP (587 est généralement utilisé pour le chiffrement TLS/SSL)
	private static String USERNAME = "ecolotroc@hotmail.com"; // Votre adresse e-mail
	private static String PASSWORD = "Troc_29@"; // Votre mot de passe e-mail
	
	/**
	 * ***************** Début singleton ***************************
	 */

	private static EmailManager instance;

	private EmailManager() {
	}

	public static EmailManager getInstance() {
		if (instance == null) {
			instance = new EmailManager();
		}
		return instance;
	}

	/**
	 * ***************** Fin singleton ***************************
	 */
	
	protected Session CreateSession() {
		
		// Configuration des propriétés de la session de messagerie
	    Properties properties = System.getProperties();
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", HOST);
		properties.put("mail.smtp.port", PORT);
		
        // Création de la session de messagerie
		Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(USERNAME, PASSWORD);
            }
        });
		
		return session;
        
	}
	
	public boolean envoiMailMdpOubli(Utilisateur utilisateur, String resetPasswordLink) {
		
		boolean sendMailMdpOubliOk = false;
		
        try {
        	
            // Création de l'objet Message pour composer l'e-mail
        	
        	Message message = new MimeMessage(this.CreateSession());
            
            message.setFrom(new InternetAddress(USERNAME));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(utilisateur.getEmail())); // Adresse du destinataire
            
            // Sujet de l'e-mail
            message.setSubject("ecolotroc.com : Mot de Passe Oublié ?");
            
            
            // Contenu de l'e-mail
	            
	        StringBuilder emailContent = new StringBuilder();
	            
	        emailContent.append("Cher(e) utilisateur,\n\n");
	        emailContent.append("Nous avons reçu une demande de réinitialisation de mot de passe pour votre compte. Si vous n'avez pas demandé cette réinitialisation, vous pouvez ignorer cet e-mail en toute sécurité.\n\n");
	      	emailContent.append("Si vous avez effectivement oublié votre mot de passe et souhaitez le réinitialiser, veuillez suivre les étapes ci-dessous :\n\n");
	      	emailContent.append("1. Cliquez sur le lien ci-dessous pour réinitialiser votre mot de passe :\n");
	       	emailContent.append(resetPasswordLink).append("\n\n");
	        emailContent.append("2. Vous serez redirigé vers une page où vous pourrez choisir un nouveau mot de passe pour votre compte.\n\n");
	        emailContent.append("3. Une fois que vous avez choisi un nouveau mot de passe, veuillez le conserver en lieu sûr. Assurez-vous qu'il est unique et difficile à deviner.\n\n");
	        emailContent.append("Si vous avez des questions ou avez besoin d'aide supplémentaire, n'hésitez pas à nous contacter à l'adresse [" + USERNAME + "].\n\n");
	        emailContent.append("Cordialement,\n");
         	emailContent.append("L'équipe de support de [" + USERNAME + "]\n");
            
            // Convertir le contenu en une chaîne de caractères
            String emailContentString = emailContent.toString();
            message.setText(emailContentString);

            // Envoi de l'e-mail
            Transport.send(message);
            System.out.println("E-mail envoyé avec succès");
            sendMailMdpOubliOk = true;
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
		return sendMailMdpOubliOk;
		
	}
	
}
