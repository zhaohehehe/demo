package com.zhaohe.demo.util;

import java.io.File;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

public class IoFileCopyTest 
{
	private String sourceFile;
	private String targetFile;
	@Before
	public void setup(){
		sourceFile="D:/zhaohe/workspace/workspace_JavaW/zhaohe/dev-utils/util-file/src/test/java/source";
		targetFile="D:/zhaohe/workspace/workspace_JavaW/zhaohe/dev-utils/util-file/src/test/java/target";
	}
	@Test
	public void test1(){
		boolean a=new File("D:/test/util-xm").delete();
		System.out.println(a);
	}
	@Test
	public void testFileCopy1(){
		try {
			IoFileUtil.copyFile(sourceFile+"/source.txt", targetFile+"/target.txt", true);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	public void testFileCopy2(){
		try {
			IoFileUtil.copyFile(sourceFile+"/source.txt", targetFile+"/source.txt", false);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	public void testFileCopy3(){
		try {
			IoFileUtil.copyFile(sourceFile+"/source.txt", sourceFile+"/source.txt", false);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	public void testCopyFileToDir1(){
		try {
			IoFileUtil.copyFileToDir(new File(sourceFile+"/source.txt"), targetFile, false,true);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	public void testCopyFileToDir2(){
		try {
			IoFileUtil.copyFileToDir(new File(sourceFile+"/source.txt"), targetFile+"/teswt", true,false);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	public void testCopyFileToDir3(){
		try {
			IoFileUtil.copyFileToDir(new File(targetFile+"/source.txt"), targetFile, true,true);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	public void testCopyDir1(){
		try {
			IoFileUtil.copyDir(sourceFile, targetFile+"/test", true, false);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	public void testCopyDir2(){
		try {
			IoFileUtil.copyDir(sourceFile, sourceFile, true, false);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
