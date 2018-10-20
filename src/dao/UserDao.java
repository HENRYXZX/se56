package dao;

import java.util.List;

import domain.User;



public interface UserDao {
	
	public List<User> findAll();
	public List<User> findAllLimit(int start);
	public List<User> recommedUser();
	public List<User> searchByNickname(String nickname);
	public int findCountOfUser();
	public int insert(User u);
	public int delete(int id);
	public int update(User u);
	public int updateUserInf(User u);
	public User findById(int id);
	public User findByIdOnlyINI(int id);
	public int checkLoginName(String login_name);
	public int checkNickname(String nickname);
	
	public int focusNumReduce(int uid);
	public int fansNumReduce(int uid);
	public int focusNumAdd(int uid);
	public int fansNumAdd(int uid);
}
