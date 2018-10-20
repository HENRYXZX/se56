package service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.AdminDao;
import dao.Admin_LogDao;
import dao.User_LogDao;
import domain.Admin;
import domain.Admin_Log;
import domain.User;
import domain.User_Log;
import service.AdminService;
@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminDao admindao;
	@Autowired
	private Admin_LogDao aldao;
	@Autowired
	private User_LogDao uldao;
	//通过返回对象是否为空，验证登录,返回对象包含可能用到的信息，如id
	@Override
	public Admin loginValidate(String loginName, String loginPassword) {
		
		List<Admin> list=admindao.findAll();
		Admin admin=null;
		for(Admin a:list)
		{
			if(a.getLoginName().equals(loginName)&&a.getLoginPassword().equals(loginPassword))
			{
				admin=a;
				break;
			}
		}
		return admin;
	
	}
	@Override
	public List<Admin_Log> findAll() {
		
		return aldao.findAll();
	}
	@Override
	public List<User_Log> findByUid(int uid) {
		
		return uldao.findByUid(uid);
	}
}
