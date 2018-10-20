package dao.impl;

import java.util.List;

import dao.UserDao;
import domain.User;

import org.springframework.beans.factory.annotation.Autowired;

public class UserDaoImpl implements UserDao{
	
	@Autowired
	private UserDao user;
	
	public List<User> findAll() {
		return user.findAll();
	}

	public int insert(User u) {
		// TODO Auto-generated method stub
		return user.insert(u);
	}

	public int delete(int id) {
		// TODO Auto-generated method stub
		return user.delete(id);
	}

	public int updateUserInf(User u) {
		// TODO Auto-generated method stub
		return user.updateUserInf(u);
	}

	public User findById(int id) {
		// TODO Auto-generated method stub
		return user.findById(id);
	}

	@Override
	public int checkLoginName(String login_name) {
		// TODO Auto-generated method stub
		return user.checkLoginName(login_name);
	}

	@Override
	public int checkNickname(String nickname) {
		// TODO Auto-generated method stub
		return user.checkNickname(nickname);
	}

	@Override
	public int focusNumReduce(int uid) {
		// TODO Auto-generated method stub
		return user.focusNumReduce(uid);
	}

	@Override
	public int fansNumReduce(int uid) {
		// TODO Auto-generated method stub
		return user.fansNumReduce(uid);
	}

	@Override
	public int focusNumAdd(int uid) {
		// TODO Auto-generated method stub
		return user.focusNumAdd(uid);
	}

	@Override
	public int fansNumAdd(int uid) {
		// TODO Auto-generated method stub
		return user.fansNumAdd(uid);
	}

	@Override
	public User findByIdOnlyINI(int id) {
		// TODO Auto-generated method stub
		return user.findByIdOnlyINI(id);
	}

	@Override
	public List<User> searchByNickname(String nickname) {
		// TODO Auto-generated method stub
		return user.searchByNickname(nickname);
	}

	@Override
	public int update(User u) {
		// TODO Auto-generated method stub
		return user.update(u);
	}

	@Override
	public List<User> findAllLimit(int start) {
		// TODO Auto-generated method stub
		return user.findAllLimit(start);
	}

	@Override
	public int findCountOfUser() {
		// TODO Auto-generated method stub
		return user.findCountOfUser();
	}

	@Override
	public List<User> recommedUser() {
		// TODO Auto-generated method stub
		return user.recommedUser();
	}
	
}
