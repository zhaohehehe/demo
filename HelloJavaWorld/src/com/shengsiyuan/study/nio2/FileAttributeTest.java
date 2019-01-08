package com.shengsiyuan.study.nio2;

import java.nio.file.attribute.AclFileAttributeView;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.DosFileAttributeView;
import java.nio.file.attribute.FileOwnerAttributeView;
import java.nio.file.attribute.PosixFileAttributeView;

public class FileAttributeTest {
	public static void main(String[] args) {
		//访问文件属性
		AclFileAttributeView a;
		BasicFileAttributeView b;
		DosFileAttributeView d;
		FileOwnerAttributeView f;
		PosixFileAttributeView p;
	}
}
