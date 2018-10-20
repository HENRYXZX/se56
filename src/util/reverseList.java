package util;

import java.util.ArrayList;
import java.util.List;

import domain.Blog;

public class reverseList  {
	
	
	
	public static  List<Blog> toReverse(List<Blog> list)
	{
		List<Blog> back=new ArrayList<Blog>();
		for(int i=list.size()-1;i>=0;i--) 
		{
			back.add(list.get(i));
		}
	
		return back;
	}
}
