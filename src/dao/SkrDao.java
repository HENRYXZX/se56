package dao;

import domain.Skr_contact;

public interface SkrDao {
	
	public int insert(Skr_contact skr);
	public int deleteByObject(Skr_contact skr);
	public int deleteByEvaluateid(int id);
	public int existValidate(Skr_contact skr);
}
