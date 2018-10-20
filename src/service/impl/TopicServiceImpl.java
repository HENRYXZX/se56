package service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.BlogDao;
import dao.TopicDao;
import domain.Topic;
import service.TopicService;

@Service
public class TopicServiceImpl implements TopicService{

	@Autowired
	private TopicDao tdao;
	@Autowired
	private BlogDao bdao;
	@Override
	public int addTopic(Topic t) {
		// TODO Auto-generated method stub
		
		return tdao.insert(t);
	}

	@Override
	public int removeTopic(int id) {
		// TODO Auto-generated method stub
		bdao.updateWhenTopicRemove(id);
		return tdao.delete(id);
	}

	@Override
	public int update(Topic t) {
		// TODO Auto-generated method stub
		return tdao.update(t);
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
	public List<Topic> findAllAndOne() {
		// TODO Auto-generated method stub
		 List<Topic> list=tdao.findAll();
		 List<Topic> back=new ArrayList<Topic>();
		 for(Topic s:list)
		 {
			 if(s.getStatus()==1) back.add(s);
			  
		 }
		return back;
	}

	@Override
	public List<Topic> findAllAndZero() {
		List<Topic> list=tdao.findAll();
		List<Topic> back=new ArrayList<Topic>();
		 for(Topic s:list)
		 {
			 if(s.getStatus()==0) back.add(s);
			  
		 }
		return back;
	}

	@Override
	public int topicOut(int id) {
		
		
		return tdao.delete(id);
	}

	@Override
	public int topicPass(int id) {
		//更新状态和创建时间
		Topic t=new Topic(id,1);
		t.setCreateTime(new Timestamp(System.currentTimeMillis()));
		return tdao.updatePass(t);
	}

	@Override
	public List<Topic> searchByTopicName(String name) {
		// TODO Auto-generated method stub
		return tdao.searchByTopicName(name);
	}

	@Override
	public Topic findById(int id) {
		// TODO Auto-generated method stub
		return tdao.findById(id);
	}

}
