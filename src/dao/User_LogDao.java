package dao;

import java.util.List;

import domain.User_Log;

public interface User_LogDao {
	public List<User_Log> findByUid(int uid);
	public int insert(User_Log u);
	public int delete(int id);
	public int update(User_Log u);
	public User_Log findById(int id);
}
