package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import domain.Topic;
import net.sf.json.JSONObject;
import service.BlogService;
import service.EvaluateService;
import service.TopicService;
import service.UserService;
@Controller
@RequestMapping(value="/Topic")
public class TopicController {

	@Autowired
	private UserService us;
	@Autowired
	private BlogService bs;
	
	@Autowired
	private TopicService ts;
	@Autowired
	private EvaluateService es;
	

	/*
	 * 热门专题（首页）
	 * 前六个
	 * */
	@ResponseBody
	@RequestMapping(value="/initializeTopic" , produces="application/json;charset=UTF-8")
	public String initializeTopic(String page,String callback,HttpSession session) throws Exception {
		
		JSONObject json = new JSONObject();
		List<Topic> list=ts.findAllAndOne();
		List<Topic> list2=new ArrayList<Topic>();
		int i=0;
		for(Topic t:list)
		{
			if(i<6) list2.add(t);
			else break;
		}
		
		json.accumulate("topicData", list2);
		String res=callback+"("+json.toString()+")";
		System.out.println(res);
		return res;
	}
	

	/*
	 * 列出所有专题，暂时没有分页
	 * */
	@ResponseBody
	@RequestMapping(value="/listAllTopic" , produces="application/json;charset=UTF-8")
	public String listAllTopic(String callback,HttpSession session) throws Exception {
		
		JSONObject json = new JSONObject();
		
		List<Topic> back=ts.findAllAndOne();
		json.accumulate("topicData",back);
		String res=callback+"("+json.toString()+")";
		System.out.println(res);
		return res;
	}

	/*
	 * 列出待审核的专题
	 * */
	@ResponseBody
	@RequestMapping(value="/listTopicPending" , produces="application/json;charset=UTF-8")
	public String listTopicPending(String callback,HttpSession session) throws Exception {
		
		JSONObject json = new JSONObject();
		
		List<Topic> back=ts.findAllAndZero();
		json.accumulate("topicData",back);
		String res=callback+"("+json.toString()+")";
		System.out.println(res);
		return res;
	}
	

	/*
	 * 查看专题
	 * */
	@ResponseBody
	@RequestMapping(value="/lookTopic" , produces="application/json;charset=UTF-8")
	public String lookTopic(String topicid,String callback,HttpSession session) throws Exception {
		
		JSONObject json = new JSONObject();
		Topic t=ts.findById(Integer.parseInt(topicid));
		if(session.getAttribute("user")!=null) json.accumulate("loginStatus", "yes");
		else json.accumulate("loginStatus", "no");
		json.accumulate("topicData",t);
		String res=callback+"("+json.toString()+")";
		System.out.println(res);
		return res;
	}
}
