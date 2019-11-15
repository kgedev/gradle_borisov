package mail;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;
import org.gradle.api.*;
import org.gradle.api.logging.*;
import org.gradle.api.tasks.*;

public class MailTask extends DefaultTask {
	private String to;
	
	@Input
	public String getTo() {
		return to;
	}
	
	public void setTo(String to) {
		this.to = to;
	}
	
	@TaskAction
	public void sendMail() throws Exception {
		String login = (String)(getProject().getProperties().get("recipientMail"));
		String password = (String)(getProject().getProperties().get("mailPassword"));
		String from = (String)(getProject().getProperties().get("from"));
		String hostName = (String)(getProject().getProperties().get("hostName"));
		String smtpPort = (String)(getProject().getProperties().get("smtpPort"));
		String message = (String)(getProject().getProperties().get("message"));
		String subject = (String)(getProject().getProperties().get("subject"));
		
		
		getLogger().lifecycle("Sending mail..." + to);
		Email email = new SimpleEmail();
		email.setHostName(hostName);
		email.setAuthenticator(new DefaultAuthenticator(login, password));
		email.setSmtpPort(Integer.parseInt(smtpPort));
		email.setSSLOnConnect(true);
		email.setFrom(from);
		email.setSubject(subject);
		email.setMsg(message);
		email.addTo(to);
		email.send();
		System.out.println("mail send");
	}
}

