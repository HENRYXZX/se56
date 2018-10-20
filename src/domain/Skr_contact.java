package domain;

public class Skr_contact {
	
	private int id;
	private int uid;
	private int evaluate_id;
	
	public Skr_contact(){}
	
	public Skr_contact(int uid,int eid)
	{
		this.uid=uid;
		this.evaluate_id=eid;
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
	public int getEvaluate_id() {
		return evaluate_id;
	}
	public void setEvaluate_id(int evaluate_id) {
		this.evaluate_id = evaluate_id;
	}
	
	
}
