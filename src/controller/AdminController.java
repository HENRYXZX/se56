package controller;



import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import domain.Admin;
import domain.Blog;
import domain.Evaluate;
import domain.Topic;
import domain.User;
import net.sf.json.JSONObject;
import service.AdminService;
import service.BlogService;
import service.EvaluateService;
import service.TopicService;
import service.UserService;


@Controller
@RequestMapping(value="/Admin")
public class AdminController {
	
	
	@Autowired
	private UserService us;
	@Autowired
	private BlogService bs;
	
	@Autowired
	private TopicService ts;
	@Autowired
	private EvaluateService es;
	@Autowired
	private AdminService as;
	

	/*
	 * 管理员登录
	 * */
	@ResponseBody
	@RequestMapping(value="/login" ,produces="application/json;charset=UTF-8")
	public String login(String loginName, String loginPassword,
			String callback,HttpSession session) throws Exception {
		

		JSONObject json = new JSONObject();
		Admin admin=as.loginValidate(loginName, loginPassword);
		if(admin==null){
			json.accumulate("result","error");
			json.accumulate("message","账号或密码错误！");
			
		} 
		else{
			json.accumulate("result","correct");
			//由前端获取id并跳转，再又id请求所需数据
			json.accumulate("id",admin.getId()+"");//int转为String
			
			session.setAttribute("admin",admin);
			
		}
		String res=callback+"("+json.toString()+")";
		System.out.println(res);
		return res;
	}

	/*
	 * 获得管理员信息
	 * */
	@ResponseBody
	@RequestMapping(value="/getAdminInf" , produces="application/json;charset=UTF-8")
	public String getAdminInf(String callback,HttpSession session) throws Exception {
		

		JSONObject json = new JSONObject();
		int id=0;
		if(session.getAttribute("admin")!=null)
		{
			Admin ad=(Admin)session.getAttribute("admin");
			json.accumulate("id",ad.getId());
			json.accumulate("loginStatus", "yes");
		}
		else json.accumulate("loginStatus", "no");
		String res=callback+"("+json.toString()+")";
		System.out.println(res);
		return res;
	}

	/*
	 * 审核专题
	 * */
	@ResponseBody
	@RequestMapping(value="/auditTopic" , produces="application/json;charset=UTF-8")
	public String auditTopic(String id,String topicid,String op,String callback,HttpSession session) throws Exception {
		
		JSONObject json = new JSONObject();
		if(session.getAttribute("admin")!=null){
			
			Admin admin=(Admin)session.getAttribute("admin");
			if(Integer.parseInt(id)==admin.getId()){
				int r;
				if(op.equals("通过"))
				{
					r=ts.topicPass(Integer.parseInt(topicid));
					
				}
				else 
				{
					r=ts.removeTopic(Integer.parseInt(topicid));
				}
				if(r==1) json.accumulate("result", "correct");
				else json.accumulate("result", "error");
				
			}
			else json.accumulate("result", "error");
		
		}
		else json.accumulate("result", "error");
		
		String res=callback+"("+json.toString()+")";
		System.out.println(res);
		return res;
	}

	/*
	 * 删除用户
	 * */
	@ResponseBody
	@RequestMapping(value="/deleteUser" , produces="application/json;charset=UTF-8")
	public String deleteUser(String id,String uid,String callback,HttpSession session) throws Exception {
		
		JSONObject json = new JSONObject();
		if(session.getAttribute("admin")!=null){
			
			Admin admin=(Admin)session.getAttribute("admin");
			if(Integer.parseInt(id)==admin.getId()){
				
				if(us.delete(Integer.parseInt(uid)))
				{
					json.accumulate("result", "correct");
				}
				else 
					json.accumulate("result", "error");	
			}
			else json.accumulate("result", "error");
		
		}
		else json.accumulate("result", "error");
		
		String res=callback+"("+json.toString()+")";
		System.out.println(res);
		return res;
	}

	/*
	 * 删除文章
	 * */
	@ResponseBody
	@RequestMapping(value="/deleteBlog" , produces="application/json;charset=UTF-8")
	public String deleteBlog(String id,String blogid,String callback,HttpSession session) throws Exception {
		
		JSONObject json = new JSONObject();
		if(session.getAttribute("admin")!=null){
			
			Admin admin=(Admin)session.getAttribute("admin");
			if(Integer.parseInt(id)==admin.getId()){
				
				if(bs.removeBlog(Integer.parseInt(blogid))==1)
				{
					json.accumulate("result", "correct");
				}
				else 
					json.accumulate("result", "error");
			}
			else json.accumulate("result", "error");
		
		}
		else json.accumulate("result", "error");
		
		String res=callback+"("+json.toString()+")";
		System.out.println(res);
		return res;
	}

	/*
	 * 删除专题
	 * */
	@ResponseBody
	@RequestMapping(value="/deleteTopic" , produces="application/json;charset=UTF-8")
	public String deleteTopic(String id,String topicid,String callback,HttpSession session) throws Exception {
		
		JSONObject json = new JSONObject();
		if(session.getAttribute("admin")!=null){
			
			Admin admin=(Admin)session.getAttribute("admin");
			if(Integer.parseInt(id)==admin.getId()){
				
				if(ts.removeTopic(Integer.parseInt(topicid))==1)
				{
					json.accumulate("result", "correct");
				}
				else 
					json.accumulate("result", "error");
			}
			else json.accumulate("result", "error");
		
		}
		else json.accumulate("result", "error");
		
		String res=callback+"("+json.toString()+")";
		System.out.println(res);
		return res;
	}
	

	/*
	 * 列出用户
 * */
	@ResponseBody
	@RequestMapping(value="/listUser" , produces="application/json;charset=UTF-8")
	public String listUser(String id,String page,String callback,HttpSession session) throws Exception {
		
		JSONObject json = new JSONObject();
		
	
		if(session.getAttribute("admin")!=null){
			
			Admin admin=(Admin)session.getAttribute("admin");
			if(Integer.parseInt(id)==admin.getId()){
				
				List<User> back=us.findAllLimit((Integer.parseInt(page)-1)*10);
				for(User u:back)
				{
					System.out.println(u.getId());
				}
				json.accumulate("userData", back); //返回新评论对象
				json.accumulate("result", "correct");
			}
			else json.accumulate("result", "error");
		
		}
		else json.accumulate("result", "error");
		
		String res=callback+"("+json.toString()+")";
		System.out.println(res);
		return res;
	}
	
	
	/*
	 * 列出文章
	 * */
		@ResponseBody
		@RequestMapping(value="/listBlog" , produces="application/json;charset=UTF-8")
		public String listBlog(String id,String page,String callback,HttpSession session) throws Exception {
			
			JSONObject json = new JSONObject();
			
		
			if(session.getAttribute("admin")!=null){
				
				Admin admin=(Admin)session.getAttribute("admin");
				if(Integer.parseInt(id)==admin.getId()){
					List<Blog> back=bs.findAllLimit((Integer.parseInt(page)-1)*10);
					json.accumulate("blogData", back); //返回新评论对象
					json.accumulate("result", "correct");
				}
				else json.accumulate("result", "error");
			
			}
			else json.accumulate("result", "error");
			
			String res=callback+"("+json.toString()+")";
			System.out.println(res);
			return res;
		}
	
		
	
	
}
