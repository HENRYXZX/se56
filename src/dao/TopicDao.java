package dao;

import java.util.List;

import domain.Topic;

public interface TopicDao {

	public List<Topic> findAll();
	public List<Topic> searchByTopicName(String name);
	public List<Topic> findByUid(int uid);
	public int insert(Topic t);
	public int delete(int id);
	public int update(Topic t);
	public int updatePass(Topic t);
	public Topic findById(int id);
	public int blogNumAdd(int id);
	public int blogNumReduce(int id);
}
