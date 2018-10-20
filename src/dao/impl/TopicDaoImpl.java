package dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import dao.TopicDao;
import domain.Topic;

public class TopicDaoImpl implements TopicDao {

	@Autowired
	private TopicDao tdao;
	@Override
	public List<Topic> findAll() {
		// TODO Auto-generated method stub
		return tdao.findAll();
	}

	@Override
	public int insert(Topic t) {
		// TODO Auto-generated method stub
		return tdao.insert(t);
	}

	@Override
	public int delete(int id) {
		// TODO Auto-generated method stub
		return tdao.delete(id);
	}

	@Override
	public int update(Topic t) {
		// TODO Auto-generated method stub
		return tdao.update(t);
	}

	@Override
	public Topic findById(int id) {
		// TODO Auto-generated method stub
		return tdao.findById(id);
	}

	@Override
	public int updatePass(Topic t) {
		// TODO Auto-generated method stub
		return tdao.updatePass(t);
	}

	@Override
	public int blogNumAdd(int id) {
		// TODO Auto-generated method stub
		return tdao.blogNumAdd(id);
	}

	@Override
	public int blogNumReduce(int id) {
		// TODO Auto-generated method stub
		return tdao.blogNumReduce(id);
	}

	@Override
	public List<Topic> searchByTopicName(String name) {
		// TODO Auto-generated method stub
		return tdao.searchByTopicName(name);
	}

	@Override
	public List<Topic> findByUid(int uid) {
		// TODO Auto-generated method stub
		return tdao.findByUid(uid);
	}
	
	

}
