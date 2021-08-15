package culinary.utils;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMailSSL {

	private String toMail;
	private String fromMail;
	private String subject;
	private String body;
	private Properties props;

	public void setToMail(String toMail) {
		this.toMail = toMail;
	}

	public void setFromMail(String fromMail) {
		this.fromMail = fromMail;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public void setBody(String body) {
		this.body = body;
	}
	
	public SendMailSSL() {
		toMail = "";
		fromMail = "";
		subject = "";
		body = "";

		props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

	}

	public boolean sendMsg() {
		try {
			Session session = Session.getDefaultInstance(props,
					new javax.mail.Authenticator() {
						protected PasswordAuthentication getPasswordAuthentication() {
							return new PasswordAuthentication("gospodynka.pl",
									"Morenike5");
						}
					});

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(fromMail));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(toMail));
			message.setSubject(subject);
			message.setText(body);

			Transport.send(message);

			return true;

		} catch (Exception e) {
			return false;
		}

	}
}