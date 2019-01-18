package com.zhaohe.demo.util.privatesocket;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
/**
 * value不允许重复，保证从可以从用户名找到对应的输出流，也可以根据输出流找到对应的用户名
 * @author zhaohe
 *
 * @param <K>
 * @param <V>
 */
public class CrazyitMap<K,V> {
	public Map<K,V> map=Collections.synchronizedMap(new HashMap<>());
	//根据value删除制定项
	public synchronized void removeByValue(Object value){
		for(Object key:map.keySet()){
			if(map.get(key)==value){
				map.remove(key);
				break;
			}
		}
	}
	//获取所有value组成的Set集合
	public synchronized Set<V> valueSet(){
		Set<V> result=new HashSet<V>();
		map.forEach((key,value)->{result.add(value);});
		return result;
	}
	//根据value查找key
	public synchronized K getKeyByValue(V val){
		for(K key :map.keySet()){
			if(map.get(key)==val||map.get(key).equals(val)){
				return key;
			}
		}
		return null;
	}
	//put，不允许value重复
	public synchronized V put(K key,V value){
		for(V val:valueSet()){
			if(val.equals(value)&&val.hashCode()==value.hashCode()){
				throw new RuntimeException("MyMap实例中不允许有重复的value");
			}
		}
		return map.put(key, value);
	}

}
















