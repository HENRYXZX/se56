package service;

import java.util.List;

import domain.Blog;
import domain.Collect_contact;
import domain.Evaluate;
import domain.Focus_contact;
import domain.Love_contact;
import domain.User;



public interface UserService {
	
	
	public List<User> findAll();//查找所有
	public List<User> findAllLimit(int start);//找到所有用户，分页
	public List<User> searchByNickname(String nickname);//通过昵称进行搜索，
	public List<User> recommedUser();//获得推荐用户列表
	public int findCountOfUser();//获得用户数量
	public int insert(User u);//插入
	public boolean delete(int id);//删除
	public boolean update(User u);//更新
	public boolean updateUserInf(User u);//更新用户信息
	public User findById(int id);//获得某个用户全部信息
	public User findByIdOnlyINI(int id);//获得某一用户部分信息
	
	//登录验证
	public int loginValidate(String loginName,String loginPassword);
	//注册验证
	public int registerValidate(String loginName,String loginPassword);
	//验证账户名是否重复
	public boolean checkLoginName(String loginName);
	//验证昵称是否重复
	public boolean checkNickname(String nickname);
	public List<Focus_contact> findMyFocus(int uid); 
	public List<Love_contact> findMyLove(int uid);
	
	public boolean addFocus(int uid,int fuid);//关注用户
	public boolean removeFocus(int uid,int fuid);//取消关注
	
	public boolean addSkr(int uid,int eid);//点赞
	public boolean removeSkr(int uid,int eid);//取消点赞
	
	public boolean addLove(int uid,int blogid);//喜欢文章
	public boolean removeLove(int uid,int blogid);//取消喜欢
	
	public boolean addCollect(int uid,int blogid,int rewardNum);//打赏

	//验证是否可以关注
	public int focusValidate(int uid,User fuser);
	//验证是否可以添加喜欢
	public int loveValidate(int uid,Blog blog);

	//验证是否可以点赞  
	public int skrValidate(int uid,Evaluate eid);
	
	
	
}
