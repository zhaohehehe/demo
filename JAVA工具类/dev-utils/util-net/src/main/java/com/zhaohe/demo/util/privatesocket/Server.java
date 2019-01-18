package com.zhaohe.demo.util.privatesocket;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	private static final int SERVER_PORT=30000;
	//保存用户名和对应输出流的对应关系
	public static CrazyitMap<String,PrintStream> clients=new CrazyitMap<>();
	public void init() {
		try(ServerSocket ss=new ServerSocket(SERVER_PORT)){
			while(true){
				Socket s=ss.accept();
				new ServerThread(s).run();
			}
		} catch (IOException e) {
			System.out.println("服务器启动失败，是否端口"+SERVER_PORT+"已经被占用？");
		}
	}
	public static void main(String[] args) {
		Server server=new Server();
		server.init();
		
	}
	
	

}
