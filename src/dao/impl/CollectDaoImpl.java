package dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import dao.CollectDao;
import domain.Collect_contact;

public class CollectDaoImpl implements CollectDao {

	@Autowired
	private CollectDao cdao;
	
	@Override
	public List<Collect_contact> findAll() {
		// TODO Auto-generated method stub
		
		return cdao.findAll();
	}

	@Override
	public List<Collect_contact> findByUid(int uid) {
		// TODO Auto-generated method stub
		return cdao.findByUid(uid);
	}

	@Override
	public int insert(Collect_contact u) {
		// TODO Auto-generated method stub
		return cdao.insert(u);
	}

	@Override
	public int deleteByObject(Collect_contact u) {
		// TODO Auto-generated method stub
		return cdao.deleteByObject(u);
	}

	@Override
	public int existValidate(Collect_contact u) {
		// TODO Auto-generated method stub
		return cdao.existValidate(u);
	}

	@Override
	public int deleteByUid(int uid) {
		// TODO Auto-generated method stub
		return cdao.deleteByUid(uid);
	}

	@Override
	public int deleteByBlogid(int blogid) {
		// TODO Auto-generated method stub
		return cdao.deleteByBlogid(blogid);
	}

	

}
