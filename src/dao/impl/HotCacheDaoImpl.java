package dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import dao.HotCacheDao;
import domain.HotCache;

public class HotCacheDaoImpl implements HotCacheDao {

	@Autowired
	private HotCacheDao hdao;
	@Override
	public int insert(HotCache h) {
		// TODO Auto-generated method stub
		return hdao.insert(h);
	}

	@Override
	public int deleteByType(String type) {
		// TODO Auto-generated method stub
		return hdao.deleteByType(type);
	}

	@Override
	public List<HotCache> findByType(String type) {
		// TODO Auto-generated method stub
		return hdao.findByType(type);
	}

	@Override
	public int deleteByBlogid(int blogid) {
		// TODO Auto-generated method stub
		return hdao.deleteByBlogid(blogid);
	}

}
