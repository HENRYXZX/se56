package dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import dao.EvaluateDao;
import domain.Evaluate;
public class EvaluateDaoImpl implements EvaluateDao{

	@Autowired
	private EvaluateDao edao;
	@Override
	public List<Evaluate> findByBlodid(int blogid) {
		// TODO Auto-generated method stub
		return edao.findByBlodid(blogid);
	}

	@Override
	public List<Evaluate> findByUid(int uid) {
		// TODO Auto-generated method stub
		return edao.findByUid(uid);
	}

	@Override
	public int insert(Evaluate evaluate) {
		// TODO Auto-generated method stub
		return edao.insert(evaluate);
	}

	@Override
	public int delete(int id) {
		// TODO Auto-generated method stub
		return edao.delete(id);
	}

	@Override
	public int deleteByBlogid(int blogid) {
		// TODO Auto-generated method stub
		return edao.deleteByBlogid(blogid);
	}

	@Override
	public int skrNumAdd(int evaluateid) {
		// TODO Auto-generated method stub
		return edao.skrNumAdd(evaluateid);
	}

	@Override
	public int skrNumReduce(int evaluateid) {
		// TODO Auto-generated method stub
		return edao.skrNumReduce(evaluateid);
	}

	@Override
	public Evaluate findById(int evaluateid) {
		// TODO Auto-generated method stub
		return edao.findById(evaluateid);
	}
	
	
}
