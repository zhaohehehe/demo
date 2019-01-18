package com.zhaohe.demo.util.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class CSClient {
	public static void main(String[] args) throws IOException {
		Socket s=new Socket("127.0.0.1",30000);
		//不断读取服务器发来的数据
		new Thread(new CSClientThread(s)).start();
		PrintStream ps=new PrintStream(s.getOutputStream());
		String line=null;
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		while((line=br.readLine())!=null){
			ps.println(line);
		}
	}
}
