package ∑∫–Õ.∑∫–Õ¡∑œ∞;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class GenericsTest04<T> 
{
	
	public static void main(String[] args) {

		Map<String,String> map=new HashMap<String, String>();
		map.put("zhaohe", "jilin");
		map.put("zhangsan", "beijing");
		map.put("wangwu", "hunan");
		//Set<Entry<String, String>> set=map.entrySet();//yes
		Set<Map.Entry<String, String>> set=map.entrySet();
		for(Iterator<Entry<String, String>> it=set.iterator();it.hasNext();){
			Map.Entry<String, String> entry=it.next();
			String key=entry.getKey();
			String value=entry.getValue();
			System.out.println(key+"="+value);
		}
	}
	
}
