package dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import dao.LoveDao;
import domain.Love_contact;

public class LoveDaoImpl implements LoveDao {

	@Autowired
	private LoveDaoImpl ldao;
	@Override
	public List<Love_contact> findAll() {
		// TODO Auto-generated method stub
		return ldao.findAll();
	}

	@Override
	public List<Love_contact> findByUid(int uid) {
		// TODO Auto-generated method stub
		return ldao.findByUid(uid);
	}

	@Override
	public int insert(Love_contact u) {
		// TODO Auto-generated method stub
		return ldao.insert(u);
	}

	@Override
	public int deleteByObject(Love_contact u) {
		// TODO Auto-generated method stub
		return ldao.deleteByObject(u);
	}

	@Override
	public int existValidate(Love_contact u) {
		// TODO Auto-generated method stub
		return ldao.existValidate(u);
	}

	@Override
	public int deleteByBlogid(int blogid) {
		// TODO Auto-generated method stub
		return ldao.deleteByBlogid(blogid);
	}

	@Override
	public List<Love_contact> findByBlogid(int blogid) {
		
		return ldao.findByBlogid(blogid);
	}

}
