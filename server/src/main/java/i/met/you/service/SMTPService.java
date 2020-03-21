package i.met.you.service;

import java.io.File;
import java.util.List;

import javax.mail.Message;
import javax.mail.Session;

import i.met.you.bean.MailMessageBean;

public interface SMTPService {
	
	Session getMailSession();

	void sendMailWidthAttachments(MailMessageBean mb, List<File> attachments);

	Message getMessageWithSession(MailMessageBean mb, List<File> attachments);

}
