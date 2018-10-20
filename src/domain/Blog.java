package domain;

import java.sql.Timestamp;

public class Blog {
	
	private int id;
	private Topic topic;
	private int topicid=0;
	private User user;
	private int uid;
	private String title;
	private String image;
	private int wordNum;
	private int readNum;
	private int collectNum;
	private int loveNum;
	private int commentsNum;
	private Timestamp writerTime;
	private String content;
	private String simplyContent;
	private int status=0;
	private int canEvaluate=1;
	
	//标记
	private int alreadyCollect=0; //对访问用户来讲是否已经收藏了   0可收藏  1已收藏、-1本人文章
	private int alreadyLove=0; //对访问用户来讲是否已经收藏了   0可喜欢  1已喜欢、 -1 本人文章
	
	
	public Blog(){}
	
	public Blog(int id) {this.id=id;}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getWordNum() {
		return wordNum;
	}
	public void setWordNum(int wordNum) {
		this.wordNum = wordNum;
	}
	public int getReadNum() {
		return readNum;
	}
	public void setReadNum(int readNum) {
		this.readNum = readNum;
	}
	public int getCommentsNum() {
		return commentsNum;
	}
	public void setCommentsNum(int commentsNum) {
		this.commentsNum = commentsNum;
	}
	public Timestamp getWriterTime() {
		return writerTime;
	}
	public void setWriterTime(Timestamp writerTime) {
		this.writerTime = writerTime;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Topic getTopic() {
		return topic;
	}
	public void setTopic(Topic topic) {
		this.topic = topic;
	}
	public int getCollectNum() {
		return collectNum;
	}
	public void setCollectNum(int collectNum) {
		this.collectNum = collectNum;
	}
	public int getLoveNum() {
		return loveNum;
	}
	public void setLoveNum(int loveNum) {
		this.loveNum = loveNum;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getSimplyContent() {
		return simplyContent;
	}
	public void setSimplyContent(String simplyContent) {
		this.simplyContent = simplyContent;
	}
	public int getTopicid() {
		return topicid;
	}
	public void setTopicid(int topicid) {
		this.topicid = topicid;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public int getCanEvaluate() {
		return canEvaluate;
	}
	public void setCanEvaluate(int canEvaluate) {
		this.canEvaluate = canEvaluate;
	}

	public int getAlreadyCollect() {
		return alreadyCollect;
	}

	public void setAlreadyCollect(int alreadyCollect) {
		this.alreadyCollect = alreadyCollect;
	}

	public int getAlreadyLove() {
		return alreadyLove;
	}

	public void setAlreadyLove(int alreadyLove) {
		this.alreadyLove = alreadyLove;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	
	
	
	
	
	

}
