package dao;

import java.util.List;

import domain.Focus_contact;

public interface FocusDao {
	public List<Focus_contact> findAll();
	public List<Focus_contact> findByUid(int uid);
	public List<Focus_contact> findMyFans(int fuid);
	public int insert(Focus_contact u);
	public int deleteByObject(Focus_contact u);
	public int existValidate(Focus_contact u);
}
