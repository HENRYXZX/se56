package controller;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.multipart.MultipartFile;

import domain.Blog;
import domain.Collect_contact;
import domain.Evaluate;
import domain.Focus_contact;
import domain.Love_contact;
import domain.Skr_contact;
import domain.Topic;
import domain.User;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import service.BlogService;
import service.EvaluateService;
import service.TopicService;
import service.UserService;
import util.reverseList;

@Controller
@RequestMapping(value="/User")
public class UserController {
	
	
	@Autowired
	private UserService us;
	@Autowired
	private BlogService bs;
	
	@Autowired
	private TopicService ts;
	@Autowired
	private EvaluateService es;
	
	
	/*
	 * 登录
	 * loginName 账户名
	 * loginPassword 密码
	 * */
	@ResponseBody
	@RequestMapping(value="/login" ,produces="application/json;charset=UTF-8")
	public String login(String loginName, String loginPassword,
			String callback,HttpSession session) throws Exception {
		
		JSONObject json = new JSONObject();
		int id=us.loginValidate(loginName, loginPassword);
		if(id==0){
			json.accumulate("result","error");
			json.accumulate("message","账号或密码错误！");
			
		} 
		else{
			json.accumulate("result","correct");
			//由前端获取id并跳转，再又id请求所需数据
			json.accumulate("uid",id+"");//int转为String
			
			session.setAttribute("user",us.findById(id));
			System.out.println("login ："+session.getId());
			
		}
		String res=callback+"("+json.toString()+")";
		System.out.println(res);
		return res;
	}

	/*
	 * 登出
	 * */
	@ResponseBody
	@RequestMapping(value="/out" ,produces="application/json;charset=UTF-8")
	public String out(String callback,HttpSession session) throws Exception {
		

		JSONObject json = new JSONObject();
		json.accumulate("loginStatus", "no");
		session.removeAttribute("user");
		String res=callback+"("+json.toString()+")";
		System.out.println(res);
		return res;
	}

	/*
	 * 注册
	 * loginName 注册账户名
	 * loginPassword 密码
	 * */
	@ResponseBody
	@RequestMapping(value="/register" ,produces="application/json;charset=UTF-8")
	public String register(String loginName, String loginPassword,
			String callback,HttpSession session) throws Exception {
		
		
		
		
		JSONObject json = new JSONObject();
		
		int id=us.registerValidate(loginName, loginPassword);
		 if(id!=0)
		{
			 json.accumulate("result","correct");
			 json.accumulate("uid",id+"");
			 session.setAttribute("user",us.findById(id));
			 
			
		}
		else
		{
			json.accumulate("result","error");
			json.accumulate("error_message","账户名已存在！");
		}
 
		String res=callback+"("+json.toString()+")";
		System.out.println(res);
		return  res;
	}

	/*
	 * 检查账户名是否重复
	 * loginName 账户名
	 * */
	@ResponseBody
	@RequestMapping(value="/checkLoginName" ,produces="application/json;charset=UTF-8")
	public String checkLoginName(String loginName,String callback) throws Exception {
		

		JSONObject json = new JSONObject();
		if(us.checkLoginName(loginName)){
		
			json.accumulate("result","correct");
			json.accumulate("message","可以使用！");
			
		} 
		else{
			json.accumulate("result","error");
			
			json.accumulate("message","已存在");
		}
		String res=callback+"("+json.toString()+")";
		System.out.println(res);
		return res;
	}

	/*
	 * 检查昵称是否重复
	 * nickname 昵称
	 * */
	@ResponseBody
	@RequestMapping(value="/checkNickname" ,produces="application/json;charset=UTF-8")
	public String checkNickname( String nickname,String callback) throws Exception {
		

		JSONObject json = new JSONObject();
		if(us.checkNickname(nickname)){
		
			json.accumulate("result","correct");
			json.accumulate("message","可以使用！");
			
		} 
		else{
			json.accumulate("result","error");
			
			json.accumulate("message","已存在");
		}
		String res=callback+"("+json.toString()+")";
		System.out.println(res);
		return res;
	}

