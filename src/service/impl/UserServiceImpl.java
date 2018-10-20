package service.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import domain.Blog;
import domain.Collect_contact;
import domain.Evaluate;
import domain.Focus_contact;
import domain.Love_contact;
import domain.Skr_contact;
import domain.Topic;
import domain.User;
import dao.BlogDao;
import dao.CollectDao;
import dao.EvaluateDao;
import dao.FocusDao;
import dao.LoveDao;
import dao.SkrDao;
import dao.TopicDao;
import dao.UserDao;
import service.UserService;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserDao userdao;
	@Autowired
	private FocusDao fdao;
	@Autowired
	private CollectDao cdao;
	@Autowired
	private LoveDao ldao;
	@Autowired
	private SkrDao sdao;
	@Autowired
	private BlogDao bdao;
	@Autowired
	private EvaluateDao edao;
	@Autowired
	private TopicDao tdao;
	@Autowired
	private BlogServiceImpl bs;
	@Autowired
	private TopicServiceImpl ts;
	public List<User> findAll() {
		
		return userdao.findAll();
	}

	public int insert(User u) {
		
		//插入=注册  写入注册时间、最后登录时间
		u.setImage("uploadPicLib\\\\ffb6849c-f79d-4eb0-b4a8-b24474c285b7.jpg");
		if(userdao.insert(u)==1) return u.getId();
		return 0;
		
	}

	
	public boolean delete(int id) {		
		List<Blog> blist=bdao.findByUid(id);
		for(int i=0;i<blist.size();i++)
		{
			bs.removeBlog(blist.get(i).getId());//删除文章
		}
		List<Focus_contact> flist=fdao.findByUid(id);
		for(Focus_contact f:flist)
		{
			removeFocus(id,f.getFocus_user().getId());//帮你取消关注
		}
		
		List<Focus_contact> flist2=fdao.findMyFans(id);
		for(Focus_contact f: flist2)
		{
			removeFocus(f.getUid(),id);//帮关注你的人取消关注
		}
		List<Topic> tlist=tdao.findByUid(id);
		for(Topic t:tlist)
		{
			ts.removeTopic(t.getId());//删除该用户所有的专题 （包含文章状态重置）
		}
		List<Love_contact> llist=ldao.findByUid(id);
		for(Love_contact l:llist)
		{
			removeLove(id,l.getBlog().getId());//
		}
		
		int a=userdao.delete(id);
		
		if(a==1){
			return true;
		}else{
			return false;
		}
	}

	public boolean updateUserInf(User u) {
		int a=userdao.updateUserInf(u);
		if(a==1){
			return true;
		}else{
			return false;
		}
	}

	public User findById(int id) {
		// TODO Auto-generated method stub
		return userdao.findById(id);
	}

	@Override
	public boolean checkLoginName(String loginName) {
		// TODO Auto-generated method stub
		if(userdao.checkLoginName(loginName)==0) return true;
		else return false;
	}

	@Override
	public boolean checkNickname(String nickname) {
		// TODO Auto-generated method stub
		List<User> userlist=userdao.findAll();
		for(User u:userlist)
		{
			if(u.getNickname().equals(nickname))
			{
				return false;
			}
		}
		return true;
	}
	

	@Override
	public int loginValidate(String loginName, String loginPassword) {
		// TODO Auto-generated method stub
		List<User> userlist=userdao.findAll();
		int id;
		for(User u:userlist)
		{
			if(u.getLoginName().equals(loginName)&&u.getLoginPassword().equals(loginPassword))
			{
				//验证成功，更新登录时间 
				u.setLastLoginTime(new Timestamp(System.currentTimeMillis()));
				userdao.update(u);
				id=u.getId();
				return id;
			
			}
		}
		return 0;
	}
	
	/*
	 * 验证注册
	 * 返回类型：id （0为注册失败，非0为注册成功并返回id）
	 * 
	 * */
	@Override
	public int registerValidate(String loginName, String loginPassword) {
		
		int id;
		if(checkLoginName(loginName))
		{
			User u=new User();
			u.setLoginName(loginName);
			u.setLoginPassword(loginPassword);
			u.setRegisterTime(new Timestamp(System.currentTimeMillis()));
			u.setLastLoginTime(new Timestamp(System.currentTimeMillis()));
			id=insert(u);
			return id;
		}
		else return 0;
	}

	@Override
	public List<Focus_contact> findMyFocus(int uid) {

		return fdao.findByUid(uid);
	}

