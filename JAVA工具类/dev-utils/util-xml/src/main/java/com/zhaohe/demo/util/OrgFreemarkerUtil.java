package com.zhaohe.demo.util;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.Version;

/**
 * Freemarker 模板工具
 * 
 * @author zhaohe
 *
 */
public class OrgFreemarkerUtil {
	private static OrgFreemarkerUtil instance;
	private static Configuration cfg = new Configuration(new Version("2.3.22"));

	private OrgFreemarkerUtil() {
		//cfg.setDefaultEncoding("GBK");
		cfg.setEncoding(Locale.CHINA, "UTF-8");
		cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
	}

	public static OrgFreemarkerUtil getInstance() {
		if (instance == null) {
			instance = new OrgFreemarkerUtil();
		}
		return instance;
	}

	/**
	 * 获取模板
	 * 
	 * @param tempName
	 *            模板文件的名字，如tempName.ftl文件
	 * @param tempDir
	 *            模板所在的目录
	 * @return Template
	 */
	public Template getTemplates(String tempName, String tempDir) {
		try {
			//从文件系统加载模板
			cfg.setDirectoryForTemplateLoading(new File(tempDir));
			// 通过Classloader加载模板
			//cfg.setClassForTemplateLoading(this.getClass(), tempPackagePath);
			// 根据模板名称获取模板文件
			Template template = cfg.getTemplate(tempName);
			return template;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取模板内容并输出到目标文件
	 * 
	 * @param tempName
	 *            模板文件名
	 * @param tempDir
	 *            模板所在的目录
	 * @param rootMap
	 *            数据模型Map
	 * @param desF_FullPath
	 *            输出文件路径
	 * @throws TemplateException
	 */
	public void printFtlToFile(String tempName, String tempDir, Map<String, Object> rootMap, String desF_FullPath
			) throws TemplateException, IOException {
		FileWriter out = null;
		File file = null;
		// System.out.println(desF_FullPath);
		// System.out.println("rootMap size:" + rootMap.size());
		// Set<Entry<String, Object>> entrySet = rootMap.entrySet();
		// for (Entry<String, Object> entry : entrySet) {
		// System.out.println(entry.getKey() + "&" +
		// entry.getValue().toString());
		// }
		try {
			file = new File(desF_FullPath);
			if(!file.exists()){
				file.createNewFile();
			}
			out = new FileWriter(file);
			Template template = this.getTemplates(tempName, tempDir);
			template.process(rootMap, out);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != out)
					out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 将模板内容输出到目标文件
	 * 
	 * @param rootMap
	 *            数据模型Map
	 * @param desF_FullPath
	 *            目标文件路径
	 * @param template
	 *            ftl模板
	 * @throws TemplateException
	 */
	public void printFtlToFile(Map<String, Object> rootMap, String desF_FullPath, Template template)
			throws TemplateException, IOException {
		FileWriter out = null;
		File file = null;
		try {
			file=new File(desF_FullPath);
			System.out.println(desF_FullPath);
			if(!file.exists()){
				file.createNewFile();
			}
			out = new FileWriter(file);
			template.process(rootMap, out);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != out)
					out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 添加xml文本到rootMap,用于模板填充
	 * @param key
	 * @param value
	 * @return Map
	 */
	public Map<String, Object> parseXmlToRootMap(String key, String value) {
		Map<String, Object> rootMap = new HashMap<String, Object>();
		InputStream is = new ByteArrayInputStream(value.getBytes());
		InputSource ins = new InputSource(is);
		try {
			rootMap.put(key, freemarker.ext.dom.NodeModel.parse(ins));
			//或者rootMap.put(key, freemarker.ext.dom.NodeModel.parse(new File("")));
		} catch (SAXException | IOException | ParserConfigurationException e) {
			e.printStackTrace();
		}
		return rootMap;
	}
}
