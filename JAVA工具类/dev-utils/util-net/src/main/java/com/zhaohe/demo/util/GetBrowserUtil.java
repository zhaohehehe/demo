package com.zhaohe.demo.util;

import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;

public class GetBrowserUtil {
	public static String getClientIp() {
		String tempIp = null;
		try {
			tempIp = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return tempIp;
	}

	public static String getMACAddr() throws SocketException, UnknownHostException, UnsupportedEncodingException {
		byte[] mac = NetworkInterface.getByInetAddress(InetAddress.getLocalHost()).getHardwareAddress();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < mac.length; i++) {
			String str = Integer.toHexString(mac[i] & 0xff);
			sb.append(str).append("-");
		}
		sb.deleteCharAt(sb.length() - 1);
		return sb.toString();
	}

	public static String getBrowserType(String userAgent) {
		String browser = null;
		String agent = userAgent.toLowerCase();
		if (agent.contains("firefox")) {
			browser = "Firefox";
		} else if (agent.contains("opera") || agent.contains("opr")) {
			browser = "Opera";
		} else if (agent.contains("chrome")) {
			browser = "Chrome";
		} else if (agent.contains("safari") && agent.contains("version")) {
			browser = "Safari";
		} else if (agent.contains("edge")) {
			browser = "Edge";
		} else if (agent.contains("msie")) {
			// Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 6.2; Win64; x64;
			// Trident/6.0)
			String target = agent.split("msie")[1];
			if (target != null && !target.equals("")) {
				String version = target.substring(0, target.indexOf(";")).trim();
				browser = "IE " + version;
			} else {
				browser = "IE";
			}
		} else if (agent.contains("rv:")) {
			// Mozilla/5.0 (Windows NT 10.0; WOW64; Trident/7.0; rv:11.0) like
			// Gecko
			String target = agent.split("rv:")[1];
			if (target != null && !target.equals("")) {
				String version = target.substring(0, target.indexOf(")")).trim();
				browser = "IE " + version;
			} else {
				browser = "IE";
			}
		} else {
			browser = "UnKnown, More-Info: " + userAgent;
		}
		return browser;
	}

	public static void main(String[] args) {
		System.out.println(
				GetBrowserUtil.getBrowserType("Mozilla/5.0 (Windows NT 10.0; WOW64; Trident/7.0; rv:11.0) like"));
		try {
			System.out.println(GetBrowserUtil.getMACAddr());
		} catch (SocketException | UnknownHostException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
