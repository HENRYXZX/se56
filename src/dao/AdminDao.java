package dao;

import java.util.List;

import domain.Admin;

public interface AdminDao {
	
	public List<Admin> findAll();
//	public int insert(Admin admin);
//	public int delete(int id);
//	public int update(Admin admin);
	public Admin findById(int id);
//	public int checkLoginName(String login_name);
	
}
