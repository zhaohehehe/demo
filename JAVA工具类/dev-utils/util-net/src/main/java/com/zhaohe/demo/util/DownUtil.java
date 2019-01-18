package com.zhaohe.demo.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * 多线程下载
 * 
 * @author zhaohe
 *
 */
public class DownUtil {
	public static void main(String[] args) throws IOException {
		final DownUtil downUtil = new DownUtil("http://www.baidu.com/img/bd_logo1.png?where=super", "baidu.png", 4);
		downUtil.download();
		new Thread(() -> {
			while (downUtil.getCompleteRate() < 1) {
				// 每隔0.1秒查询一次任务的完成进度，GUI程序中可根据该进度来绘制进度条
				System.out.println("已完成" + downUtil.getCompleteRate());
				try {
					TimeUnit.SECONDS.sleep((long) 0.1);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

	private String path;// 下载资源路径
	private String taregetFile;// 下载文件位置
	private int threadNum;// 县城数量
	private DownThread[] threads;// 下载线程对象
	private int fileSize;// 下载文件大小

	public DownUtil(String path, String targetFile, int threadNum) {
		this.path = path;
		this.taregetFile = targetFile;
		this.threadNum = threadNum;
		threads = new DownThread[threadNum];
	}

	// 获取下载完成的百分比
	public double getCompleteRate() {
		int sumSize = 0;
		for (int i = 0; i < threadNum; i++) {
			sumSize += threads[i].length;
		}
		return sumSize * 1.0 / fileSize;
	}

	public void download() throws IOException {
		URL url = new URL(path);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setConnectTimeout(5 * 1000);
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Accept",
				"image/gif,image/jpeg,image/pjpeg," + "application/x-shockwave-flash,application/xaml+xml,"
						+ "application/vnd.ms-xpsdocument,application/x-ms-xbap,"
						+ "application/x-ms-application,application/vnd.ms-excel,"
						+ "applicatoion/vnd.ms-powerpoint,application/msword,*/**");
		conn.setRequestProperty("Accept-Language", "zh-CN");
		conn.setRequestProperty("Charset", "UTF-8");
		conn.setRequestProperty("Connection", "Keep-Alive");
		fileSize = conn.getContentLength();// 得到文件大小
		conn.disconnect();// HttpURLConnection可以被他人调用
		int currentPartSize = fileSize / threadNum + 1;
		RandomAccessFile file = new RandomAccessFile(taregetFile, "rw");
		file.setLength(currentPartSize);
		file.close();
		for (int i = 0; i < threadNum; i++) {
			// 计算每个线程的下载位置
			int startPos = i * currentPartSize;
			RandomAccessFile currentPart = new RandomAccessFile(taregetFile, "rw");
			currentPart.seek(startPos);
			threads[i] = new DownThread(startPos, currentPartSize, currentPart);
			threads[i].start();
		}

	}

	private class DownThread extends Thread {
		private int startPos;
		private int currentPartSize;
		private RandomAccessFile currentPart;
		public int length;// 该线程已经下载的字节数

		public DownThread(int startPos, int currentPartSize, RandomAccessFile currentPart) {
			super();
			this.startPos = startPos;
			this.currentPartSize = currentPartSize;
			this.currentPart = currentPart;
		}

		@Override
		public void run() {
			try {
				URL url = new URL(path);
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setConnectTimeout(5 * 1000);
				conn.setRequestMethod("GET");
				conn.setRequestProperty("Accept",
						"image/gif,image/jpeg,image/pjpeg," + "application/x-shockwave-flash,application/xaml+xml,"
								+ "application/vnd.ms-xpsdocument,application/x-ms-xbap,"
								+ "application/x-ms-application,application/vnd.ms-excel,"
								+ "applicatoion/vnd.ms-powerpoint,application/msword,*/**");
				conn.setRequestProperty("Accept-Language", "zh-CN");
				conn.setRequestProperty("Charset", "UTF-8");
				InputStream inStream = conn.getInputStream();
				// 跳过startPos字节
				inStream.skip(this.startPos);
				byte[] buffer = new byte[1024];
				int hasRead = 0;
				while (length < this.currentPartSize && (hasRead = inStream.read(buffer)) != -1) {
					currentPart.write(buffer, 0, hasRead);
					length += hasRead;
				}
				currentPart.close();
				inStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

	}
}
