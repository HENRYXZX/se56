package controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import dao.HotCacheDao;
import domain.Blog;
import domain.HotCache;
import domain.User;
import service.BlogService;
import service.EvaluateService;
import service.TopicService;
import service.UserService;

@Controller
public class TimeTaskController {
		
	
//	@org.springframework.scheduling.annotation.Scheduled(cron="0/5 * * * * ?")
//	public void Scheduled() throws Exception{
//	System.out.println("定时器  running~");
//
//	}
	
	@Autowired
	private UserService us;
	@Autowired
	private BlogService bs;
	
	@Autowired
	private TopicService ts;
	@Autowired
	private EvaluateService es;

	//0/5 * * * * ?
	//推荐用户
	@org.springframework.scheduling.annotation.Scheduled(cron="0 0 1 * * ?")//cron="0 0 1 * * ?"
	public void WeekHot() throws Exception{
		System.out.println("开始进行7天热门的定时任务啦啦啦~");
		
		List<Integer> blist=bs.findHotW();
		bs.deleteByHotType("W");
		HotCache h=new HotCache();
		for(int k:blist)
		{
			h.setBlogid(k);
			h.setType("W");
			bs.insertHotCache(h);
			System.out.println("W"+k);
		}

	}
	
	@org.springframework.scheduling.annotation.Scheduled(cron="0 0 0/4 * * ?")//cron="0 0 0/4 * * ?"
	public void DayHot() throws Exception{
		System.out.println("开始进行当天热门的定时任务啦啦啦~");
		
		List<Integer> blist=bs.findHotD();
		bs.deleteByHotType("D");
		HotCache h=new HotCache();
		for(int k:blist)
		{
			h.setBlogid(k);
			h.setType("D");
			bs.insertHotCache(h);
			System.out.println("D"+k);
		}

	}
	@org.springframework.scheduling.annotation.Scheduled(cron="0 0 1 * * ?")//cron="0 0 1 * * ?"
	public void MonthHot() throws Exception{
		System.out.println("开始进行当月热门的定时任务啦啦啦~");
		
		List<Integer> blist=bs.findHotM();
		bs.deleteByHotType("M");
		HotCache h=new HotCache();
		for(int k:blist)
		{
			h.setBlogid(k);
			h.setType("M");
			bs.insertHotCache(h);
			System.out.println("M"+k);
		}

	}
	
	
}
