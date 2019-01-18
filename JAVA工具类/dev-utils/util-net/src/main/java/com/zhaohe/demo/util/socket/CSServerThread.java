package com.zhaohe.demo.util.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class CSServerThread implements Runnable{
	Socket s=null;
	//该线程处理的Socket对应的输入流
	BufferedReader br=null;
	public CSServerThread(Socket s) throws IOException{
		this.s=s;
		this.br=new BufferedReader(new InputStreamReader(s.getInputStream()));
	}
	private String readFromClient(){
		try {
			return br.readLine();
		} catch (IOException e) {
			CSServer.socketList.remove(s);
		}
		return null;
	}

	@Override
	public void run() {
		String content=null;
		while((content=this.readFromClient())!=null){
			//将读到的内容向每个Socket发送一次
			for(Socket s:CSServer.socketList){
				PrintStream ps = null;
				try {
					ps = new PrintStream(s.getOutputStream());
				} catch (IOException e) {
					e.printStackTrace();
				}
				ps.println(content);
			}
		}
		
	}
	
}
