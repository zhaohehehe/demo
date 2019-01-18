package com.zhaohe.demo.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;

public class IoFileUtil {
	public static void main(String[] args) throws IOException {
		insertFile("D:/zhaohe/workspace/workspace_JavaW"+
	"/zhaohe/HelloWorld/src/com/shengsiyuan/study/io/stream/bytes/output.txt",45,"插入的内容\r\n");
	}
	/*
	 ************************** insert******************************************************
	 */
	public static void insertFile(String fileName,long pos,String insertContent) throws IOException{
		File tmp=File.createTempFile("tmp", null);
		tmp.deleteOnExit();
		try(
				RandomAccessFile raf=new RandomAccessFile(fileName, "rw");
				FileOutputStream tmpOut=new FileOutputStream(tmp);
				FileInputStream tmpIn=new FileInputStream(tmp);
				)
		{
			raf.seek(pos);
			int hasRead=0;
			byte[] buf=new byte[64];
			while((hasRead=(raf.read(buf)))>0){
				tmpOut.write(buf, 0, hasRead);
			}
			raf.seek(pos);
			raf.write(insertContent.getBytes());
			while((hasRead=(tmpIn.read(buf)))>0){
				raf.write(buf, 0, hasRead);
			}
		}
	}

	/*
	 ************************** copy******************************************************
	 */

	/**
	 * 拷贝单一文件到某个文件，例如将文件 例如将文件 c:/1.txt 拷贝到 d:/2.txt
	 *
	 * @param sourceFile
	 * @param destFile
	 * @param isCover
	 *            如果文件名相同但是不是最新文件，是否选择覆盖
	 * @throws IOException
	 * @throws Exception
	 */
	public static boolean copyFile(String sourceFile, String destFile, boolean isCover) throws IOException, Exception {
		return copyFile(new File(sourceFile), new File(destFile), isCover);
	}

	public static boolean copyFile(File sourceFile, File destFile, boolean isCover) throws Exception, IOException {
		if (!sourceFile.exists() || !destFile.exists()) {
			throw new Exception("源文件或者目标文件不存在，拷贝失败！");
		} else if (sourceFile.isDirectory()) {
			throw new Exception("源文件不是正确的文件格式，拷贝失败！" + sourceFile.getAbsolutePath());
		} else if (destFile.isDirectory()) {
			throw new Exception("目标文件不是正确的文件格式，拷贝失败！" + destFile.getAbsolutePath());
		} else if (sourceFile.isFile() && destFile.isFile()
				&& sourceFile.getAbsolutePath().equals(destFile.getAbsolutePath())) {
			throw new Exception("源文件与目标文件相同！" + destFile.getAbsolutePath());
		} else if (sourceFile.isFile() && destFile.isFile() && sourceFile.getName().equals(destFile.getName())
				&& destFile.lastModified() > sourceFile.lastModified() && !isCover) {
			throw new Exception(sourceFile.getAbsolutePath() + "目标文件已经是最新的，不安全操作，拷贝失败！" + destFile.getAbsolutePath());
		} else {
			FileInputStream in = null;
			FileOutputStream out = null;
			try {
				in = new FileInputStream(sourceFile);
				out = new FileOutputStream(destFile);
				byte[] buffer = new byte[8192];
				int count = 0;
				do {
					out.write(buffer, 0, count);
					count = in.read(buffer, 0, buffer.length);
				} while (count != -1);
				return true;
			} finally {
				CloseUtil.close(out);
				CloseUtil.close(in);
			}
		}
	}

