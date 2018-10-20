package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import domain.Blog;
import domain.Evaluate;
import domain.HotCache;
import domain.User;
import net.sf.json.JSONObject;
import service.BlogService;
import service.EvaluateService;
import service.TopicService;
import service.UserService;

@Controller
@RequestMapping(value="/Blog")
public class BlogController {
	
	@Autowired
	private UserService us;
	@Autowired
	private BlogService bs;
	
	@Autowired
	private TopicService ts;
	@Autowired
	private EvaluateService es;
	
	

	/*
	 * 首页，分页列出文章
	 * page 页数
	 * 10 默认页大小
	 * */
	@ResponseBody
	@RequestMapping(value="/initializeBlog" , produces="application/json;charset=UTF-8")
	public String initializeBlog(String page,String callback,HttpSession session) throws Exception {
		
		JSONObject json = new JSONObject();
		int p=Integer.parseInt(page);
		List<Blog> list=bs.findToshowHome((p-1)*10); 
		System.out.println(page);
		json.accumulate("BlogData", list);
		String res=callback+"("+json.toString()+")";
		System.out.println(res);
		return res;
	}
	
	

	/*
	 * 文章浏览页面，获得文章信息
	 * 
	 * */
	@ResponseBody
	@RequestMapping(value="/getBlogWhenLook" , produces="application/json;charset=UTF-8")
	public String getBlogWhenLook(String blogid,String callback,HttpSession session) throws Exception {
		
		JSONObject json = new JSONObject();
		Blog b=bs.findById(Integer.parseInt(blogid));	
		b.setUser(us.findById(b.getUser().getId()));
		if(session.getAttribute("user")!=null){
			
			User u=(User)session.getAttribute("user");
//			b.setAlreadyCollect(us.collectValidate(u.getId(),b)); //修改收藏状态
			System.out.println(b.getAlreadyCollect());
			b.setAlreadyLove(us.loveValidate(u.getId(),b));//修改喜欢状态
			b.getUser().setAlreadyFocus(us.focusValidate(u.getId(), b.getUser())); //修改关注状态
			json.accumulate("loginStatus", "yes");
		}	
		else json.accumulate("loginStatus", "no");
		b.getUser().setLoginName("");
		b.getUser().setLoginPassword("");
		bs.readNumAdd(Integer.parseInt(blogid));
		json.accumulate("blogData", b);
		String res=callback+"("+json.toString()+")";
		System.out.println(res);
		return res;
	}
	

	/*
	 * 点赞评论
	 * */
	@ResponseBody
	@RequestMapping(value="/skr" , produces="application/json;charset=UTF-8")
	public String skr(String id,String evaluateid,String callback,HttpSession session) throws Exception {
		
		JSONObject json = new JSONObject();
		if(session.getAttribute("user")!=null){
			
			User usession=(User)session.getAttribute("user");
			if(Integer.parseInt(id)==usession.getId()){
				Evaluate e=new Evaluate();
				e.setId(Integer.parseInt(evaluateid));
				if(us.skrValidate(Integer.parseInt(id),e)==0) //可收藏状态
				{
						us.addSkr(Integer.parseInt(id), Integer.parseInt(evaluateid));
						json.accumulate("result", "correct");
						json.accumulate("alreadySkr", 1);
				}
				else{
					us.removeSkr(Integer.parseInt(id), Integer.parseInt(evaluateid));
					json.accumulate("result", "correct");
					json.accumulate("alreadySkr", 0);
				} 
				
			}
			else json.accumulate("result", "error");
		
		}
		else
		{
			json.accumulate("result", "error");
		}
		String res=callback+"("+json.toString()+")";
		System.out.println(res);
		return res;
	}	

	/*
	 * 文章浏览页面，获得该文章的所有评论，暂时没有分页
	 * */
	@ResponseBody
	@RequestMapping(value="/getEvaluateWhenLook" , produces="application/json;charset=UTF-8")
	public String getEvaluateWhenLook(String blogid,String callback,HttpSession session) throws Exception {
		
		JSONObject json = new JSONObject();
		List<Evaluate> list=es.findByBlodid(Integer.parseInt(blogid));
		
		if(session.getAttribute("user")!=null){
			
			User u=(User)session.getAttribute("user");
			for(int i=0;i<list.size();i++)
			list.get(i).setAlreadySkr(us.skrValidate(u.getId(), list.get(i)));//修改点赞状态
		}	
		json.accumulate("evaluateData", list);
		String res=callback+"("+json.toString()+")";
		System.out.println(res);
		return res;
	}
	

	/*
	 * 列出某专题下最新文章，分页
	 * */
	@ResponseBody
	@RequestMapping(value="/listBlogByTopicNew" , produces="application/json;charset=UTF-8")
	public String listBlogByTopicNew(String page,String topicid,String callback,HttpSession session) throws Exception {
		
		JSONObject json = new JSONObject();
		List<Blog> back=bs.findByTopicAndNew(Integer.parseInt(topicid),(Integer.parseInt(page)-1)*10);
		for(Blog b:back)
		{
			System.out.println("##### title="+b.getTitle());
		}
	
		json.accumulate("blogDataNew",back);
		String res=callback+"("+json.toString()+")";
		System.out.println(res);
		return res;
	}

