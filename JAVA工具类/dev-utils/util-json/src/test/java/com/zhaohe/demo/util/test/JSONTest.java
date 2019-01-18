package com.zhaohe.demo.util.test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.google.gson.JsonObject;
import com.zhaohe.demo.util.GoogleJsonUtil;
import com.zhaohe.demo.util.OrgJsonUtil;

public class JSONTest {
	TestCase testObj;
	@Before
	public void setup(){
		testObj=new TestCase(1,"test",new Date());
	}
	//@Test
	@Ignore
	public void testGoogleJson(){
		JsonObject jsonObj=GoogleJsonUtil.objToJsonObj(testObj);
		System.out.println("Object: "+testObj+"转换为JsonObject: "+jsonObj);
		
		String jsonStr=GoogleJsonUtil.objToJsonString(testObj);
		System.out.println("Object.toString(): "+testObj.toString());
		System.out.println("Object: "+testObj+"转换为Json类型的String: "+jsonStr);
		
		JsonObject jsonObj2=GoogleJsonUtil.jsonStringToJsonObj(jsonStr);
		System.out.println("Json类型的String: "+jsonStr+"转换为JsonObject: "+jsonObj2);
		System.out.println("JsonObject.toString(): "+jsonObj.toString());
		
		Object obj=GoogleJsonUtil.jsonStringToObj(jsonStr, TestCase.class);
		if(obj instanceof TestCase){
			System.out.println("Json类型的String: "+jsonStr+"转换为Object: "+TestCase.class.getName());
			System.out.println(((TestCase) obj).getDate());
			System.out.println(((TestCase) obj).getName());
		}
		Map<String,Object> map=new HashMap<>();
		map.put("address", "beijing");
		map.put("country", "china");
		
		System.out.println(GoogleJsonUtil.jsonStringToJsonObj("{'id':2,'name':'test'}"));
		
		System.out.println(GoogleJsonUtil.jsonStringToJsonObj("{'123':'456'}"));
		
		System.out.println(GoogleJsonUtil.extendJson(jsonStr, map));
	}
	@Test
	public void testOrgJson(){
		String json="{'id':2,'name':'test'}";
		Map<String,Object> map=new HashMap<>();
		map.put("address", "beijing");
		map.put("country", "china");
		System.out.println(OrgJsonUtil.extendJson(json, map));
	}
}


class TestCase{
	int id;
	String name;
	Date date;
	
	public TestCase(int id, String name, Date date) {
		super();
		this.id = id;
		this.name = name;
		this.date = date;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	
}
