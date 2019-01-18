package com.zhaohe.demo.util.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.zhaohe.demo.util.XStreamXmlFromToObjUtil;

public class XmlFromToObjUtilTest {
	TestCase testObj;
	TestCaseList2 testCaseList2;
	@Before
	public void setup(){
		testObj=new TestCase(1,"test",new Date());
		List<TestCaseList> list=new ArrayList<TestCaseList>();
		list.add(new TestCaseList("element1"));
		list.add(new TestCaseList("element2"));
		list.add(new TestCaseList("element3"));
		testObj.setTestList(list);
		testCaseList2=new TestCaseList2("testCaseList2");
	}
	@Test
	@Ignore
	public void testXmlFromToObjUtil(){
		String xml=XStreamXmlFromToObjUtil.parseObjToXml(testObj);
		//System.out.println(xml);
		Object obj=XStreamXmlFromToObjUtil.parseXmlToObj(xml);
		System.out.println(obj.toString());
		
		String xml2=XStreamXmlFromToObjUtil.parseObjToXml(testCaseList2);
		System.out.println(xml2);
		obj=XStreamXmlFromToObjUtil.parseXmlToObj(xml);
		System.out.println(obj.toString());
		obj=XStreamXmlFromToObjUtil.parseXmlToObj(xml2);
		System.out.println(obj.toString());
		
	}
	@Test
	public void testXmlFromToObjUtil2(){
		XStreamXmlFromToObjUtil.printObjToFile(testObj, "D:/test/xstream.xml");
		XStreamXmlFromToObjUtil.printObjToFile(testCaseList2, "D:/test/xstream.xml");
	}
}
@XStreamAlias("testcase")
class TestCase{
	int id;
	String name;
	Date date;
	//@XStreamImplicit
	public List<TestCaseList> testList=new ArrayList<TestCaseList>();
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
	public List<TestCaseList> getTestList() {
		return testList;
	}
	public void setTestList(List<TestCaseList> testList) {
		this.testList = testList;
	}
	
}
@XStreamAlias(value = "testcaselist")
class TestCaseList{
	String description;
	

	public TestCaseList(String description) {
		super();
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
@XStreamAlias(value = "testcaselist")
class TestCaseList2{
	String description;
	

	public TestCaseList2(String description) {
		super();
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
