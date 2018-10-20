package dao;

import java.util.List;

import domain.Love_contact;

public interface LoveDao {
	public List<Love_contact> findAll();
	public List<Love_contact> findByUid(int uid);
	public List<Love_contact> findByBlogid(int blogid);
	public int insert(Love_contact u);
	public int deleteByObject(Love_contact u);
	public int deleteByBlogid(int blogid);
	public int existValidate(Love_contact u);
}
