package com.lifeshs.component.mail;

import java.util.Properties;
import com.lifeshs.utils.PropertiesUtil;
	/**   
	* 发送邮件需要使用的基本信息 
	*author by wangfun
	http://www.5a520.cn 小说520  
	*/    
public class MailSenderInfo {    
		
		public MailSenderInfo(){
			PropertiesUtil pro = PropertiesUtil.getInstances();
			this.mailServerHost = pro.readProperty("mail.serverHost");   
			this.mailServerPort = pro.readProperty("mail.serverPort");      
			this.validate = Boolean.valueOf(pro.readProperty("mail.validate"));
			this.userName = pro.readProperty("mail.userName");  
			this.password = pro.readProperty("mail.password");  //您的邮箱密码    
			this.fromAddress = pro.readProperty("mail.fromAddress");  
		}
		
	    // 发送邮件的服务器的IP和端口    
	    private String mailServerHost;    
	    private String mailServerPort = "25";    
	    // 邮件发送者的地址    
	    private String fromAddress;    
	    // 邮件接收者的地址    
	    private String toAddress;    
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
	    public String getMailServerHost() {    
	      return mailServerHost;    
	    }    
	    public void setMailServerHost(String mailServerHost) {    
	      this.mailServerHost = mailServerHost;    
	    }   
	    public String getMailServerPort() {    
	      return mailServerPort;    
	    }   
	    public void setMailServerPort(String mailServerPort) {    
	      this.mailServerPort = mailServerPort;    
	    }   
	    public boolean isValidate() {    
	      return validate;    
	    }   
	    public void setValidate(boolean validate) {    
	      this.validate = validate;    
	    }   
	    public String[] getAttachFileNames() {    
	      return attachFileNames;    
	    }   
	    public void setAttachFileNames(String[] fileNames) {    
	      this.attachFileNames = fileNames;    
	    }   
	    public String getFromAddress() {    
	      return fromAddress;    
	    }    
	    public void setFromAddress(String fromAddress) {    
	      this.fromAddress = fromAddress;    
	    }   
	    public String getPassword() {    
	      return password;    
	    }   
	    public void setPassword(String password) {    
	      this.password = password;    
	    }   
	    public String getToAddress() {    
	      return toAddress;    
	    }    
	    
	    /**
	     *  @author duosheng.mo 
	     *	@DateTime 2016-5-13 下午04:50:03
	     *  @serverComment 发送多个可以用逗号隔开
	     *
	     *  @param toAddress
	     */
	    public void setToAddress(String toAddress) {    
	      this.toAddress = toAddress;    
	    }    
	    public String getUserName() {    
	      return userName;    
	    }   
	    public void setUserName(String userName) {    
	      this.userName = userName;    
	    }   
	    public String getSubject() {    
	      return subject;    
	    }   
	    public void setSubject(String subject) {    
	      this.subject = subject;    
	    }   
	    public String getContent() {    
	      return content;    
	    }   
	    public void setContent(String textContent) {    
	      this.content = textContent;    
	    }    
	}   
