package dao;

import java.util.List;

import domain.Admin_Log;

public interface Admin_LogDao {
	public List<Admin_Log> findAll();
	public int insert(Admin_Log u);
	public int delete(int id);
	public int update(Admin_Log u);
	public Admin_Log findById(int id);
}
