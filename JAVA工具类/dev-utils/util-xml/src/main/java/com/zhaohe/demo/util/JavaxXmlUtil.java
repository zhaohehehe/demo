package com.zhaohe.demo.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.stream.StreamResult;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.DOMReader;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class JavaxXmlUtil {
	public static void getRootElementByLoadFile(File xmlFile) {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = null;
		try {
			db = dbf.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		org.w3c.dom.Document document = null;
		try {
			document = db.parse(xmlFile);
		} catch (SAXException | IOException e) {
			e.printStackTrace();
		}
		DOMReader domReader = new DOMReader();
		Document dom4jDocument = domReader.read(document);
		Element rootElement = dom4jDocument.getRootElement();
		System.out.println("DOMReader方式解析: \n" + rootElement.asXML());

	}

	public static String formatXML(String inputXml) {
		try {
			Transformer seralizer = SAXTransformerFactory.newInstance().newTransformer();
			// 是否输出 whitespace
			seralizer.setOutputProperty(OutputKeys.INDENT, "yes");
			seralizer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
			Source xmlSource = new SAXSource(new InputSource(new ByteArrayInputStream(inputXml.getBytes())));
			StreamResult sres = new StreamResult(new ByteArrayOutputStream());
			seralizer.transform(xmlSource, sres);
			return new String(((ByteArrayOutputStream) sres.getOutputStream()).toByteArray());
		} catch (Exception e) {
			return inputXml;
		}
	}
}
