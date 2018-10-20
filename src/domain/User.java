package domain;

import java.sql.Timestamp;

public class User {
	
	private int id;
	private String loginName;
	private String loginPassword;
	private Timestamp registerTime;
	private	Timestamp lastLoginTime;
	private String name;
	private String gender;
	private String phone;
	private String email;
	private String image;
	private String nickname;
	private int focusNum;
	private int fansNum;
	
	
	private int alreadyFocus=0; //对访问用户来讲是否已经关注   1为已关注  0为可关注   -1自己本人 
	
	
	public User(){}
	//中间过程构造
	public User(int id) {this.id=id;}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getLoginPassword() {
		return loginPassword;
	}
	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}
	public Timestamp getRegisterTime() {
		return registerTime;
	}
	public void setRegisterTime(Timestamp registerTime) {
		this.registerTime = registerTime;
	}
	public Timestamp getLastLoginTime() {
		return lastLoginTime;
	}
	public void setLastLoginTime(Timestamp lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	public int getFocusNum() {
		return focusNum;
	}
	public void setFocusNum(int focusNum) {
		this.focusNum = focusNum;
	}
	public int getFansNum() {
		return fansNum;
	}
	public void setFansNum(int fansNum) {
		this.fansNum = fansNum;
	}
	public int getAlreadyFocus() {
		return alreadyFocus;
	}
	public void setAlreadyFocus(int alreadyFocus) {
		this.alreadyFocus = alreadyFocus;
	}
	
	
	
	

}
