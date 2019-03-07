package com.lifeshs.pojo.huanxin;

public class HuanxinUserVO {

	private String username;
	
	private String password;
	
	private String nickname;


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

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	@Override
	public String toString() {
		return "HuanxinUserVO{" +
				"username='" + username + '\'' +
				", password='" + password + '\'' +
				", nickname='" + nickname + '\'' +
				'}';
	}
}
