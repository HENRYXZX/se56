package controller;


import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import domain.Blog;
import domain.Focus_contact;
import domain.User;
import net.sf.json.JSONObject;
import service.BlogService;
import service.UserService;

@Controller
@RequestMapping(value="/Test")
public class test {
	
	
	@Autowired
	private BlogService blogS;
	
		
	@ResponseBody
	@RequestMapping(value="/test" ,produces="application/json;charset=UTF-8")
	public String login(@RequestParam("s") String s,String callback) throws Exception {
		
		
	
		
		System.out.println(s);
		JSONObject json = new JSONObject();
		json.accumulate("back", s);
	
		String res=callback+"("+json.toString()+")";
		System.out.println(res);
		return res;
	}
//		
//		@ResponseBody
//		@RequestMapping(value="/login_error" )
//		public String login_error(String callback) throws Exception {
//			
//			
//			System.out.println("进入login_error");
//			Map<String,String> map=new HashMap<String,String>();
//			map.put("result","error");
//
//			JSONObject resultJSON = JSONObject.fromObject(map); 
//			String res=callback+"("+resultJSON.toString()+")";
//			System.out.println(res);
//			return res;
//		}
//		
//		//测试失败，无法跳转
//		@RequestMapping(value="/validate" ,produces="application/json;charset=UTF-8")
//		public String validate(@RequestParam("login_name") String loginName,@RequestParam("login_password") String loginPassword,
//				String callback) throws Exception {
//			
//			System.out.println("进入 validate");
//			System.out.println(loginName+" "+loginPassword);
//			if(loginName.equals("u"))
//			{
//				System.out.println("redirect:Test/login_success?uid="+1);
//				return "redirect:Test/login_success?uid="+1;
//			}
//			else {
//					System.out.println("输出tttt");
//					return "redirect:Test/login_error?callback="+callback;
//				}
//			}
//	
//		
//		@RequestMapping("/login_success")
//		public ModelAndView login_success(String uid){
//			
//			System.out.println("进入login_success");
//			ModelAndView mv = new ModelAndView();
//			if(Integer.parseInt(uid)==1)
//			mv.setViewName("ttt");
//			else mv.setViewName("list_user");
//			mv.addObject(uid);
//			return mv;
//					
//		}
//		
//		
		
		
		
		
		
		
		
		
		
		
		
}
