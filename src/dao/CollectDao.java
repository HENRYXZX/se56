package dao;

import java.util.List;

import domain.Collect_contact;

public interface CollectDao {
	public List<Collect_contact> findAll();
	public List<Collect_contact> findByUid(int uid);
	public int insert(Collect_contact u);
	public int deleteByObject(Collect_contact u);
	public int deleteByUid(int uid);
	public int deleteByBlogid(int blogid);
	public int existValidate(Collect_contact u);
}
