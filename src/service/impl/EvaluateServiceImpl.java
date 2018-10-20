package service.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.BlogDao;
import dao.EvaluateDao;
import dao.SkrDao;
import domain.Evaluate;
import domain.Skr_contact;
import service.EvaluateService;

@Service
public class EvaluateServiceImpl implements EvaluateService {

	@Autowired
	private EvaluateDao edao;
	@Autowired
	private BlogDao bdao;
	@Autowired
	private SkrDao sdao;
	@Override
	public int addEvaluate(Evaluate e) {
		
		e.setTime(new Timestamp(System.currentTimeMillis()));
		if(edao.insert(e)==1) 
		{
			bdao.commentsNumAdd(e.getBlogid());
			return e.getId();
		}
		return 0;
	}
	
	@Override
	public boolean removeEvaluate(int id) {
		
		Evaluate e=edao.findById(id);
		int a=edao.delete(id);
		if(a==1){
			bdao.commentsNumReduce(e.getBlogid());
			sdao.deleteByEvaluateid(e.getId());
			return true;
		}else{
			return false;
		}
	}

	@Override
	public List<Evaluate> findByUid(int uid) {
		
		
		return edao.findByUid(uid);
	}

	@Override
	public List<Evaluate> findByBlodid(int blogid) {
		// TODO Auto-generated method stub
		return edao.findByBlodid(blogid);
	}

	@Override
	public boolean removeByBlodid(int blogid) {
	
		int a=edao.deleteByBlogid(blogid);
		if(a==1){
			return true;
		}else{
			return false;
		}
		
	}

	

	@Override
	public int skrNumAdd(int eid) {
		// TODO Auto-generated method stub
		return edao.skrNumAdd(eid);
	}
	
	@Override
	public int skrNumReduce(int eid) {
		// TODO Auto-generated method stub
		return edao.skrNumReduce(eid);
	}

	@Override
	public Evaluate findById(int evaluateid) {
		// TODO Auto-generated method stub
		return edao.findById(evaluateid);
	}
	

	

	

}
