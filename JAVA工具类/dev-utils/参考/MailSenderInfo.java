

import java.util.Properties;

import javax.mail.internet.InternetAddress;

public class MailSenderInfo { 
	// 发送邮件的服务器的IP和端口 
	private String mailServerHost; 
	private String mailServerPort = "25"; 
	// 邮件发送者的地址 
	private String fromAddress; 
	// 邮件接收者的地址 
	private InternetAddress[] toAddress; 
	// 登陆邮件发送服务器的用户名和密码 
	private String userName; 
	private String password; 
	// 是否需要身份验证 
	private boolean validate = false; 
	// 邮件主题 
	private String subject; 
	// 邮件的文本内容 
	private String content; 
	// 邮件附件的文件名 
	private String[] attachFileNames; 	
	public String[] getAttachFileNames() { 
	  return attachFileNames; 
	} 
	public String getContent() { 
	  return content; 
	} 
	public String getFromAddress() { 
	  return fromAddress; 
	}
	public String getMailServerHost() { 
	  return mailServerHost; 
	}
	public String getMailServerPort() { 
	  return mailServerPort; 
	}
	public String getPassword() { 
	  return password; 
	}
	/** 
	  * 获得邮件会话属性 
	  */ 
	public Properties getProperties(){ 
	  Properties p = new Properties(); 
	  p.put("mail.smtp.host", this.mailServerHost); 
	  p.put("mail.smtp.port", this.mailServerPort); 
	  p.put("mail.smtp.auth", validate ? "true" : "false"); 
	  return p; 
	}
	public String getSubject() { 
	  return subject; 
	}
	public InternetAddress[] getToAddress() {
		return toAddress;
	}
	public String getUserName() { 
	  return userName; 
	} 
	public boolean isValidate() { 
	  return validate; 
	}
	public void setAttachFileNames(String[] fileNames) { 
	  this.attachFileNames = fileNames; 
	}
	public void setContent(String textContent) { 
	  this.content = textContent; 
	}
	public void setFromAddress(String fromAddress) { 
	  this.fromAddress = fromAddress; 
	}
	public void setMailServerHost(String mailServerHost) { 
	  this.mailServerHost = mailServerHost; 
	}
	public void setMailServerPort(String mailServerPort) { 
	  this.mailServerPort = mailServerPort; 
	}
	public void setPassword(String password) { 
	  this.password = password; 
	}
	public void setSubject(String subject) { 
	  this.subject = subject; 
	}
	public void setToAddress(InternetAddress[] toAddress) {
		this.toAddress = toAddress;
	}
	public void setUserName(String userName) { 
	  this.userName = userName; 
	}
	public void setValidate(boolean validate) { 
	  this.validate = validate; 
	} 
} 
