package com.shengsiyuan.study.nio;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileLock;

public class FileLockTest {
	//共享锁：允许多个进程读取文件，但是阻止其他进程获得对该文件的排它锁
	//排它锁：锁住文件的读写
	//lock()和tryLock()都是排它锁；
	//lock()是阻塞的，tryLock()是非阻塞的
	/**
	 * 文件锁虽然可以用于并发访问，按时对于高并发的情形，推荐使用数据库保存程序信息，而不是使用文件
	 * @param args
	 */
	
	private static final String GEN_LCK_FILE = "genFile.lck";

    private static FileLock genLock = null;

    @SuppressWarnings("resource")
	public static void main(String args[]) {
        
        try {
            // Acquire an exclusive lock, assure manager is stand alone
            genLock = new FileOutputStream(GEN_LCK_FILE).getChannel().tryLock();
            System.out.println(new File(GEN_LCK_FILE).getAbsolutePath());

            if (null != genLock) {
                //open the file you want!
            } else {
                System.err.println("this file has been open but other person!");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != genLock) {
                try {
                    genLock.release();
                    new File(GEN_LCK_FILE).delete();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
