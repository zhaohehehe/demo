package com.zhaohe.demo.util;

import java.io.File;
import java.util.ArrayList;

public class 展示文件目录结构 {
	/**
	 * 方法二
	 * 递归树状展示某文件夹下的所有内容
	 * @param file
	 * @author shengsiyuan
	 */
	//方法二中，用于判断目录或文件所处的层次
	private static int steps;
	public static void deepFileListByRecursion(File file){
		//如果是文件或者空的目录，则跳出
		if(file.isFile()||0==(file.listFiles().length)){
			return;
		}
		//目录，而且有子目录或者文件
		else{
			File[] files=file.listFiles();
			files=sortListFile(files);
			for(File f:files){
				StringBuffer output=new StringBuffer();
				if(f.isFile()){
					output.append(getIndents(steps));
					output.append(f.getName());
					System.out.println(output);
				}
				else{
					output.append(getIndents(steps));
					output.append(f.getName());
					output.append("\\");
					System.out.println(output);
					steps++;
					deepFileListByRecursion(f);
					//针对于同级目录的情况，假设a,b是同级目录：先遍历a时，深度++；因为a,b同级，所以在遍历b之前，深度要先--；
					steps--;
				}
				//System.out.println(output);//在这里打印会倒着输出
			}
			
		}
	}
	/**方法一，与方法二不同的是会输出给定的根目录
	 * 递归树状展示某文件夹下的所有内容
	 * @param fileDire
	 * @param tabs
	 * @throws Exception
	 * @author zhaohe
	 */
	public static void showFileByRecursion(File fileDire,int steps) throws Exception{
		if(!fileDire.exists()){
			throw new Exception("文件不存在！");
		}
		//如果当前路径fileDire是文件，那么直接输出，层次steps不变
		if(fileDire.isFile()){
			System.out.println(getIndents(steps)+fileDire.getName());
			return;
		}
		//如果当前路径fileDire是目录，那么输出目录后，层次steps+1递归更深一层目录
		else{
			System.out.println(getIndents(steps)+"/"+fileDire.getName());
			steps++;
			//遍历当前路径fileDire下的所有路径
			File[] listFile=fileDire.listFiles();
			//将路径排在上面，文件排在下面
			listFile=sortListFile(listFile);
			for(File file:listFile){
				//如果file的父级文件是fileDire，证明是同级目录，前边steps多加了1，此时steps-1；
				//前提是file是目录，因为steps+1是在目录条件下多加的
				if(file.isDirectory()&&file.getParent().equals(fileDire.getName())){
					steps--;
				}
				showFileByRecursion(file,steps);
			}
		}
	} 
	/**
	 * 将路径排在上面，文件排在下面
	 * @param list
	 * @return
	 */
	public static File[] sortListFile(File[] list){
		ArrayList<File> arrList=new ArrayList<File>();
		for(File file:list){
			if(file.isDirectory()){
				arrList.add(file);
			}
		}
		for(File file:list){
			if(file.isFile()){
				arrList.add(file);
			}
		}
		//转换成文件数组
		return arrList.toArray(new File[list.length]);
		
	}
	/**
	 * 通过文件层次确定缩进大小，每进入新的一层，缩进增加
	 * @param steps 表示文件夹级数，初始值自定义
	 * @return
	 */
	public static String getIndents(int steps){
		StringBuffer sb=new StringBuffer();
		for(int i=0;i<steps;i++){
			sb.append("\t");
		}
		return sb.toString();
	}
	public static void main(String[] args) {
		try {
			展示文件目录结构.showFileByRecursion(new File("d:/test"),0);
			System.out.println("---------------------------");
			展示文件目录结构.deepFileListByRecursion(new File("d:/test"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