	/**
	 * 拷贝单一文件到某个目录下
	 *
	 * @param srcFile
	 * @param desF_Dir
	 * @param isCover
	 * @throws Exception
	 */
	public static File copyFileToDir(File srcFile, String desF_Dir, boolean isCover, boolean isCreateDesDirIfNoExist)
			throws Exception {
		File defFile = new File(desF_Dir);
		if (!srcFile.exists()) {
			throw new Exception("源文件不存在，拷贝失败！");
		}
		if (!srcFile.isFile()) {
			throw new Exception("源文件不是标准文件，拷贝失败！");
		}
		if (defFile.exists() && defFile.isFile()) {
			throw new Exception("目标路径不是标准目录，拷贝失败！");
		}
		if (!defFile.exists() && isCreateDesDirIfNoExist) {
			defFile.mkdirs();
		} else if (!defFile.exists() && !isCreateDesDirIfNoExist) {
			throw new Exception("目录不存在，拷贝失败！");
		}
		File[] files = defFile.listFiles();
		File newDesFile = new File(desF_Dir + File.separator + srcFile.getName());
		for (File file : files) {
			if (file.isFile() && file.getName().equals(srcFile.getName()) && !isCover) {
				throw new Exception("目标目录已存在相同文件，拷贝失败！");
			}
		}
		newDesFile.createNewFile();
		copyFile(srcFile, newDesFile, true);
		return newDesFile;

	}

	/**
	 * 例如将文件 例如将文件 c:/1.txt 拷贝到 d:/2.txt
	 *
	 * @param srcF_Dir
	 * @param desF_Dir
	 * @param isCover
	 * @param isCreateDesDirIfNoExist
	 *            如果目录不存在，是否选择创建或者终止执行
	 * @return
	 * @throws IOException
	 * @throws Exception
	 */
	public static void copyDir(String srcF_Dir, String desF_Dir, boolean isCover, boolean isCreateDesDirIfNoExist)
			throws Exception, IOException {
		File srcFile = new File(srcF_Dir);
		File desFile = new File(desF_Dir);
		if (srcF_Dir.equals(desF_Dir)) {
			throw new Exception("源目录与目标目录相同，拷贝失败！");
		}
		if (!srcFile.exists()) {
			throw new Exception("源目录不存在，拷贝失败！");
		} else if (srcFile.exists() && srcFile.isFile()) {
			throw new Exception("源目录不是正确的目录格式，拷贝失败！");
		} else if (desFile.exists() && desFile.isFile()) {
			throw new Exception("目标目录不是正确的目录格式，拷贝失败！");
		} else if (!desFile.exists() && isCreateDesDirIfNoExist) {
			desFile.mkdirs();
		} else if (!desFile.exists() && !isCreateDesDirIfNoExist) {
			throw new Exception("目标目录不存在，拷贝失败！");
		} else {
			// do what
		}
		// 遍历当前目录srcFile下的所有文件
		File[] listFile = srcFile.listFiles();
		for (File file : listFile) {
			String desPathTemp = desF_Dir + File.separator + file.getName();
			File desFileTemp = new File(desPathTemp);
			// 如果是文件，进行文件拷贝
			if (file.isFile()) {
				desFileTemp.createNewFile();
				IoFileUtil.copyFile(file, desFileTemp, isCover);
			}
			// 如果是目录，目录递归拷贝
			else {
				desFileTemp.mkdirs();
				copyDir(file.getAbsolutePath(), desFileTemp.getAbsolutePath(), isCover, true);
			}
		}
	}

	/*
	 ***************************** delete***********************************************
	 */
	public static boolean deleteFile(String filePath) {
		return new File(filePath).delete();
	}

	public static void deleteDir(File dir) {
		if (dir == null || !dir.exists()) {
			return;
		}
		if (dir.isFile()) {
			dir.delete();
		} else {
			File[] fileList = dir.listFiles();
			for (File fileTemp : fileList) {
				deleteDir(fileTemp);
			}
			dir.delete();
		}
	}

	public static void deleteFileInDir(File dir) {
		if (dir == null || !dir.exists()) {
			return;
		}
		if (dir.isFile()) {
			dir.delete();
		} else {
			File[] fileList = dir.listFiles();
			for (File fileTemp : fileList) {
				deleteFileInDir(fileTemp);
			}
		}
	}

	public static void deleteDir(String dir) throws Exception {
		deleteDir(new File(dir));
	}

	public static void deleteFileInDir(String dir) throws Exception {
		deleteFileInDir(new File(dir));
	}
}
