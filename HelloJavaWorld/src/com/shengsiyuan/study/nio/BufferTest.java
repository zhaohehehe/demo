package com.shengsiyuan.study.nio;

import java.nio.CharBuffer;

public class BufferTest {
	public static void show(CharBuffer buff){
		System.out.println("capacity: "+buff.capacity());
		System.out.println("limit: "+buff.limit());
		System.out.println("position: "+buff.position());
	}
	public static void main(String[] args) {
		//创建普通buffer，成本低；可以在buffer的基础上创建buffer(buff.allocate(capacity))，成本高
		CharBuffer buff = CharBuffer.allocate(8);
		BufferTest.show(buff);
		//
		buff.put('a');
		buff.put('b');
		buff.put('a');
		BufferTest.show(buff);
		//为从buffer中取数据做准备
		buff.flip();
		BufferTest.show(buff);
		//为向buffer中装入数据做准备
		buff.clear();
		BufferTest.show(buff);
		//System.out.println(buff.get(2));
		buff.put('c');
		System.out.println(buff.get(3));
		BufferTest.show(buff);
		
		
	}
}
