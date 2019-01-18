package com.zhaohe.demo.util;

import java.io.UnsupportedEncodingException;
import java.lang.management.ManagementFactory;
import java.util.List;
import java.util.Map;

public class GetPathUtil {
	public static void main(String[] args) {
		GetPathUtil.getJarFilePathByClass(TestClass.class);
		List<String> list = ManagementFactory.getRuntimeMXBean().getInputArguments();
		System.out.println(list);
		// String path = System.getProperty("user.dir");
		String path = System.getenv("user.dir");
		System.out.println(path);
	}

	public static String getJarFilePathByClass(Class<?> clz) {
		String jarFilePath = clz.getProtectionDomain().getCodeSource().getLocation().getFile();
		try {
			jarFilePath = java.net.URLDecoder.decode(jarFilePath, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		return jarFilePath;
	}

	public static Map<String, String> getSystemProperties() {
		return ManagementFactory.getRuntimeMXBean().getSystemProperties();
	}

	public static List<String> getInputArguments() {
		return ManagementFactory.getRuntimeMXBean().getInputArguments();
	}

	public static String getSystemProperty(String key) {
		return System.getProperty(key);// "user.dir"
	}

}
