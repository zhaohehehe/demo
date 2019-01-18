

import javax.mail.internet.InternetAddress;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MailUtil {

	private static Logger logger = LoggerFactory.getLogger(MailUtil.class);
	private static ReadProperties readProperties = new ReadProperties("mail.properties");

	public static void sendEmail(InternetAddress[] internetAddress, String txt, String subject) {
		String host = readProperties.getValue("mailServerHost");
		String username = readProperties.getValue("mailServerUsername");
		String pwd = readProperties.getValue("mailServerPassword");
		String from = readProperties.getValue("mailServerUsername");
		if("".equals(host)||"".equals(username)||"".equals(pwd)||"".equals(from)){
			logger.error("-------------------mailServerHost or mailServerUsername or mailServerPassword or mailServerUsername is null.-------------------");
			return;
		}
		MailSenderInfo mailInfo = new MailSenderInfo();
		mailInfo.setMailServerHost(host);
		mailInfo.setMailServerPort(readProperties.getValue("mailServerPort"));
		mailInfo.setValidate(true);
		mailInfo.setUserName(username);
		mailInfo.setPassword(pwd);
		mailInfo.setFromAddress(from);
		mailInfo.setToAddress(internetAddress);
		mailInfo.setSubject(subject);
		mailInfo.setContent(txt);
		SimpleMailSender sms = new SimpleMailSender();
		sms.sendTextMail(mailInfo);
	}
}
