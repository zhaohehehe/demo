package com.shengsiyuan.study.nio2;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class FilesTest {
	public static void main(String[] args) throws FileNotFoundException, IOException {
		Files.copy(Paths.get(""), new FileOutputStream("a.txt"));
		//获取所有行
		List<String> lines=Files.readAllLines(Paths.get(""),Charset.forName("gbk"));
		Files.write(Paths.get(""), lines, Charset.forName("gbk"));
		//jdk8 Files 略
	}
}
























