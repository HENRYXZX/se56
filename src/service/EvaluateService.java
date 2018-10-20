package service;

import java.util.List;

import domain.Evaluate;

public interface EvaluateService {
	
	public int addEvaluate(Evaluate e);  //添加评论
	public boolean removeEvaluate(int id);//删除评论
	public List<Evaluate> findByUid(int uid); //显示我的评论
	public List<Evaluate> findByBlodid(int blogid); //显示文章下评论
	public int skrNumAdd(int eid);//点赞书自增
	public int skrNumReduce(int eid);//点赞书减少
	public boolean removeByBlodid(int blogid);//删除某一文章下所有评论
	public Evaluate findById(int evaluateid);//找到某条评论
	
}
