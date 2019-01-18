package com.zhaohe.demo.util.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CSServer {
	public static List<Socket> socketList=Collections.synchronizedList(new ArrayList<>());
	public static void main(String[] args) throws IOException {
		ServerSocket ss=new ServerSocket(30000);
		while(true){
			Socket s=ss.accept();
			socketList.add(s);
			new Thread(new CSServerThread(s)).start();
		}
	}
	
}
