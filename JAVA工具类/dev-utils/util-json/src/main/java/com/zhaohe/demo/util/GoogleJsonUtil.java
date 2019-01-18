package com.zhaohe.demo.util;

import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class GoogleJsonUtil {
	private static Gson gson = new Gson();
	private static JsonParser parser = new JsonParser();
	/******************************************************************
	 * Object-->JsonObject
	 * 1.Object-->String
	 * 2.String-->JsonObject
	 ******************************************************************
	 */
	public static String objToJsonString(Object obj){
		return gson.toJson(obj);
	}
	public static JsonObject jsonStringToJsonObj(String str){
		return parser.parse(str).getAsJsonObject();
	}
	public static JsonObject objToJsonObj(Object obj){
		return jsonStringToJsonObj(objToJsonString(obj));
	}
	/******************************************************************
	 * JsonObject-->Object
	 * 1.JsonObject-->String()(不需要)
	 * 2.String-->Object
	 ******************************************************************
	 */
	public static <T> T jsonStringToObj(String str,Class<T> clazz){
		if(str!=null&&!str.equals("")){
			return gson.fromJson(str, clazz);
		}
		return null;
	}
	/******************************************************************
	 * 使用Map扩展json字符串
	 ******************************************************************
	 */
	
	public static String extendJson(String json,Map<String,Object> map){
		JsonObject obj = jsonStringToJsonObj(json);
		Set<Entry<String,Object>> set = map.entrySet();
		for(Entry<String,Object> entry:set){
			obj.addProperty(entry.getKey(), entry.getValue().toString());
		}
		return obj.toString();
	}
}