	/*
	 * 修改用户信息
	 * user 接收新的用户信息
	 * */
	@ResponseBody
	@RequestMapping(value="/alterUserInf" ,produces="application/json;charset=UTF-8")
	public String alterUserInf(User user,String callback,HttpSession session) throws Exception {
		

		JSONObject json = new JSONObject();
		if(session.getAttribute("user")==null) {
			json.accumulate("loginStatus", "no");
		}
		else{
			User usession=(User)session.getAttribute("user");
			if(user.getId()!=usession.getId())
			{
				json.accumulate("loginStatus", "no");
			}
			else if(us.updateUserInf(user))
			{
				
				User unew=us.findById(user.getId());
				json.accumulate("result","correct");
				json.accumulate("loginStatus", "yes");
				session.removeAttribute("user");
				session.setAttribute("user", unew);
				
				json.accumulate("mes", "update session ok!");
				
			} 	
			else{
				json.accumulate("loginStatus", "yes");
				json.accumulate("result","error_db");
			}
		}
		
		String res=callback+"("+json.toString()+")";
		System.out.println(res);
		return res;
	}

	/*
	 * 修改密码
	 * 
	 * */
	@ResponseBody
	@RequestMapping(value="/alterPassword" ,produces="application/json;charset=UTF-8")
	public String alterPassword(String id, String oldPassword, String newPassword,
			String callback,HttpSession session) throws Exception {
		

		JSONObject json = new JSONObject();
		
		User user=us.findById(Integer.parseInt(id));
		
		System.out.println(user.getLoginPassword());
		if(session.getAttribute("user")==null) {
			json.accumulate("loginStatus", "no");
		}
		else{
			User usession=(User)session.getAttribute("user");
			if(Integer.parseInt(id)==usession.getId())
			{
				json.accumulate("loginStatus", "yes");

				if(!user.getLoginPassword().equals(oldPassword))
				{
					json.accumulate("result","error");
					json.accumulate("message", "原密码错误！");
				}
				else{
					
					user.setLoginPassword(newPassword);
					if(us.update(user))
					{
						json.accumulate("result", "correct");
						
					}
					else {
						json.accumulate("result","error_db");
					}
				}
			}
			else json.accumulate("loginStatus", "no");
		}
		
		
		String res=callback+"("+json.toString()+")";
		System.out.println(res);
		return res;
	}
	

	/*
	 * 获得登录用户信息
	 * */
	@ResponseBody
	@RequestMapping(value="/getUserInf" , produces="application/json;charset=UTF-8")
	public String getUserInf(String callback,HttpSession session) throws Exception {
		

		JSONObject json = new JSONObject();
		int id=0;
		System.out.println(" getInf："+session.getId());
		if(session.getAttribute("user")!=null)
		{
			User usession=(User)session.getAttribute("user");
			id=usession.getId();
			json.accumulate("id", usession.getId());
			json.accumulate("image", usession.getImage());
			json.accumulate("nickname", usession.getNickname());
			json.accumulate("loginStatus", "yes");
		}
		else json.accumulate("loginStatus", "no");
		String res=callback+"("+json.toString()+")";
		System.out.println(res);
		return res;
	}
	

	/*
	 * 获得用户全部信息
	 * */
	@ResponseBody
	@RequestMapping(value="/getUserAllInf" , produces="application/json;charset=UTF-8")
	public String getUserAllInf(String id,String callback,HttpSession session) throws Exception {
		

		JSONObject json = new JSONObject();
	
		if(session.getAttribute("user")!=null)
		{
			User usession=(User)session.getAttribute("user");
		
			json.accumulate("user", us.findById(usession.getId()));
			json.accumulate("result", "correct");
		}
		else json.accumulate("loginStatus", "no");
		String res=callback+"("+json.toString()+")";
		System.out.println(res);
		return res;
	
	}
	

	/*
	 * 获得用户信息（对于他人）
	 * */
	@ResponseBody
	@RequestMapping(value="/getUserInfOther" , produces="application/json;charset=UTF-8")
	public String getUserInfOther(String uid,String callback,HttpSession session) throws Exception {
		

		JSONObject json = new JSONObject();
		
		User user=us.findByIdOnlyINI(Integer.parseInt(uid));
		if(session.getAttribute("user")!=null){
			
			User u=(User)session.getAttribute("user");
//			b.setAlreadyCollect(us.collectValidate(u.getId(),b)); //修改收藏状态
			
			user.setAlreadyFocus(us.focusValidate(u.getId(), user)); //修改关注状态
			json.accumulate("loginStatus", "yes");
		}
		else json.accumulate("loginStatus","no");
	
		json.accumulate("user", user);
		json.accumulate("result", "correct");
		String res=callback+"("+json.toString()+")";
		System.out.println(res);
		return res;
	
	}
	

