/**
 * 
 */
package i.met.you.service.impl;

import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.logging.log4j.Logger;

import i.met.you.bean.MailMessageBean;
import i.met.you.service.SMTPService;
import i.met.you.util.ApplicationLogger;

/**
 * @author levi
 *
 */
public class SMTPServiceImpl implements SMTPService {

	private static final Logger logger = ApplicationLogger.getLogger(SMTPServiceImpl.class);

	private static volatile SMTPServiceImpl instance = null;

	private SMTPServiceImpl() {

	}

	public static SMTPServiceImpl getInstance() {
		if (instance == null) {
			synchronized (SMTPServiceImpl.class) {
				if (instance == null) {
					instance = new SMTPServiceImpl();
				}
			}
		}
		return instance;
	}

	@Override
	public Session getMailSession() {
		Session session = null;
		
		return session;
	}

	@Override
	public void sendMailWidthAttachments(MailMessageBean mb, List<File> attachments) {

		try {
			Message message = new MimeMessage(this.getMailSession());
			message.setFrom(new InternetAddress(mb.getFrom()));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mb.getTo()));
			message.setSubject(mb.getSubject());

			MimeBodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setContent(mb.getBody(), "text/html; charset=utf-8");

			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart);

			if (attachments != null) {
				File attachment = null;
				Iterator<File> it = attachments.iterator();
				while (it.hasNext()) {
					attachment = it.next();
					MimeBodyPart attachPart = new MimeBodyPart();
					attachPart.attachFile(attachment);
					multipart.addBodyPart(attachPart);
				}
			}

			message.setContent(multipart);
			Transport.send(message);
			// taskExecutor.execute(new AsyncMimeMailTask(message));
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

	}

	@Override
	public Message getMessageWithSession(MailMessageBean mb, List<File> attachments) {
		Message message = new MimeMessage(this.getMailSession());
		try {

			message.setFrom(new InternetAddress(mb.getFrom()));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mb.getTo()));
			message.setSubject(mb.getSubject());

			MimeBodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setContent(mb.getBody(), "text/html; charset=utf-8");

			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart);

			if (attachments != null) {
				File attachment = null;
				Iterator<File> it = attachments.iterator();
				while (it.hasNext()) {
					attachment = it.next();
					MimeBodyPart attachPart = new MimeBodyPart();
					attachPart.attachFile(attachment);
					multipart.addBodyPart(attachPart);
				}
			}

			message.setContent(multipart);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return message;

	}

	// gyors teszt

	public static void main(String[] args) {

		SMTPService smtpService = SMTPServiceImpl.getInstance();

		MailMessageBean mb = new MailMessageBean();
		mb.setFrom("info@.world");
		mb.setTo("levente.ligeti@gmail.com");
		mb.setSubject("test mail");
		mb.setBody("<h1>hello world2</h1>");

		Message msg = smtpService.getMessageWithSession(mb, null);
		AsyncMailSender asyncMailSender = new AsyncMailSender();
		asyncMailSender.init(msg);

	}

}
