package com.zhaohe.demo.util;

import java.io.File;

import org.junit.Test;

public class IoFileDelTest 
{
	@Test
	public void testFileDel1(){
		IoFileUtil.deleteDir(new File("D:/test/util-reflect"));
	}
	@Test
	public void testFileDel2(){
		IoFileUtil.deleteFileInDir(new File("D:/test/util-file"));
	}
	
}
