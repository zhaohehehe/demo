package com.zhaohe.demo.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class GetMFFileUtil {
	
	public static Map<String, String> getManifestAttrMap() {
		String metaInfPath = GetMFFileUtil.class.getResource("/").getPath().split("WEB-INF")[0];
		System.out.println("MANIFEST Path: " + metaInfPath + "META-INF/MANIFEST.MF");
		return GetMFFileUtil.getManifestAttrs(metaInfPath + "META-INF/MANIFEST.MF");
	}

	public static Map<String, String> getManifestAttrs(String manifestPath) {
		InputStream is = null;
		Reader reader = null;
		BufferedReader br = null;
		Map<String, String> map = null;
		try {
			is = new FileInputStream(new File(manifestPath));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		if (is != null) {
			map = new HashMap<String, String>();
			try {
				reader = new InputStreamReader(is, "utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			br = new BufferedReader(reader);
			String line = null;
			try {
				while ((line = br.readLine()) != null) {
					int splitIndex = line.indexOf(":");
					if (splitIndex > line.length() - 1 || line.equals("")) {
						continue;
					}
					if (splitIndex < 0) {
						System.out.println(line + "格式无效！");
					} else if (splitIndex == 0) {
						System.out.println(line + "格式无效！");
					} else if (splitIndex == line.length() - 1) {
						map.put(line.substring(0, splitIndex).trim(), "");
					} else {
						map.put(line.substring(0, splitIndex).trim(), line.substring(splitIndex + 1).trim());
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				CloseUtil.close(is);
				CloseUtil.close(reader);
				CloseUtil.close(br);
			}
		}
		return map;
	}
}
