package dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import dao.User_LogDao;
import domain.User_Log;

public class User_LogDaoImpl implements User_LogDao {
	
	@Autowired
	private User_LogDao udao;
	@Override
	public List<User_Log> findByUid(int uid) {
		// TODO Auto-generated method stub
		return udao.findByUid(uid);
	}

	@Override
	public int insert(User_Log u) {
		// TODO Auto-generated method stub
		return udao.insert(u);
	}

	@Override
	public int delete(int id) {
		// TODO Auto-generated method stub
		return udao.delete(id);
	}

	@Override
	public int update(User_Log u) {
		// TODO Auto-generated method stub
		return udao.update(u);
	}

	@Override
	public User_Log findById(int id) {
		// TODO Auto-generated method stub
		return udao.findById(id);
	}

}
