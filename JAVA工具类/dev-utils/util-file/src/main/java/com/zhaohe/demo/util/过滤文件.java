package com.zhaohe.demo.util;

import java.io.File;
import java.io.FilenameFilter;

public class 过滤文件 {
	public static void main(String[] args) {
		File file = new File("d:/test");
		String[] listFilter=file.list(new FilenameFilter() {
			/**
			 * 参数为：待测试的文件目录以及文件名
			 * 值为true的name会被返回
			 */
			@Override
			public boolean accept(File dir, String name) {
				if(name.endsWith("txt"))
					return true;
				return false;
			}
		});
		for(String str:listFilter){
			System.out.println(str);
		}
	}
}
