package service;

import java.sql.Timestamp;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import domain.Blog;
import domain.HotCache;

public interface BlogService {

	public List<Blog> findToshowHome(int start);//主界面的推荐文章
	public List<Blog> findByTopicAndNew(int topicid , int start);//专题的最新文章，分页
	public List<Blog> findByTopicAndHot( int topicid ,int start);//专题的最热文章，分页
	public List<Blog> findByTopicPending(int topicid);//列出最在某个专题下待审核的文章
	public List<Blog> findByTopicFail(int topicid);//列出在某个专题下审核失败的文章
	public List<Blog> findByUidAndNew( int uid , int start);//列出某个用户最新文章
	public List<Blog> findByUidAndHot(int uid , int start);//列出某个用户最热文章
	public List<Blog> findAllLimit(int start);//分页列出
	public List<Blog> findByUid(int uid);//列出某位用户的文章
	public int findCountOfBlog();//查找所有的文章数量
	public int blogInTopic(int blogid,int topicid);//直接将文章收录进自己管理的专题
	public List<Blog> findByUidAndZero(int uid);//列出某一用户下未投稿、未收录的文章
	public List<Blog> searchByTitle(String title);//通过标题进行搜索，返回结果
	public List<Integer> findHotW();//获得周热门文章
	public List<Integer> findHotD();//获得每日热门文章
	public List<Integer> findHotM();//获得每月热门文章
	public int addBlog(Blog u);  //增加一篇文章    
	public int removeBlog(int id); //删除一篇文章
	
	public int readNumAdd(int blogid); //阅读数+1   被动函数
	public int collectNumAdd(int blogid);//收藏数+1  主动桉树
	public int loveNumAdd(int blogid);//喜欢数加+1  主动
	public int commentsNumAdd(int blogid);//评论数+1  主动
	
	public int collectNumReduce(int blogid);// 收藏数-1
	public int loveNumReduce(int blogid); //喜欢数-1
	public int commentsNumReduce(int blogid); //评论数-1  是否需要有待考虑
	
	public Blog findById(int id);   //获得指定一个文章对象
	
	public int blogApply(int id,int topicid); //文章投稿
	public int blogPass(int id,int topidid);//审核通过
	public int blogRefuse(int id);//审核拒绝
	public int blogWithdraw(int id);//撤回文章
	
	public int deleteByHotType(String type); //删除文章缓存表下的某种类型的文章，更新
	public int insertHotCache(HotCache h);//插入更新的文章缓存信息
	public List<HotCache> listHot(String type);//获得某热门（每日，每周、每月）其中一种热门缓存数据

}
