package com.shengsiyuan.study.nio2;

import java.nio.file.Path;
import java.nio.file.Paths;

public class PathsTest {
	public static void main(String[] args) {
		Path path=Paths.get(".");
		System.out.println(path.getNameCount());
		System.out.println(path.getRoot());
		System.out.println(path.getParent());
		Path absolutePath=path.toAbsolutePath();
		System.out.println(absolutePath.getRoot());
		System.out.println(absolutePath.getParent());
		System.out.println(absolutePath.getNameCount());
		System.out.println(absolutePath.getName(3));
		Path path2=Paths.get("d","zhoahe","test");
		System.out.println(path2);
	}
}















