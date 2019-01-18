package com.zhaohe.demo.util.test;

import java.io.IOException;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

import com.zhaohe.demo.util.OrgFreemarkerUtil;

import freemarker.template.Template;
import freemarker.template.TemplateException;

public class FreemarkerUtilTest {
	String path;

	@Before
	public void setup() {
		path = "D:/zhaohe/workspace/workspace_JavaW/zhaohe/dev-utils/util-file/src/test/java/com/zhaohe/demo/util/test";
	}
	@Test
	public void testFreemarkerUtil() {
		HashMap<String, Object> root = new HashMap<String, Object>();
		root.put("str", "String");
		OrgFreemarkerUtil freemarkerUtil = OrgFreemarkerUtil.getInstance();
		root.putAll(freemarkerUtil.parseXmlToRootMap("xml", "<parent><son>son1</son><son>son2</son></parent>"));
		Template temp = freemarkerUtil.getTemplates("freemarker.ftl", path);
		try {
			freemarkerUtil.printFtlToFile(root, path + "/freemarker.xml", temp);
		} catch (TemplateException | IOException e) {
			e.printStackTrace();
		}
	}
}
