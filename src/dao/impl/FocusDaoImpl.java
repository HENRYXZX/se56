package dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import dao.FocusDao;
import domain.Focus_contact;

public class FocusDaoImpl implements FocusDao {
	
	@Autowired
		
	private FocusDao fdao;
	@Override
	public List<Focus_contact> findAll() {
		// TODO Auto-generated method stub
		return fdao.findAll();
	}

	@Override
	public List<Focus_contact> findByUid(int uid) {
		// TODO Auto-generated method stub
		return fdao.findByUid(uid);
	}

	@Override
	public int insert(Focus_contact u) {
		// TODO Auto-generated method stub
		return fdao.insert(u);
	}

	@Override
	public int deleteByObject(Focus_contact u) {
		// TODO Auto-generated method stub
		return fdao.deleteByObject(u);
	}

	@Override
	public int existValidate(Focus_contact u) {
		// TODO Auto-generated method stub
		return fdao.existValidate(u);
	}

	@Override
	public List<Focus_contact> findMyFans(int fuid) {
		// TODO Auto-generated method stub
		return fdao.findMyFans(fuid);
	}

	

}
