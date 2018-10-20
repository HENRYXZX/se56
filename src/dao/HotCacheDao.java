package dao;

import java.util.List;

import domain.HotCache;

public interface HotCacheDao {
		
	 public int insert(HotCache h);
	 public int deleteByType(String type);
	 public List<HotCache> findByType(String type);
	 public int deleteByBlogid(int blogid);
}
