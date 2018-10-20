package service;


import java.util.List;

import domain.Admin;
import domain.Admin_Log;
import domain.Blog;
import domain.User_Log;

public interface AdminService {
	
	
	public Admin loginValidate(String loginName,String loginPassword);//管理员登录验证
	public List<Admin_Log> findAll(); //列出所有管理员的日志
	public List<User_Log> findByUid(int uid);//找到某个用户的日志		
	
}
