package test;

import org.apache.commons.mail.HtmlEmail;

public class Email {

	public boolean send(Mail mail) {
		// 发送email
		HtmlEmail email = new HtmlEmail();
		try {
			// 这里是SMTP发送服务器的名字：163的如下："smtp.163.com"
			email.setHostName(mail.getHost());
			// 字符编码集的设置
			email.setCharset(Mail.ENCODEING);
			// 收件人的邮箱
			email.addTo(mail.getReceiver());
			// 发送人的邮箱
			email.setFrom(mail.getSender(), mail.getName());
			// 如果需要认证信息的话，设置认证：用户名-密码。分别为发件人在邮件服务器上的注册名称和密码
			email.setAuthentication(mail.getUsername(), mail.getPassword());
			// 要发送的邮件主题
			email.setSubject(mail.getSubject());
			email.addCc("2837498697@qq.com");// 抄送
			// 要发送的信息，由于使用了HtmlEmail，可以在邮件内容中使用HTML标签
			email.setMsg(mail.getMessage());
			// 发送
			email.send();
			// if (logger.isDebugEnabled()) {
			// logger.debug(mail.getSender() + " 发送邮件到 " + mail.getReceiver());
			// }
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			String str = mail.getSender() + " 发送邮件到 " + mail.getReceiver() + " 失败";
			return false;
		}
	}

	public static void main(String[] args) {
		Mail mail = new Mail();
		mail.setHost("smtp.163.com"); // 设置邮件服务器,如果不用163的,自己找找看相关的
		mail.setSender("sdfs@163.com");
		mail.setReceiver("guodongjing@edayun.cn"); // 接收人
		mail.setUsername("sdfs@163.com"); // 登录账号,一般都是和邮箱名一样吧
		mail.setPassword("sdfs"); // 发件人邮箱的登录密码
		mail.setSubject("aaaaaaaaa");
		mail.setMessage("<h6>bbbbbbbb\nbbbbbbbbb</h6>");
		new Email().send(mail);
	}
}

class Mail {
	public static final String ENCODEING = "UTF-8";

	private String host; // 服务器地址

	private String sender; // 发件人的邮箱

	private String receiver; // 收件人的邮箱

	private String name; // 发件人昵称

	private String username; // 账号

	private String password; // 密码

	private String subject; // 主题

	private String message; // 信息(支持HTML)

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
