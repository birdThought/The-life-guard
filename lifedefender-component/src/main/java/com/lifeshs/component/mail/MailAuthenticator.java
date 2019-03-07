package com.lifeshs.component.mail;

import javax.mail.*;   

/**
 *  版权归
 *  TODO 服务器邮箱登录验证
 *  @author duosheng.mo  
 *  @DateTime 2016-5-13 下午04:39:08
 */
public class MailAuthenticator extends Authenticator{   
	private  String userName;   
	private  String password;  
        
    public MailAuthenticator(){   
    }   
    
    public MailAuthenticator(String username, String password) {    
        this.userName = username;    
        this.password = password;    
    }  
    
    protected PasswordAuthentication getPasswordAuthentication(){   
        return new PasswordAuthentication(userName, password);   
    }   
}   