	/*
	 * 列出某专题下热门文章，分页
	 * page 页数
	 * */
	@ResponseBody
	@RequestMapping(value="/listBlogByTopicHot" , produces="application/json;charset=UTF-8")
	public String listBlogByTopicHot(String page,String topicid,String callback,HttpSession session) throws Exception {
		
		JSONObject json = new JSONObject();
		List<Blog> back=bs.findByTopicAndHot(Integer.parseInt(topicid),(Integer.parseInt(page)-1)*10);
		for(Blog b:back)
		{
			System.out.println(b.getTitle());
		}
		
		json.accumulate("blogDataHot",back);
		String res=callback+"("+json.toString()+")";
		System.out.println(res);
		return res;
	}

	/*
	 * 列出用户的最新文章，分页
	 * page 页数
	 * */
	@ResponseBody
	@RequestMapping(value="/listBlogByUserNew" , produces="application/json;charset=UTF-8")
	public String listBlogByUserNew(String page,String uid,String callback,HttpSession session) throws Exception {
		
		JSONObject json = new JSONObject();
		List<Blog> back=bs.findByUidAndNew(Integer.parseInt(uid),(Integer.parseInt(page)-1)*10);
		for(Blog b:back)
		{
			System.out.println("##### title="+b.getTitle());
		}
	
		json.accumulate("blogDataNew",back);
		String res=callback+"("+json.toString()+")";
		System.out.println(res);
		return res;
	}
	

	/*
	 * 列出用户的热门文章，分页
	 * page 页数
	 * */
	@ResponseBody
	@RequestMapping(value="/listBlogByUserHot" , produces="application/json;charset=UTF-8")
	public String listBlogByUserHot(String page,String uid,String callback,HttpSession session) throws Exception {
		
		JSONObject json = new JSONObject();
		List<Blog> back=bs.findByUidAndHot(Integer.parseInt(uid),(Integer.parseInt(page)-1)*10);
		for(Blog b:back)
		{
			System.out.println(b.getTitle());
		}
		
		json.accumulate("blogDataHot",back);
		String res=callback+"("+json.toString()+")";
		System.out.println(res);
		return res;
	}

	/*
	 * 列出每周热门
	 * */
	@ResponseBody
	@RequestMapping(value="/listHotW" , produces="application/json;charset=UTF-8")
	public String listHotW(String callback,HttpSession session) throws Exception {
		
		JSONObject json = new JSONObject();
		List<HotCache> back=bs.listHot("W");
		json.accumulate("hotCacheData",back);
		String res=callback+"("+json.toString()+")";
		System.out.println(res);
		return res;
	}

	/*
	 * 列出每日热门
	 * */
	@ResponseBody
	@RequestMapping(value="/listHotD" , produces="application/json;charset=UTF-8")
	public String listHotD(String callback,HttpSession session) throws Exception {
		
		JSONObject json = new JSONObject();
		List<HotCache> back=bs.listHot("D");
		json.accumulate("hotCacheData",back);
		String res=callback+"("+json.toString()+")";
		System.out.println(res);
		return res;
	}

	/*
	 * 列出每月热门
	 * */
	@ResponseBody
	@RequestMapping(value="/listHotM" , produces="application/json;charset=UTF-8")
	public String listHotM(String callback,HttpSession session) throws Exception {
		
		JSONObject json = new JSONObject();
		List<HotCache> back=bs.listHot("M");
		json.accumulate("hotCacheData",back);
		String res=callback+"("+json.toString()+")";
		System.out.println(res);
		return res;
	}
	

	/*
	 * 推荐文章
	 * */
	@ResponseBody
	@RequestMapping(value="/RecommendBlog" , produces="application/json;charset=UTF-8")
	public String RecommendBlog(String blogid,String callback,HttpSession session) throws Exception {
		
		JSONObject json = new JSONObject();
		Blog b=bs.findById(Integer.parseInt(blogid));
		List<Blog> back=new ArrayList<Blog>();
		
		if(b.getTopic()==null)
		{
			back=bs.findToshowHome(0); 
		}
		else 
		{
			int topicid=b.getTopic().getId();
			back=bs.findByTopicAndHot(topicid, 0);
		}
		json.accumulate("blogData",back);
		String res=callback+"("+json.toString()+")";
		System.out.println(res);
		return res;
	}
	
	/*
	 * 获得用户数量
	 * 
	 * */
	@ResponseBody
	@RequestMapping(value="/getBlogNum" , produces="application/json;charset=UTF-8")
	public String getBlogName(String callback,HttpSession session) throws Exception {
		
		JSONObject json = new JSONObject();
		int num=bs.findCountOfBlog();
		
		int pageNum=(int)Math.ceil(num/10);
		json.accumulate("blogNum", num);
		json.accumulate("pageNum", pageNum);
		json.accumulate("reuslt", "correct");
		String res=callback+"("+json.toString()+")";
		System.out.println(res);
		return res;
	}
	
}
