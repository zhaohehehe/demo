package com.zhaohe.demo.util2;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class IgnoreDtdValidateEntityResolver implements EntityResolver {

	@Override
	public InputSource resolveEntity(String arg0, String arg1) throws SAXException, IOException {
		return new InputSource(new ByteArrayInputStream("<?xml version='1.0' encoding='UTF-8'?>".getBytes()));
	}

	public IgnoreDtdValidateEntityResolver() {
	}

	public static void main(String[] args) {
		File file = new File("src/main/java/com/zhaohe/demo/util2/test.html");
		SAXReader reader = new SAXReader();
		// 不加忽略校验会报错
		reader.setEntityResolver(new IgnoreDtdValidateEntityResolver());
		Document document = null;
		if (file.exists()) {
			try {
				document = reader.read(file);
				System.out.println(document.asXML());
			} catch (DocumentException e) {
				e.printStackTrace();
			}
		}
	}

}
