package dao.impl;

import org.springframework.beans.factory.annotation.Autowired;

import dao.SkrDao;
import domain.Skr_contact;

public class SkrDaoImpl implements SkrDao {

	@Autowired
	private SkrDao sdao;
	@Override
	public int insert(Skr_contact skr) {
		// TODO Auto-generated method stub
		return sdao.insert(skr);
	}

	@Override
	public int deleteByObject(Skr_contact skr) {
		// TODO Auto-generated method stub
		return sdao.deleteByObject(skr);
	}

	@Override
	public int deleteByEvaluateid(int id) {
		// TODO Auto-generated method stub
		return sdao.deleteByEvaluateid(id);
	}

	@Override
	public int existValidate(Skr_contact skr) {
		// TODO Auto-generated method stub
		return sdao.existValidate(skr);
	}

}
