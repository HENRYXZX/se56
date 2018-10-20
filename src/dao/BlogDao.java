package dao;

import java.sql.Timestamp;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import domain.Blog;

public interface BlogDao {
	
	public List<Blog> findToshowHome(int start);
	public List<Blog> findByTopicAndNew(@Param("topicid") int topicid ,@Param("start") int start);
	public List<Blog> findByTopicAndHot(@Param("topicid") int topicid ,@Param("start") int start);
	public List<Blog> findByTopicPending(int topicid);
	public List<Blog> findByTopicFail(int topicid);
	public List<Blog> findByUidAndNew(@Param("uid") int uid ,@Param("start") int start);
	public List<Blog> findByUidAndHot(@Param("uid") int uid ,@Param("start") int start);
	public List<Blog> findByUid(int uid);
	public List<Blog> searchByTitle(String title);
	public List<Blog> findAllLimit(int start);
	
	public List<Integer> findHotInTimeZone(@Param("nowTime") Timestamp nowTime,@Param("lastTime") Timestamp lastTime);
	public int findCountOfBlog();
	//删除专题后，改变的状态
	public int updateWhenTopicRemove(int topicid);
	
	public int insert(Blog u);
	public int delete(int id);
	public int update(Blog u);
	
	public int readNumAdd(int blogid);
	public int collectNumAdd(int blogid);
	public int loveNumAdd(int blogid);
	public int commentsNumAdd(int blogid);

	public int collectNumReduce(int blogid);
	public int loveNumReduce(int blogid);
	public int commentsNumReduce(int blogid);
	
	public Blog findById(int id);
	public int updateStatus(Blog b);
	public int updateTopic(Blog b);
	

}
