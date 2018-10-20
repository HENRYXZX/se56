package service;

import java.util.List;

import domain.Topic;

public interface TopicService {
	
	public List<Topic> findAllAndOne();//获得所有通过审核的专题
	public List<Topic> findAllAndZero();//获得所有审核中的专题
	public List<Topic> searchByTopicName(String name);//通过专题名搜索
	
	public int addTopic(Topic t); //申请专题（添加）
	public int removeTopic(int id);//删除专题
	public int update(Topic t);//更新
	public Topic findById(int id);//获得某一专题数据
	
	public int blogNumAdd(int id);//文章数自增
	public int blogNumReduce(int id);//文章数减一
	
	public int topicOut(int id);//审核不通过
	public int topicPass(int id);//审核通过
}
