package com.zhaohe.demo.util.privatesocket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class ServerThread implements Runnable {
	private Socket s = null;
	// 该线程处理的Socket对应的输入流
	BufferedReader br = null;
	PrintStream ps = null;

	public ServerThread(Socket s) throws IOException {
		this.s = s;
	}

	@Override
	public void run() {
		try {
			br = new BufferedReader(new InputStreamReader(s.getInputStream()));
			ps=new PrintStream(s.getOutputStream());
			String content = null;
			while ((content = br.readLine()) != null) {
				// 用户登录名
				if (content.startsWith(CrazyitProtocol.USER_ROUND) && content.endsWith(CrazyitProtocol.USER_ROUND)) {
					String userName = getRealMsg(content);
					if (Server.clients.map.containsKey(userName)) {
						System.out.println("重复");
						ps.println(CrazyitProtocol.NAME_REP);
					} else {
						System.out.println("成功");
						ps.println(CrazyitProtocol.LOGIN_SUCCESS);
						Server.clients.put(userName, ps);
					}
				} else if (content.startsWith(CrazyitProtocol.PRIVATE_ROUND)
						&& content.endsWith(CrazyitProtocol.PRIVATE_ROUND)) {// 私聊信息
					String userAndMsg = getRealMsg(content);
					String user = userAndMsg.split(CrazyitProtocol.SPLIT_SING)[0];
					String msg = userAndMsg.split(CrazyitProtocol.SPLIT_SING)[1];
					Server.clients.map.get(user).println(Server.clients.getKeyByValue(ps) + "悄悄对你说： " + msg);

				} else {// 公聊
					String msg = getRealMsg(content);
					for (PrintStream clientPS : Server.clients.valueSet()) {
						clientPS.println(Server.clients.getKeyByValue(ps) + "说：" + msg);
					}
				}
			}
		} catch (IOException e) {
			Server.clients.removeByValue(ps);
			System.out.println(Server.clients.map.size());
			try {
				if (br != null) {
					br.close();
				}
				if (ps != null) {
					ps.close();
				}
				if (s != null) {
					s.close();
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}

		}

	}

	private String getRealMsg(String content) {
		return content.substring(CrazyitProtocol.PROTOCOL_LEN,content.length()-CrazyitProtocol.PROTOCOL_LEN);
	}

}
