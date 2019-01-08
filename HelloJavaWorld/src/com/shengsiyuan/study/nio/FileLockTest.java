package com.shengsiyuan.study.nio;

public class FileLockTest {
	//共享锁：允许多个进程读取文件，但是阻止其他进程获得对该文件的排它锁
	//排它锁：锁住文件的读写
	//lock()和tryLock()都是排它锁；
	//lock()是阻塞的，tryLock()是非阻塞的
	/**
	 * 文件锁虽然可以用于并发访问，按时对于高并发的情形，推荐使用数据库保存程序信息，而不是使用文件
	 * @param args
	 */
	public static void main(String[] args) {
		
	}
}
