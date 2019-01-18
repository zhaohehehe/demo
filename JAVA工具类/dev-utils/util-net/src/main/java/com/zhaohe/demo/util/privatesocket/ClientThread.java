package com.zhaohe.demo.util.privatesocket;

import java.io.BufferedReader;
import java.io.IOException;

public class ClientThread extends Thread{
	BufferedReader br=null;
	public ClientThread(BufferedReader br){
		this.br=br;
	}
	@Override
	public void run() {
		try{
			String line=null;
			while((line=br.readLine())!=null){
				//这里只打印了从服务器端独到的内容，实际上可能更复杂，例如聊天用户列表以及游戏五子棋的坐标等等
				System.out.println(line);
			}
		} catch(IOException e){
			e.printStackTrace();
		} finally{
			if(br!=null){
				try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

}
