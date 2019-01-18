package com.zhaohe.demo.util.privatesocket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

public class Client {
	public static void main(String[] args) {
		Client client = new Client();
		client.init();
		client.readAndSend();
	}
	//读取键盘输出，并向网络发送
	private void readAndSend() {
		try{
			String line=null;
			//不断读取键盘输入
			while((line=keyIn.readLine())!=null){
				if(line.indexOf(":")>0 && line.startsWith("//")){
					//想发送私聊信息
					line=line.substring(2);
					ps.println(CrazyitProtocol.PRIVATE_ROUND+line.split(":")[0]+
							CrazyitProtocol.SPLIT_SING+line.split(":")[1]+CrazyitProtocol.PRIVATE_ROUND);
					
				}else{
					ps.println(CrazyitProtocol.MSG_ROUND+line+CrazyitProtocol.MSG_ROUND);
				}
			}
		}catch(IOException e){
			System.out.println("网络异常，请重新登陆");
			closeRs();
			System.exit(1);
			
		}
		
	}
	private static final int SERVER_PORT=30000;
	private Socket socket;
	private PrintStream ps;
	private BufferedReader brServer;
	private BufferedReader keyIn;
	public void init(){
		try{
			keyIn=new BufferedReader(new InputStreamReader(System.in));
			socket=new Socket("127.0.0.1",SERVER_PORT);
			ps=new PrintStream(socket.getOutputStream());
			brServer=new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String tip="";
			//不断弹出对话框，要求用户输入
			while(true){
				String userName=JOptionPane.showInputDialog(tip+"输入用户名");
				//输入名字前后加协议字符串后发送
				ps.println(CrazyitProtocol.USER_ROUND+userName+CrazyitProtocol.USER_ROUND);
				//读取服务端响应
				String result=brServer.readLine();
				//如果用户名重复，则开始下一次循环
				if(result.equals(CrazyitProtocol.NAME_REP)){
					tip="用户名重复，请重新";
					continue;
				}
				if(result.equals(CrazyitProtocol.LOGIN_SUCCESS)){
					break;//登陆成功
				}
			}
		} catch(UnknownHostException e){
			System.out.println("找不到远程服务器，请确认服务已经启动");
			closeRs();
			System.exit(1);
		} catch(IOException e1){
			System.out.println("网络异常，请重新登陆");
			closeRs();
			System.exit(1);
		}
		new ClientThread(brServer).start();
	}
	private void closeRs() {
		try{
			if(keyIn!=null){
				keyIn.close();
			}
			if(brServer!=null){
				brServer.close();
			}
			if(ps!=null){
				ps.close();
			}
			if(socket!=null){
				socket.close();
			}
		} catch(IOException e){
			e.printStackTrace();
		}
		
	}
	
}
