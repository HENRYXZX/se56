package dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import dao.Admin_LogDao;


public class Admin_LogDaoImpl implements Admin_LogDao {

	@Autowired
	private Admin_LogDao adao;
	@Override
	public List<domain.Admin_Log> findAll() {
		// TODO Auto-generated method stub
		return adao.findAll();
	}

	@Override
	public int insert(domain.Admin_Log u) {
		// TODO Auto-generated method stub
		return adao.insert(u);
	}

	@Override
	public int delete(int id) {
		// TODO Auto-generated method stub
		return adao.delete(id);
	}

	@Override
	public int update(domain.Admin_Log u) {
		// TODO Auto-generated method stub
		return adao.update(u);
	}

	@Override
	public domain.Admin_Log findById(int id) {
		// TODO Auto-generated method stub
		return adao.findById(id);
	}

}
