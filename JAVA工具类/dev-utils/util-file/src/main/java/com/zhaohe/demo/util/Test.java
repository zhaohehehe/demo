package com.zhaohe.demo.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Test {
	public static void main(String[] args) throws IOException {
		
		InputStream is=new FileInputStream(new File("C:\\ProgramData\\MySQL\\MySQL Server 5.5\\data\\mysql\\servers.frm"));
		byte[] bytes=new byte[1024];
		while(true){
			int len=is.read(bytes);
			if(len==-1){break;}
			System.out.println(new String(bytes,0,len));
		}
	}
}
