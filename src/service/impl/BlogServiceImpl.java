package service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import domain.Blog;
import domain.Evaluate;
import domain.HotCache;
import domain.Love_contact;
import service.BlogService;
import service.EvaluateService;
import service.UserService;
import dao.BlogDao;
import dao.CollectDao;
import dao.EvaluateDao;
import dao.HotCacheDao;
import dao.LoveDao;
import dao.TopicDao;
@Service
public class BlogServiceImpl implements BlogService {

	@Autowired
	private BlogDao bdao;
	@Autowired
	private TopicDao tdao;
	@Autowired
	private EvaluateDao edao;
	@Autowired
	private HotCacheDao hdao;
	@Autowired
	private LoveDao ldao;
	@Autowired
	private CollectDao cdao;
	@Autowired
	private EvaluateService es;
	@Autowired
	private UserService us;
	@Autowired 
	private HotCacheDao hcdao;
	@Override
	public int addBlog(Blog blog) {
		// TODO Auto-generated method stub
		blog.setWriterTime(new Timestamp(System.currentTimeMillis()));
		blog.setWordNum(blog.getSimplyContent().length());
		if(blog.getSimplyContent().length()>81)
			blog.setSimplyContent(blog.getSimplyContent().substring(0, 81));
		return bdao.insert(blog);
	}

	@Override
	public int removeBlog(int id) {
		// TODO Auto-generated method stub
		Blog b=bdao.findById(id);
		if(b.getTopic()!=null) //有专题
		{
			tdao.blogNumReduce(b.getTopic().getId()); //将专题数-1
		}
		List<Evaluate> elist=edao.findByBlodid(id);
		for(int i=0;i<elist.size();i++) 
		{
			es.removeEvaluate(elist.get(i).getId()); //删除这篇文章的所有评论
		}
		hcdao.deleteByBlogid(id);//删除热门缓存表中的数据
//		List<Love_contact> llist=ldao.findByBlogid(id);
//		for(Love_contact l:llist)
//		{
//			us.removeLove(l.getUid(), id);
//		}
		ldao.deleteByBlogid(id);//删除这篇文章被喜欢的记录
		cdao.deleteByBlogid(id);//删除这篇文章被打赏的记录
		if(bdao.delete(id)==1) return 1;
		else return 0;
	}

	

	@Override
	public Blog findById(int id) {
		// TODO Auto-generated method stub
		return bdao.findById(id);
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
	public List<Blog> findByTopicPending(int id) {
		// TODO Auto-generated method stub
		return bdao.findByTopicPending(id);
	}

	

	@Override
	public List<Blog> findByUidAndZero(int uid) {
		List<Blog> list=bdao.findByUid(uid);
		List<Blog> list2=new ArrayList<Blog>();
		for(Blog b:list)
		{
			if(b.getStatus()==0) list2.add(b); 
		}
		return list2;
	}
	@Override
	public List<Blog> findByUid(int uid) {
		List<Blog> list=bdao.findByUid(uid);
		
		return list;
	}


//	@Override
//	public List<Blog> sortByNewTime(List<Blog> list) {
//		Collections.sort(list, new Comparator<Blog>(){
//            public int compare(Blog a, Blog b) {
//            	long value1=a.getWriterTime().getTime();
//            	long value2=b.getWriterTime().getTime();
//                if(value1 <value2){
//                    return 1;
//                }
//                if(value1 == value2){
//                    return 0;
//                }
//                return -1;
//                
//            }
//        });
//		return list;
//		
//		
//		
//		
//		
//	}
//
//	@Override
//	public List<Blog> sortByHot(List<Blog> list) {
//		
//		Collections.sort(list, new Comparator<Blog>(){
//            public int compare(Blog a, Blog b) {
//                //先按期号降序，如果期号相同按玩法名称降序，如果玩法名称相同按投注时间降序
//                double value1=(a.getReadNum()*1.2+a.getCollectNum()*1.8+a.getCommentsNum()*1.5+a.getLoveNum()*2.0);
//                double value2=(b.getReadNum()*1.2+b.getCollectNum()*1.8+b.getCommentsNum()*1.5+b.getLoveNum()*2.0);	
//                if(value1 <value2){
//                    return 1;
//                }
//                if(value1 == value2){
//                    return 0;
//                }
//                return -1;
//                
//            }
//        });
//		return list;
//	}
	@Override
	public int blogInTopic(int blogid,int topicid) {
		Blog b=new Blog(blogid);
		b.setStatus(2); //2 通过
		b.setTopicid(topicid);
		bdao.updateStatus(b);
		bdao.updateTopic(b);
		return tdao.blogNumAdd(topicid);
	}
	

	@Override
	public int blogApply(int id,int topicid) {
		
		Blog b=new Blog(id);
		b.setTopicid(topicid);
		b.setStatus(1);  //1 待审批
		bdao.updateStatus(b);
		return bdao.updateTopic(b);
	}

	@Override
	public int blogPass(int id,int topicid) {
		Blog b=new Blog(id);
		b.setStatus(2); //2 通过
		bdao.updateStatus(b);
		return tdao.blogNumAdd(topicid);
	}

	@Override
	public int blogWithdraw(int id) {
		//专题号置为0   -1状态为失败
		Blog b=new Blog(id);
		b.setTopicid(0);
		b.setStatus(0); 
		bdao.updateStatus(b);
		return bdao.updateTopic(b);
	}
	@Override
	public int blogRefuse(int id)
	{
		Blog b=new Blog(id);
		b.setStatus(-1);
		return bdao.updateStatus(b);
	}

	@Override
	public List<Blog> findToshowHome(int start) {
		
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
	public List<Integer> findHotW() {
		// TODO Auto-generated method stub
		Timestamp now=new Timestamp(System.currentTimeMillis());
		Timestamp last=new Timestamp(System.currentTimeMillis());
		last.setDate(last.getDate()-7);
		System.out.println(now);
		System.out.println(last);
		return bdao.findHotInTimeZone(now, last);
	}

	@Override
	public List<Integer> findHotD() {
		Timestamp now=new Timestamp(System.currentTimeMillis());
		Timestamp last=new Timestamp(System.currentTimeMillis());
		last.setDate(last.getDate()-1);
		System.out.println(now);
		System.out.println(last);
		return bdao.findHotInTimeZone(now, last);
	}

	@Override
	public List<Integer> findHotM() {
		Timestamp now=new Timestamp(System.currentTimeMillis());
		Timestamp last=new Timestamp(System.currentTimeMillis());
		last.setMonth(last.getMonth()-1);
		System.out.println(now);
		System.out.println(last);
		return bdao.findHotInTimeZone(now, last);
	}

	@Override
	public int deleteByHotType(String type) {
		// TODO Auto-generated method stub
		return hdao.deleteByType(type);
	}
	@Override
	public int insertHotCache(HotCache h) {
		// TODO Auto-generated method stub
		return hdao.insert(h);
	}

	@Override
	public List<HotCache> listHot(String type) {
		// TODO Auto-generated method stub
		if(type.equals("W"))  return hdao.findByType("W");
		else if(type.equals("D")) return hdao.findByType("D");
		else if(type.equals("M")) return hdao.findByType("M");
		return null;
		
	}

	
	
	

}
