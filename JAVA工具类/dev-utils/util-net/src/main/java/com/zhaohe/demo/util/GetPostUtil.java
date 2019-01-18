package com.zhaohe.demo.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

public class GetPostUtil {
	public static void main(String[] args) throws IOException {
		//String s=GetPostUtil.sendGet("http://www.baidu.com/img/bd_logo1.png", "where=super");
		//System.out.println(s);
		String sl=GetPostUtil.sendPost("http://133.160.94.97:8080/bdam/bdamOperation/getCurrentOperationErrors", "{\"region\":\"1\"}");
		System.out.println(sl);
	}
	public static String sendPost(String url, String param) {
		String result = "";
		try {
			URL realUrl = new URL(url);
			URLConnection conn = realUrl.openConnection();
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("Content-Type", "application/json; charset=utf-8");  
			conn.setRequestProperty("user-agent", "Mozilla/4.0(compatible; MSIE 6.0;Windows NT 5.1; SV1)");
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			try (PrintWriter out = new PrintWriter(conn.getOutputStream())) {
				out.print(param);
				out.flush();// 输出流的缓冲
			}
			try (BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"))) {
				String line;
				while ((line = in.readLine()) != null) {
					result += "\n" + line;
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public static String sendGet(String url, String... param) throws IOException {
		String result = "";
		String urlName = url;
		for (int i = 0; i < param.length; i++) {
			urlName += ("?" + param);
		}
		URL realUrl = new URL(urlName);
		URLConnection conn = realUrl.openConnection();
		conn.setRequestProperty("accept", "*/*");
		conn.setRequestProperty("Content-Type", "application/json; charset=utf-8");  
		conn.setRequestProperty("connection", "Keep-Alive");
		conn.setRequestProperty("user-agent", "Mozilla/4.0(compatible; MSIE 6.0;Windows NT 5.1; SV1)");
		// 建立实际的链接
		conn.connect();
		// 获取所有的响应头字段
		Map<String, List<String>> map = conn.getHeaderFields();
		for (String key : map.keySet()) {
			System.out.println(key + "---->" + map.get(key));
		}
		try (BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"))) {
			String line;
			while ((line = in.readLine()) != null) {
				result += "\n" + line;
			}
		}
		return result;
	}
}
