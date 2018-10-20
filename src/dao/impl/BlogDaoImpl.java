package dao.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;


import dao.BlogDao;
import domain.Blog;

public class BlogDaoImpl implements BlogDao {

	@Autowired
	private BlogDao bdao;
	@Override
	public int insert(Blog blog) {
		// TODO Auto-generated method stub
		return bdao.insert(blog);
	}

	@Override
	public int delete(int id) {
		// TODO Auto-generated method stub
		return bdao.delete(id);
	}

	@Override
	public int update(Blog u) {
		// TODO Auto-generated method stub
		return bdao.update(u);
	}

	@Override
	public Blog findById(int id) {
		// TODO Auto-generated method stub
		return bdao.findById(id);
	}

	
	@Override
	public List<Blog> findByTopicPending(int id) {
		// TODO Auto-generated method stub
		return bdao.findByTopicPending(id);
	}

	@Override
	public List<Blog> findByUid(int id) {
		// TODO Auto-generated method stub
		return bdao.findByUid( id);
	}

	

	@Override
	public int readNumAdd(int blogid) {
		// TODO Auto-generated method stub
		return bdao.readNumAdd(blogid);
	}

	@Override
	public int collectNumAdd(int blogid) {
		// TODO Auto-generated method stub
		return bdao.collectNumAdd(blogid);
	}

	@Override
	public int loveNumAdd(int blogid) {
		// TODO Auto-generated method stub
		return bdao.loveNumAdd(blogid);
	}

	@Override
	public int commentsNumAdd(int blogid) {
		// TODO Auto-generated method stub
		return bdao.commentsNumAdd(blogid);
	}

	
	@Override
	public int collectNumReduce(int blogid) {
		// TODO Auto-generated method stub
		return bdao.collectNumReduce(blogid);
	}

	@Override
	public int loveNumReduce(int blogid) {
		// TODO Auto-generated method stub
		return bdao.loveNumReduce(blogid);
	}

	@Override
	public int commentsNumReduce(int blogid) {
		// TODO Auto-generated method stub
		return bdao.commentsNumReduce(blogid);
	}

	
	@Override
	public int updateStatus(Blog b) {
		// TODO Auto-generated method stub
		return bdao.updateStatus(b);
	}

	@Override
	public int updateTopic(Blog b) {
		// TODO Auto-generated method stub
		return bdao.updateTopic(b);
	}

	@Override
	public List<Blog> findToshowHome(int start) {
		// TODO Auto-generated method stub
		return bdao.findToshowHome(start);
	}

	@Override
	public List<Blog> findByTopicAndNew(int topicid, int start) {
		// TODO Auto-generated method stub
		return bdao.findByTopicAndNew(topicid, start);
	}

	@Override
	public List<Blog> findByTopicAndHot(int topicid, int start) {
		// TODO Auto-generated method stub
		return bdao.findByTopicAndHot(topicid, start);
	}

	@Override
	public List<Blog> findByUidAndNew(int uid, int start) {
		// TODO Auto-generated method stub
		return bdao.findByUidAndNew(uid, start);
	}

	@Override
	public List<Blog> findByUidAndHot(int uid, int start) {
		// TODO Auto-generated method stub
		return bdao.findByUidAndHot(uid, start);
	}

	@Override
	public int updateWhenTopicRemove(int topicid) {
		// TODO Auto-generated method stub
		return bdao.updateWhenTopicRemove(topicid);
	}

	@Override
	public List<Blog> searchByTitle(String title) {
		// TODO Auto-generated method stub
		return bdao.searchByTitle(title);
	}

	@Override
	public List<Blog> findByTopicFail(int topicid) {
		// TODO Auto-generated method stub
		return bdao.findByTopicFail(topicid);
	}

	@Override
	public List<Blog> findAllLimit(int start) {
		// TODO Auto-generated method stub
		return bdao.findAllLimit(start);
	}

	@Override
	public int findCountOfBlog() {
		// TODO Auto-generated method stub
		return bdao.findCountOfBlog();
	}

	@Override
	public List<Integer> findHotInTimeZone(Timestamp nowTime, Timestamp lastTime) {
		// TODO Auto-generated method stub
		return bdao.findHotInTimeZone(nowTime, lastTime);
	}

}
