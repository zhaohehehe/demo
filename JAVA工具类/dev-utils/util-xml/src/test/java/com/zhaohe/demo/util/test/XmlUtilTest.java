package com.zhaohe.demo.util.test;

import java.io.File;

import org.dom4j.Element;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.zhaohe.demo.util.JavaxXmlUtil;
import com.zhaohe.demo.util.OrgDom4jXmlUtil;

public class XmlUtilTest {
	String xml=null;
	File file=null;
	@Before
	public void setup(){
		xml="<root><brother name=\"Bob\">brother1</brother><child>child1</child><child><son>son1</son><son>son2</son></child></root>";
		file=new File("D:/zhaohe/workspace/workspace_JavaW/zhaohe/dev-utils/util-xml/src/test/java/com/zhaohe/demo/util/test/pom.xml");
	}
	@Test
	@Ignore
	public void testDom4jXml1(){
		OrgDom4jXmlUtil.formatXML(xml, "utf-8");
		
	}
	@Test
	@Ignore
	public void testDom4jXml2(){
		String output="D:/zhaohe/workspace/workspace_JavaW/zhaohe/dev-utils/util-xml/src/test/java/com/zhaohe/demo/util/test/output.xml";
		Element root=OrgDom4jXmlUtil.getRootElementByLoadFile(file);
		OrgDom4jXmlUtil.outputToXmlFile(root, new File(output), "utf-8");
		OrgDom4jXmlUtil.outputToConsole(root);
	}
	@Test
	@Ignore
	public void testDom4jXml3(){
		Element root=OrgDom4jXmlUtil.getRootElementByLoadFile(file);
		System.out.println(OrgDom4jXmlUtil.getDocumentNamespaces(root).toString());
	}
	@Test
	@Ignore
	public void testDom4jXml4(){
		Element root=OrgDom4jXmlUtil.getRootElementByLoadFile(file);
		System.out.println(OrgDom4jXmlUtil.selectSingleNode(root, "//beans:import").asXML());
		System.out.println(OrgDom4jXmlUtil.selectSingleNode(root, "//jaxws:endpoint").asXML());
	}
	@Test
	@Ignore
	public void testDom4jXml5(){
		Element root=OrgDom4jXmlUtil.getRootElementByLoadFile(new File("D:/test/pom.xml"));
		Element element=OrgDom4jXmlUtil.selectSingleNode(root, "//brother");
		//<brother name="blob">brother1</brother>
		OrgDom4jXmlUtil.addElement(element, "person", "person1");
		//<brother name="blob">brother1<person>person1</person></brother>
		System.out.println(element.asXML());
		OrgDom4jXmlUtil.addElementText(element, "addTxext",false);
		//<brother name="blob">addTxextbrother1<person>person1</person></brother>
		System.out.println(element.asXML());
	}
	@Test
	//@Ignore
	public void testJavaxXmlUtil(){
		//JavaxXmlUtil.getRootElementByLoadFile(file);
		System.out.println(JavaxXmlUtil.formatXML(xml));
	}
}
