package dao;

import java.sql.Timestamp;
import java.util.List;
import domain.Evaluate;
public interface EvaluateDao {
	
	public List<Evaluate> findByBlodid(int blogid);
	public List<Evaluate> findByUid(int uid);
	public Evaluate findById(int evaluateid);
	public int insert(Evaluate evaluate); //返回插入记录主键
	public int delete(int id);
	public int deleteByBlogid(int blogid);
	public int skrNumAdd(int evaluateid);
	public int skrNumReduce(int evaluateid);
}
