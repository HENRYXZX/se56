package domain;


import java.sql.Timestamp;
import domain.Blog;
import domain.User;

public class Evaluate {
	
	private int id;
	private int uid=0;
	private User user=null; //冗余属性
	private int blogid=0;
	private Blog blog=null; //冗余属性
	private String content;
	private Timestamp time;
	private int skrNum=0;

	
	private int alreadySkr=0;  //对访问用户来说 针对一条评论是否点过赞  0可点赞   1已点
	
	public Evaluate(){}
	public Evaluate(int uid,int blogid,String content)
	{
		this.uid=uid;
		this.blogid=blogid;
		this.content=content;
		this.time=new Timestamp(System.currentTimeMillis());
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
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public int getBlogid() {
		return blogid;
	}
	public void setBlogid(int blogid) {
		this.blogid = blogid;
	}
	public Blog getBlog() {
		return blog;
	}
	public void setBlog(Blog blog) {
		this.blog = blog;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}
	public int getAlreadySkr() {
		return alreadySkr;
	}
	public void setAlreadySkr(int alreadySkr) {
		this.alreadySkr = alreadySkr;
	}
	public int getSkrNum() {
		return skrNum;
	}
	public void setSkrNum(int skrNum) {
		this.skrNum = skrNum;
	}
	
	
}