//	@Override
//	public List<Collect_contact> findMyCollect(int uid) {
//		
//		return cdao.findByUid(uid);
//	}
	
	@Override
	public List<Love_contact> findMyLove(int uid) {
		
		return ldao.findByUid(uid);
	}
	
	@Override
	public boolean addFocus(int uid, int fuid) {
		
		if(uid!=fuid)
		{
			Focus_contact f=new Focus_contact(uid,fuid);
			f.setTime(new Timestamp(System.currentTimeMillis()));
			if(fdao.insert(f)==1)
			{ 
				userdao.fansNumAdd(fuid);
				userdao.focusNumAdd(uid);
				return true;
			}
			else return false;
			
		}
		return false;
	}
	@Override
	public boolean removeFocus(int uid,int fuid) {
		
		Focus_contact f=new Focus_contact(uid,fuid);
		if(fdao.deleteByObject(f)==1){
			userdao.fansNumReduce(fuid);
			userdao.focusNumReduce(uid);
			return true;
			}
		return false;
	}

	@Override
	public boolean addCollect(int uid, int blogid,int rewardNum) {
		
		Collect_contact c=new Collect_contact(uid,blogid);
		c.setTime(new Timestamp(System.currentTimeMillis()));
		c.setRewardNum(rewardNum);
		if(cdao.insert(c)==1) {
			bdao.collectNumAdd(blogid);
			return true;}
		else return false;
	}
//	@Override
//	public boolean removeCollect(int uid,int blogid) {
//		
//		Collect_contact c=new Collect_contact(uid,blogid);
//		if(cdao.deleteByObject(c)==1){ 
//			bdao.collectNumReduce(blogid);
//			return true;}
//		return false;
//	}
	
	@Override
	public boolean addSkr(int uid, int eid) {
		
		Skr_contact s=new Skr_contact(uid,eid);
		if(sdao.insert(s)==1) {
			edao.skrNumAdd(eid);
			return true;}
		else return false;
	
	}

	@Override
	public boolean removeSkr(int uid, int eid) {
		
		Skr_contact s=new Skr_contact(uid,eid);
		if(sdao.deleteByObject(s)==1) {
			edao.skrNumReduce(eid);
			return true;
			}
		else return false;
	}

	@Override
	public boolean addLove(int uid, int blogid) {
		
	   Love_contact l=new Love_contact(uid,blogid);
	   l.setTime(new Timestamp(System.currentTimeMillis()));
	   if(ldao.insert(l)==1) {
		   bdao.loveNumAdd(blogid);
		   return true;}
		else return false;
	}

	@Override
	public boolean removeLove(int uid, int blogid) {
		Love_contact l=new Love_contact(uid,blogid);
		if(ldao.deleteByObject(l)==1) {
			bdao.loveNumReduce(blogid);
			return true;}
		else return false;
	}


	@Override
	public int focusValidate(int uid, User fuser) {
		
		//比如进入自己的主页
		if(uid==fuser.getId()) return -1;
		else{
			Focus_contact f=new Focus_contact(uid,fuser.getId());
			//数据库有数据，已关注状态
			if(fdao.existValidate(f)==1) return 1;
			else return 0;
		}
	}

	@Override
	public int loveValidate(int uid, Blog blog) {
		//判断是否自己的文章
		if(uid==blog.getUser().getId()) return -1;
		else{
			Love_contact l=new Love_contact(uid,blog.getId());
			//数据库有数据，已喜欢状态
			if(ldao.existValidate(l)==1) return 1;
			else return 0;
		}
		
	}

//	@Override
//	public int collectValidate(int uid, Blog blog) {
//		//判断是否自己的文章
//		if(uid==blog.getUser().getId()) return -1;
//		else{
//			Collect_contact c=new Collect_contact(uid,blog.getId());
//			//数据库有数据，已喜欢状态
//			if(cdao.existValidate(c)==1) return 1;
//			else return 0;
//		}
//	}
	
	@Override
	public int skrValidate(int uid, Evaluate e) {
		
		Skr_contact s=new Skr_contact(uid,e.getId());
		if(sdao.existValidate(s)==1) return 1;
		return 0;
	}

	@Override
	public List<User> searchByNickname(String nickname) {
		// TODO Auto-generated method stub
		return userdao.searchByNickname(nickname);
	}

	@Override
	public boolean update(User u) {
		int a=userdao.update(u);
		if(a==1){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public List<User> findAllLimit(int start) {
		// TODO Auto-generated method stub
		return userdao.findAllLimit(start);
	}

	@Override
	public int findCountOfUser() {
		// TODO Auto-generated method stub
		return userdao.findCountOfUser();
	}

	@Override
	public User findByIdOnlyINI(int id) {
		// TODO Auto-generated method stub
		return userdao.findByIdOnlyINI(id);
	}

	@Override
	public List<User> recommedUser() {
		// TODO Auto-generated method stub
		return userdao.recommedUser();
	}

	
//	@Override
//	public int focusNumAdd(int uid) {
//		// TODO Auto-generated method stub
//		return userdao.focusNumAdd(uid);
//	}
//
//	@Override
//	public int focusNumReduce(int uid) {
//		// TODO Auto-generated method stub
//		return userdao.focusNumReduce(uid);
//	}
//
//	@Override
//	public int fansNumAdd(int uid) {
//		// TODO Auto-generated method stub
//		return userdao.fansNumAdd(uid);
//	}
//
//	@Override
//	public int fansNumReduce(int uid) {
//		// TODO Auto-generated method stub
//		return userdao.fansNumReduce(uid);
//	}

	
	
	
}
