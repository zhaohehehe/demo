package zhaohe.study.listener;

import java.io.UnsupportedEncodingException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MyRootServletContextListener implements ServletContextListener {
	private String moduleName;
	/*
	 * 按照web-fragment加载顺序获取应用依赖的所有Jar包路径; 这些JAR包里面都含有web-fragment.xml文件;
	 */
	private static Map<String, String> jars = null;

	public Map<String, String> getJars() {
		return jars;
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		if (jars == null) {
			jars = new LinkedHashMap<String, String>();
		}
		String jarFilePath = this.getClass().getProtectionDomain().getCodeSource().getLocation().getFile();
		try {
			jarFilePath = java.net.URLDecoder.decode(jarFilePath, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		if (jarFilePath != null && !jarFilePath.equals("") && jarFilePath.endsWith(".jar")) {
			jars.put(this.getModuleName(), jarFilePath);
		}
		sce.getServletContext().setAttribute("jars", jars);
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
}
