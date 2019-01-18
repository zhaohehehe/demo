package com.zhaohe.demo.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

public class XStreamXmlFromToObjUtil {
	static XStream xstream;
	static{
		xstream = new XStream(new StaxDriver());
		// 通知XStream框架来处理注释
		xstream.autodetectAnnotations(true);
	}
	/**************************************************************************************
	 * 解析转换过程
	 **************************************************************************************/
	/**
	 * 序列化对象到到文件中
	 * @param targetFile
	 * @param obj
	 */
	public static void printObjToFile(Object obj, String targetFile) {
		try {
			ObjectOutputStream objectOutputStream = xstream.createObjectOutputStream(new FileOutputStream(targetFile));
			objectOutputStream.writeObject(obj);
			objectOutputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**************************************************************************************
	 * 解析转换过程
	 **************************************************************************************/
	/**
	 * 序列化对象到XML,使用toXML() 方法来获取对象的XML字符串表示
	 * 
	 * @param obj
	 */
	public static String parseObjToXml(Object obj) {
		// 将对象转换为xml序列化流，并进行格式转换
		String xml = xstream.toXML(obj);
		return JavaxXmlUtil.formatXML(xml);
	}

	/**
	 * 反序列化XML获得对象,使用 fromXML()方法来从XML获取对象
	 * 
	 * @param xml
	 */
	public static Object parseXmlToObj(String xml) {
		Object obj = xstream.fromXML(xml);
		return obj;
	}
}