	/*
	 * 打赏文章
	 * */
	@ResponseBody
	@RequestMapping(value="/rewardBlog" , produces="application/json;charset=UTF-8")
	public String rewardBlog(String id,String blogid,String rewardNum,String callback,HttpSession session) throws Exception {
		
		JSONObject json = new JSONObject();
		
	
		if(session.getAttribute("user")!=null){
			
			User usession=(User)session.getAttribute("user");
			if(Integer.parseInt(id)==usession.getId()){
				us.addCollect(Integer.parseInt(id), Integer.parseInt(blogid),Integer.parseInt(rewardNum));
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
	 * 喜欢文章
	 * */
	@ResponseBody
	@RequestMapping(value="/loveBlog" , produces="application/json;charset=UTF-8")
	public String loveBlog(String id,String blogid,String callback,HttpSession session) throws Exception {
		
		JSONObject json = new JSONObject();
		
	
		if(session.getAttribute("user")!=null){
			
			User usession=(User)session.getAttribute("user");
			if(Integer.parseInt(id)==usession.getId()){
				if(us.loveValidate(Integer.parseInt(id), bs.findById(Integer.parseInt(blogid)))==0) //可收藏状态
				{
					
						us.addLove(Integer.parseInt(id), Integer.parseInt(blogid));
						json.accumulate("result", "correct");
						json.accumulate("alreadyLove", 1);
				}
				else{
					us.removeLove(Integer.parseInt(id), Integer.parseInt(blogid));
					json.accumulate("result", "correct");
					json.accumulate("alreadyLove", 0);
				} 
				json.accumulate("result", "error");
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
	 * 关注用户
	 * */
	@ResponseBody
	@RequestMapping(value="/focusUser" , produces="application/json;charset=UTF-8")
	public String focusUser(String id,String fuid,String callback,HttpSession session) throws Exception {
		
		JSONObject json = new JSONObject();
		
	
		if(session.getAttribute("user")!=null){
			
			User usession=(User)session.getAttribute("user");
			if(Integer.parseInt(id)==usession.getId()){
				if(us.focusValidate(Integer.parseInt(id), us.findById(Integer.parseInt(fuid)))==0) //可关注状态
				{
						us.addFocus(Integer.parseInt(id), Integer.parseInt(fuid));
						json.accumulate("result", "correct");
						json.accumulate("alreadyFocus", 1);
				}
				else{
					us.removeFocus(Integer.parseInt(id), Integer.parseInt(fuid));
					json.accumulate("result", "correct");
					json.accumulate("alreadyFocus", 0);
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
	 * 留下评论
	 * */
	@ResponseBody
	@RequestMapping(value="/evaluateBlog" , produces="application/json;charset=UTF-8")
	public String evaluateBlog(String id,String blogid,String content,String callback,HttpSession session) throws Exception {
		
		JSONObject json = new JSONObject();
		
	
		if(session.getAttribute("user")!=null){
			
			User usession=(User)session.getAttribute("user");
			if(Integer.parseInt(id)==usession.getId()){
				
				Evaluate e=new Evaluate(Integer.parseInt(id),Integer.parseInt(blogid),content);
				int eid=es.addEvaluate(e);
				if(eid!=0) //插入成功，返回id
				{
					json.accumulate("newEvaluateData", es.findById(eid)); //返回新评论对象
					json.accumulate("result", "correct");
					
				}
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
	 * 列出用户喜欢的文章
	 * uid 用户id
	 * */
	@ResponseBody
	@RequestMapping(value="/listUserLove" , produces="application/json;charset=UTF-8")
	public String listUserLove(String uid,String callback,HttpSession session) throws Exception {
		
		JSONObject json = new JSONObject();
		List<Love_contact> back=us.findMyLove(Integer.parseInt(uid));
		json.accumulate("loveData",back); //返回新评论对象
		json.accumulate("result", "correct");
		String res=callback+"("+json.toString()+")";
		System.out.println(res);
		return res;
	}
	
	

	/*
	 * 列出用户关注的对象
	 * uid 用户id
	 * */
	@ResponseBody
	@RequestMapping(value="/listUserFocus" , produces="application/json;charset=UTF-8")
	public String listUserFocus(String uid,String callback,HttpSession session) throws Exception {
		
		JSONObject json = new JSONObject();
		List<Focus_contact> back=us.findMyFocus(Integer.parseInt(uid));
		json.accumulate("focusData",back); //返回新评论对象
		json.accumulate("result", "correct");
		String res=callback+"("+json.toString()+")";
		System.out.println(res);
		return res;
	}

	/*
	 * 列出用户所有评论
	 * uid 用户id
	 * */
	@ResponseBody
	@RequestMapping(value="/listUserEvaluate" , produces="application/json;charset=UTF-8")
	public String listUserEvaluate(String uid,String callback,HttpSession session) throws Exception {
		
		JSONObject json = new JSONObject();
		List<Evaluate> back=es.findByUid(Integer.parseInt(uid));
		json.accumulate("evaluateData",back); //返回新评论对象
		json.accumulate("result", "correct");
		String res=callback+"("+json.toString()+")";
		System.out.println(res);
		return res;
	}
	

	/*
	 * 获得用户信息统计
	 * uid 用户id
	 * */
	@ResponseBody
	@RequestMapping(value="/getStatisticsByUser" , produces="application/json;charset=UTF-8")
	public String getStatisticsByUser(String uid,String callback,HttpSession session) throws Exception {
		
		JSONObject json = new JSONObject();
		List<Blog> back=bs.findByUid(Integer.parseInt(uid));
		User u=us.findById(Integer.parseInt(uid));
		int allWordNum=0;
		int allLoveNum=0;
		for(Blog b:back){ 
			
			allWordNum+=b.getWordNum();
			allLoveNum+=b.getLoveNum();
		}
		json.accumulate("result", "correct");
		json.accumulate("focusNum", u.getFocusNum());
		json.accumulate("fansNum", u.getFansNum());
		json.accumulate("blogNum", back.size());
		json.accumulate("allWordNum",allWordNum);
		json.accumulate("allLoveNum", allLoveNum);
		String res=callback+"("+json.toString()+")";
		System.out.println(res);
		return res;
	}

	/*
	 * 写文章
	 * b 接收文章内容
	 * */
	@ResponseBody
	@RequestMapping(value="/writerBlog" , produces="application/json;charset=UTF-8")
	public String writerBlog(Blog b,HttpSession session) throws Exception {
		
		
		
//		System.out.println("写文章来了！！！！");
//		System.out.println(b.toString());
		JSONObject json = new JSONObject();
		int id=b.getId();
		
		if(session.getAttribute("user")!=null){
			
			User usession=(User)session.getAttribute("user");
			if(id==usession.getId()){
				
				b.setUid(id);
				if(bs.addBlog(b)==1)
				{
					json.accumulate("result", "correct");
				}
				else {
					json.accumulate("result", "error");
					System.out.println("插不进去");
				}
			}
			else json.accumulate("result", "error");
		
		}
		else json.accumulate("result", "error");
		System.out.println("**********************");
		String res=json.toString();
		System.out.println(res);
		return res;
	}
	

	/*
	 * 通过专题名搜素
	 * key 关键字
	 * */
	@ResponseBody
	@RequestMapping(value="/searchTopic" , produces="application/json;charset=UTF-8")
	public String searchTopic(String key,String callback,HttpSession session) throws Exception {
		
		JSONObject json = new JSONObject();
		List<Topic> back=ts.searchByTopicName(key);
		
		json.accumulate("result", "correct");
		json.accumulate("topicData",back);
		String res=callback+"("+json.toString()+")";
		System.out.println(res);
		return res;
	}

	/*
	 * 通过文章标题搜索
	 * key 关键字
	 * */
	@ResponseBody
	@RequestMapping(value="/searchBlog" , produces="application/json;charset=UTF-8")
	public String searchBlog(String key,String callback,HttpSession session) throws Exception {
		
		JSONObject json = new JSONObject();
		List<Blog> back=bs.searchByTitle(key);
		json.accumulate("result", "correct");
		json.accumulate("blogData",back);
		String res=callback+"("+json.toString()+")";
		System.out.println(res);
		return res;
	}

	/*
	 * 通过昵称搜索
	 * key 关键字
	 * */
	@ResponseBody
	@RequestMapping(value="/searchUser" , produces="application/json;charset=UTF-8")
	public String searchUser(String key,String callback,HttpSession session) throws Exception {
		
		JSONObject json = new JSONObject();
		System.out.println(key);
		List<User> back=us.searchByNickname(key);
		if(session.getAttribute("user")!=null){
			User u=(User)session.getAttribute("user");
//			b.setAlreadyCollect(us.collectValidate(u.getId(),b)); //修改收藏状态
			for(int i=0;i<back.size();i++){
				if(back.get(i).getId()==u.getId()) back.remove(i); 
					
				back.get(i).setAlreadyFocus(us.focusValidate(u.getId(), back.get(i)));
			}
			
		}	
		json.accumulate("result", "correct");
		json.accumulate("allLoveNum",back);
		String res=callback+"("+json.toString()+")";
		System.out.println(res);
		return res;
	}

	/*
	 * 创建专题
	 * t 接收创建专题的信息
	 * */
	@ResponseBody
	@RequestMapping(value="/createTopic" , produces="application/json;charset=UTF-8")
	public String createTopic(Topic t,String callback,HttpSession session) throws Exception {
		
		JSONObject json = new JSONObject();
		int id=t.getId();
		
		if(session.getAttribute("user")!=null){
			
			User usession=(User)session.getAttribute("user");
			if(id==usession.getId()){
				
				t.setManagerid(id);
				if(ts.addTopic(t)==1)
					json.accumulate("result", "correct");
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
	 * 上传图片
	 * id 验证登录id
	 * myFile 文件对象
	 * */
	@ResponseBody
	@RequestMapping(value="/uploadPic" ,method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	public String uploadPic(String id, MultipartFile myFile,String  op,HttpSession session,HttpServletRequest request) throws Exception {
		
		JSONObject json = new JSONObject();
		
		if(session.getAttribute("user")!=null){
			
			User usession=(User)session.getAttribute("user");
			if(Integer.parseInt(id)==usession.getId()){
				
				String lib="uploadPicLib";
				String realpath = request.getServletContext().getRealPath(lib);
				String fileName = myFile.getOriginalFilename(); 
				String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
				String uuid = UUID.randomUUID().toString()+"."+suffix;
				
				File targetFile = new File(realpath, uuid); 
				if(!targetFile.exists()){  
		            targetFile.mkdirs();  
		        } 
				//上传 
		        try {   
		        	myFile.transferTo(targetFile);
		        	System.out.println("成功上传");
		        	json.accumulate("result", "correct");
		        	 json.accumulate("path", lib+File.separator+File.separator+uuid);
		        	
		        	if(op!=null&&op.equals("updateImage"))
		        	{
		        		User uu=us.findById(Integer.parseInt(id));
		        		uu.setImage(lib+File.separator+File.separator+uuid);
		        		us.update(uu);
		        		
		        		session.removeAttribute("user");
		        		session.setAttribute("user", uu);
		        		
		        	}
		        } catch (Exception e) {  
		            e.printStackTrace();  
		            json.accumulate("result", "error");
		        }  
				
			}
			else json.accumulate("result", "error");
		
		}
		else json.accumulate("result", "error");
		
		String res=json.toString();
		System.out.println(res);
		return res;
	}
	
	

	/*
	 * 列出用户管理的专题
	 * uid 用户id
	 *
	 * */
	@ResponseBody
	@RequestMapping(value="/listUserTopic" , produces="application/json;charset=UTF-8")
	public String listUserTopic(String uid,String callback,HttpSession session) throws Exception {
		
		JSONObject json = new JSONObject();
		
		List<Topic> list=ts.findAllAndOne();
		List<Topic> back=new ArrayList<Topic>();
		for(Topic t:list)
		{
			if(t.getManager().getId()==Integer.parseInt(uid)) back.add(t);
		}
		json.accumulate("topicData",back);
		String res=callback+"("+json.toString()+")";
		System.out.println(res);
		return res;
	}

	/*
	 * 将文章直接收录进自己管理的专题
	 * id 验证id
	 * blogid 文章id
	 * topicid 专题id
	 * */
	@ResponseBody
	@RequestMapping(value="/letMyBlogInTopic" , produces="application/json;charset=UTF-8")
	public String letMyBlogInTopic(String id,String blogid,String topicid,String callback,HttpSession session) throws Exception {
		
		JSONObject json = new JSONObject();
		
		if(session.getAttribute("user")!=null){
			
			User usession=(User)session.getAttribute("user");
			if(Integer.parseInt(id)==usession.getId()){
				
				
				if(bs.blogInTopic(Integer.parseInt(blogid), Integer.parseInt(topicid))==1)
				{
					json.accumulate("reuslt", "correct");
					System.out.println("Blog in ToPIC SUCCESS"	);
				}
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
	 * 列出有用户未收录的文章（不包含待审核和失败状态的文章）
	 * uid 用户id
	 * */
	@ResponseBody
	@RequestMapping(value="/findByUidAndZero" , produces="application/json;charset=UTF-8")
	public String findByUidAndZero(String id,String callback,HttpSession session) throws Exception {
		
		JSONObject json = new JSONObject();
		
		
		List<Blog> back=bs.findByUidAndZero(Integer.parseInt(id));
		json.accumulate("blogData", back);
		json.accumulate("reuslt", "correct");
		

		
		String res=callback+"("+json.toString()+")";
		System.out.println(res);
		return res;
	}
	

	/*
	 * 删除文章
	 * id 验证id
	 * blogid 文章id
	 *
	 * */
	@ResponseBody
	@RequestMapping(value="/deleteMyBlog" , produces="application/json;charset=UTF-8")
	public String deleteMyBlog(String id, String blogid,String callback,HttpSession session,HttpServletRequest request) throws Exception {
		
		JSONObject json = new JSONObject();
		
		if(session.getAttribute("user")!=null){
			
			User usession=(User)session.getAttribute("user");
			if(Integer.parseInt(id)==usession.getId()){
				
				if(bs.removeBlog(Integer.parseInt(blogid))==1)
				{
					json.accumulate("result", "删除成功！");
				}
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
	 * 从专题中将文章退稿
	 * id 验证id （专题管理员id）
	 * blogid 文章id
	 * */
	@ResponseBody
	@RequestMapping(value="/OutBlogFromTopic" ,method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	public String OutBlogFromTopic(String id, String blogid,String callback,HttpSession session,HttpServletRequest request) throws Exception {
		
		JSONObject json = new JSONObject();
		
		if(session.getAttribute("user")!=null){
			
			User usession=(User)session.getAttribute("user");
			if(Integer.parseInt(id)==usession.getId()){
					
				Blog b=bs.findById(Integer.parseInt(blogid));
				if(b.getTopic().getManager().getId()==usession.getId())
				{
					if(bs.blogWithdraw(Integer.parseInt(blogid))==1)
					{
						json.accumulate("result", "退稿成功！");
					}
					else json.accumulate("result", "error");
				}
				
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
	 * 推荐用户列表
	 * 
	 * */
	@ResponseBody
	@RequestMapping(value="/recommedUser" , produces="application/json;charset=UTF-8")
	public String recommedUser(String callback,HttpSession session) throws Exception {
		
		JSONObject json = new JSONObject();
		List<User> back=us.recommedUser();
		json.accumulate("userData", back);
		json.accumulate("reuslt", "correct");
		String res=callback+"("+json.toString()+")";
		System.out.println(res);
		return res;
	}
	
	/*
	 * 获得用户数量
	 * 
	 * */
	@ResponseBody
	@RequestMapping(value="/getUserNum" , produces="application/json;charset=UTF-8")
	public String getUserName(String callback,HttpSession session) throws Exception {
		
		JSONObject json = new JSONObject();
		int num=us.findCountOfUser();
		int pageNum=(int)Math.ceil(num/10);
		json.accumulate("userNum", num);
		json.accumulate("pageNum", pageNum);
		json.accumulate("reuslt", "correct");
		String res=callback+"("+json.toString()+")";
		System.out.println(res);
		return res;
	}
	
	
	

}
