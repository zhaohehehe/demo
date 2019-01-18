package com.zhaohe.demp.util.io;

import java.io.IOException;
import java.io.InputStream;

public class MyOwnInputStream extends InputStream {
	
	protected byte[] data;
	//保存当前度取的位置
	protected int ptr=0;
	protected int mark;
	
	public MyOwnInputStream(byte[] data) {

		this.data=data;
		
	}

	@Override
	public int read() throws IOException {
		return (ptr<data.length)?data[ptr++]:-1;
	}
	
	//还剩多少字节没读
	@Override
	public int available() throws IOException {
		//return super.available();
		return data.length-ptr;
	}
	@Override
	public void close() throws IOException {
		//super.close();
		ptr=data.length;
	}
	@Override
	public synchronized void mark(int readlimit) {
		//super.mark(readlimit);
		this.mark=readlimit;
	}
	@Override
	public synchronized void reset() throws IOException {
		//super.reset();
		if(mark<0||mark>=data.length){
			throw new IOException("the mark is invalid!");
		}
		//Resets the buffer to the marked position
		this.ptr=this.mark;
	}
	@Override
	public boolean markSupported() {
		//return super.markSupported();
		return true;
	}
	@Override
	//b[]={0,1,2,3,4,off,,,,,}
	public int read(byte[] b, int off, int len) throws IOException {
		//ptr=off;
		if(this.ptr>=data.length||len<0){
			return -1;
		}
		if(this.ptr+len>data.length){
			len=data.length-ptr;
		}
		if(len==0){
			return 0;
		}
		//void java.lang.System.arraycopy(Object src, int srcPos, Object dest, int destPos, int length)
		System.arraycopy(data, ptr, b, off, len);
		for(int i=0;i<b.length;i++){
			System.out.println(b[i]);
		}
		ptr+=len;
		return len;
	}


	public static void main(String[] args) throws IOException {
		byte[] data=new byte[]{1,2,3,4,5,6,7,8,9,0};
		MyOwnInputStream mis=new MyOwnInputStream(data);
		mis.read(new byte[4],2,2);
		mis.close();
	}
}
