package com.dgut.mvc.bean;

public class User {
	private Integer uid;
	private String userName;
	private String password;
	private Integer isAdministrator;
	private Integer postRight;
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public User(Integer uid, String userName, String password, Integer isAdministrator, Integer postRight) {
		super();
		this.uid = uid;
		this.userName = userName;
		this.password = password;
		this.isAdministrator = isAdministrator;
		this.postRight = postRight;
	}
	
	public User(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
		isAdministrator = 0;
		postRight = 1;
	}
	
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getIsAdministrator() {
		return isAdministrator;
	}
	public void setIsAdministrator(Integer isAdministrator) {
		this.isAdministrator = isAdministrator;
	}
	public Integer getPostRight() {
		return postRight;
	}
	public void setPostRight(Integer postRight) {
		this.postRight = postRight;
	}
	@Override
	public String toString() {
		return "User [uid=" + uid + ", userName=" + userName + ", password=" + password + ", isAdministrator="
				+ isAdministrator + ", postRight=" + postRight + "]";
	}
	
	
}
