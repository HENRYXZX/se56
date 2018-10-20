package controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import domain.Blog;
import domain.User;
import net.sf.json.JSONObject;
import service.BlogService;
import service.EvaluateService;
import service.TopicService;
import service.UserService;
@Controller
@RequestMapping(value="/Apply")
public class ApplyController {
	@Autowired
	private UserService us;
	@Autowired
	private BlogService bs;
	
	@Autowired
	private TopicService ts;
	@Autowired
	private EvaluateService es;
	


	/*
	 * 对用用户身份展示出投稿面板中岁需要的信息
	 * id 用户id
	 * topicid 专题id
	 * */
	@ResponseBody
	@RequestMapping(value="/showApplyByUser" , produces="application/json;charset=UTF-8")
	public String showApplyByUser(String id,String topicid,String callback,HttpSession session) throws Exception {
		
		JSONObject json = new JSONObject();
		if(session.getAttribute("user")!=null){
			
			User usession=(User)session.getAttribute("user");
			if(Integer.parseInt(id)==usession.getId()){
				
				List<Blog> bottom=bs.findByUidAndZero(Integer.parseInt(id));
				List<Blog> middle=bs.findByTopicPending(Integer.parseInt(topicid));
				List<Blog> top=bs.findByTopicFail(Integer.parseInt(topicid));
				
				for(int i=0;i<middle.size();i++)
				{	
					if(middle.get(i).getUser().getId()!=Integer.parseInt(id))
					{
						middle.remove(i);
					}
				}
				for(int i=0;i<top.size();i++)
				{
					if(top.get(i).getUser().getId()!=Integer.parseInt(id))
					{
						top.remove(i);
					}
				}
				json.accumulate("result", "correct");
				json.accumulate("blogDataTop", top);
				json.accumulate("blogDataMiddle", middle);
				json.accumulate("blogDataBottom", bottom);
				
				
				
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
	 * 列出专题管理员在投稿面板中所需要的数据（待审核的文章）
	 * */
	@ResponseBody
	@RequestMapping(value="/showApplyByManager" , produces="application/json;charset=UTF-8")
	public String showApplyByManager(String id,String topicid,String callback,HttpSession session) throws Exception {
		
		JSONObject json = new JSONObject();
		if(session.getAttribute("user")!=null){
			
			User usession=(User)session.getAttribute("user");
			if(Integer.parseInt(id)==usession.getId()){
				
				List<Blog> list=bs.findByTopicPending(Integer.parseInt(topicid));
				json.accumulate("applyData", list);
				json.accumulate("result", "correct");
				 
				
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
	 * 用户的申请操作（投稿+撤回）
	 * */
	@ResponseBody
	@RequestMapping(value="/applyOperationByUser" , produces="application/json;charset=UTF-8")
	public String applyOperationByUser(String id,String topicid,String blogid,String callback,HttpSession session) throws Exception {
		
		JSONObject json = new JSONObject();
		if(session.getAttribute("user")!=null){
			
			User usession=(User)session.getAttribute("user");
			if(Integer.parseInt(id)==usession.getId()){
				

				Blog b=bs.findById(Integer.parseInt(blogid));
				if(b.getStatus()==1||b.getStatus()==-1)//申请中-->撤回  or 失败-->撤回
				{
					bs.blogWithdraw(Integer.parseInt(blogid));
					json.accumulate("result", "correct");
				}
				else 
				{
					bs.blogApply(Integer.parseInt(blogid), Integer.parseInt(topicid));
					json.accumulate("result", "correct");
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
	 * 管理员的操作（同意拒绝）
	 * */
	@ResponseBody
	@RequestMapping(value="/applyOperationByManager" , produces="application/json;charset=UTF-8")
	public String applyOperationByManager(String id,String topicid,String blogid,String op,String callback,HttpSession session) throws Exception {
		
		JSONObject json = new JSONObject();
		if(session.getAttribute("user")!=null){
			
			User usession=(User)session.getAttribute("user");
			if(Integer.parseInt(id)==usession.getId()){
				

				Blog b=bs.findById(Integer.parseInt(blogid));
				if(op.equals("收录"))//申请中-->撤回  or 失败-->撤回
				{
					bs.blogPass(Integer.parseInt(blogid),Integer.parseInt(topicid));
					json.accumulate("result", "correct");
				}
				else 
				{
					bs.blogRefuse(Integer.parseInt(blogid));
					json.accumulate("result", "correct");
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
	
	
}
