package com.briup.enviroment.gui;

public class User {
	private int id;           
	private String username;  
	private String pwd;       
	private String gender;    
	private String info;
	public User() {
	}
	public User(int id, String username, String pwd, String gender, String info) {
		
		this.id = id;
		this.username = username;
		this.pwd = pwd;
		this.gender = gender;
		this.info = info;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", pwd=" + pwd
				+ ", gender=" + gender + ", info=" + info + "]";
	}
	
}
