package com.zhaohe.demo.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Text;
import org.dom4j.XPath;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class OrgDom4jXmlUtil {

	/*******************************************************************************
	 * 加载过程，返回root Element
	 *******************************************************************************/
	public static Element getRootElementByLoadInputStream(InputStream is) {
		// 。。。判断输入流是否为空
		SAXReader saxReader = new SAXReader();
		Document document = null;
		try {
			document = saxReader.read(is);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return document.getRootElement();
	}

	public static Element getRootElementByLoadFile(File xmlFile) {
		InputStream is = null;
		try {
			is = new FileInputStream(xmlFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return getRootElementByLoadInputStream(is);
	}
	/*******************************************************************************
	 * 输出过程
	 *******************************************************************************/
    public static void outputToConsole(Element root){
    	XMLWriter xmlWriter = new XMLWriter();
        try {
			xmlWriter.write(root.getDocument());
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
	public static void outputToXmlFile(Element root,File xmlOutFile, String encoding){
		FileOutputStream out = null;
		try {
			out = new FileOutputStream(xmlOutFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		OutputFormat format = new OutputFormat();
		format.setEncoding(encoding);
		format.setNewlines(false);
		format.setIndent(false);
		format.setSuppressDeclaration(false);
		XMLWriter writer = null;
		try {
			writer = new XMLWriter(out, format);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		writer.setEscapeText(false);
		try {
			writer.write(root.getDocument());
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("生成XML文件成功");
	}
	/*******************************************************************************
	 * 解析过程
	 *******************************************************************************/

	public static String formatXML(String inputXML, String encoding) {
		SAXReader reader = new SAXReader();
		Document document = null;
		String formatXml = null;
		XMLWriter writer = null;
		try {
			document = reader.read(new StringReader(inputXML));
			System.out.println("未格式化解析:"+document.getRootElement().asXML());
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		OutputFormat format = new OutputFormat("    ", true);
		format.setEncoding(encoding);
		format.setNewlines(true);
		format.setIndent(true);
		format.setSuppressDeclaration(true);
		StringWriter stringWriter = new StringWriter();
		writer = new XMLWriter(stringWriter, format);
		try {
			writer.write(document);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		formatXml=stringWriter.getBuffer().toString();
		try {
			stringWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("格式化解析:"+formatXml);
		System.out.println("迭代解析，不包括root，这里只迭代1次，完整迭代可以编写递归函数:");
        for (Iterator<?> iter = document.getRootElement().elementIterator(); iter.hasNext();)
        {
            Element e = (Element) iter.next();
            System.out.println("name属性值: "+e.attributeValue("name"));
            System.out.println(e.asXML());
        }
		return formatXml;
	}
	/*******************************************************************************
	 * update过程：dom4j中通过xpath处理不带命名空间和带命名空间的XML文件 
	 *******************************************************************************/
	
	private static Map<String, Object> getElementNamespace(Element element) {
		if (element.getNamespaceURI()!=null&&!element.getNamespaceURI().equals("")) {
			Map<String, Object> map = new HashMap<String, Object>();
			String uri=element.getNamespaceURI();
			map.put(uri.substring(uri.lastIndexOf("/")+1), uri);
			return map;
		}
		return null;
	}
	/*
	 * 获取文档的所有namespace
	 */
	public static Map<String, Object> getDocumentNamespaces(Element root){
		Map<String, Object> map = new HashMap<String, Object>();
		if(root.getDocument().getRootElement().equals(root)){
			Map<String, Object> rootMap=getElementNamespace(root);
			if(rootMap!=null){
				map.putAll(rootMap);
			}
		}
		for(Iterator<?> it=root.elements().iterator();it.hasNext();){
			Element element=(Element) it.next();
			Map<String, Object> temp=getElementNamespace(element);
			if(temp!=null){
				map.putAll(temp);
				getDocumentNamespaces(element);
			}
		}
		return map;
	}
	public static Element selectSingleNode(Element root,String xpathExpression){
		Map<String, Object> map=getDocumentNamespaces(root);
		if(map!=null){
			XPath xpath=root.getDocument().createXPath(xpathExpression);
			xpath.setNamespaceURIs(map);
			return (Element) xpath.selectSingleNode(root);
		}
		return (Element) root.selectSingleNode(xpathExpression);
	}
	public static Element addElementText(Element element, String text,boolean isLastIndex) {
		@SuppressWarnings("unchecked")
		List<Object> list = element.content();
		Text e = DocumentHelper.createText(text);
		if(isLastIndex){
			list.add(e);
		}else{
			list.add(0, e);
		}
		element.setContent(list);
		return element;
	}
	public static Element addElement(Element parent,String elementName,String text){
		parent.addElement(elementName).setText(text);
		return parent;
	}

}
