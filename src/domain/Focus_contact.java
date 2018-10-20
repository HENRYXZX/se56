package domain;

import java.sql.Timestamp;

public class Focus_contact {
	
	private int id;
	private int uid;
	private User focus_user;
	private Timestamp time;
	
	public Focus_contact(){}
	//创建中间过程的关系，用于插入数据库
	public Focus_contact(int uid,int fuid)
	{
		this.uid=uid;
		this.focus_user=new User(fuid);
		
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
	public int getUid() {
		return uid;
	}
	
	
	public void setUid(int uid) {
		this.uid = uid;
	}
	public User getFocus_user() {
		return focus_user;
	}
	public void setFocus_user(User focus_user) {
		this.focus_user = focus_user;
	}
	public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}
	
	
}
