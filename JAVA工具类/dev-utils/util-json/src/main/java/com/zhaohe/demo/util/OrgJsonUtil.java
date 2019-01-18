package com.zhaohe.demo.util;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.json.JSONObject;


public class OrgJsonUtil {
	
	/******************************************************************
	 * 使用Map扩展json字符串
	 * JSONObject.keys() : 遍历key
	 * JSONObject.putOnce(String key, Object value) : key不能重复
	 * JSONObject.putOpt(String key, Object value) : key可以重复，但是值会覆盖
	 * JSONObject.accumulate(String key, Object value) : key可以重复，如果value中的元素有1个，则该value类型为参数类型，如果value的元素有>1个，则该value类型为数组
	 * JSONObject.append(String key, Object value) : key可以重复，value此时类型为数组（无论value中的元素有多少个）
	 ******************************************************************
	 */
	
	public static String extendJson(String json,Map<String, Object> map){
		JSONObject jsonObj=new JSONObject(json);
		Set<Entry<String,Object>> set = map.entrySet();
		for(Entry<String,Object> entry:set){
			jsonObj.putOnce((String) entry.getKey(), entry.getValue().toString());
		}
		return jsonObj.toString();
	}
}
                                                                              

