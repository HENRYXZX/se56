package domain;

import java.sql.Timestamp;

public class Topic {
	
	private int id;
	private String name;
	private String image;
	private int managerid=0;
	private User manager;
	private Timestamp createTime;
	private int blogNum=0;
	private String announcement;
	private int isprivate;
	private int status=0;  //0为审批中（创建即自动申请），1为审批通过
	
	public Topic(){}
	public Topic(int id,int status)
	{
		this.id=id;
		this.status=status;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getManagerid() {
		
		if(manager!=null) managerid=this.manager.getId();
		return managerid;
	}
	public void setManagerid(int managerid) {
		this.managerid = managerid;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public String getAnnouncement() {
		return announcement;
	}
	public void setAnnouncement(String announcement) {
		this.announcement = announcement;
	}
	public int getIsprivate() {
		return isprivate;
	}
	public void setIsprivate(int isprivate) {
		this.isprivate = isprivate;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public int getBlogNum() {
		return blogNum;
	}
	public void setBlogNum(int blogNum) {
		this.blogNum = blogNum;
	}
	public User getManager() {
		return manager;
	}
	public void setManager(User manager) {
		this.manager = manager;
	}
	
	
}
