package com.shengsiyuan.study.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

/**
 * Channel不能直接和程序交互，需要buffer中转
 * @author zhaohe
 *
 */
public class ChannelTest {
	public static void main(String[] args) throws FileNotFoundException, IOException {
		//FileChannel转为ByteBuffer
		File f=new File("");
		try(FileChannel inChannel=new FileInputStream(f).getChannel();
				FileChannel outChannel=new FileOutputStream("1.txt").getChannel())
		{
			MappedByteBuffer buffer=inChannel.map(FileChannel.MapMode.READ_ONLY, 0, f.length());
			//GBK字符集创建见解码器
			//字符集是为了解决二进制与字符之间的对应关系
			Charset charset=Charset.forName("GBK");
			outChannel.write(buffer);
			buffer.clear();
			CharsetDecoder decoder=charset.newDecoder();
			CharBuffer charBuffer=decoder.decode(buffer);
			System.out.println(charBuffer.toString());
		}
	}
}
















