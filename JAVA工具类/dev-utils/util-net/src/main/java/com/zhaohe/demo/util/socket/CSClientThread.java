package com.zhaohe.demo.util.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class CSClientThread implements Runnable{
	private Socket s;
	BufferedReader br=null;
	public CSClientThread(Socket s) throws IOException{
		this.s=s;
		this.br=new BufferedReader(new InputStreamReader(s.getInputStream()));
	}
	@Override
	public void run() {
		String content=null;
		try {
			while((content=br.readLine())!=null){
				System.out.println(content);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}








