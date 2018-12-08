package zhaohe.study.websocket.org;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/startServer")
public class ChatServerServlet extends HttpServlet {

	ChatServer chatServer;

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("启动状态： " + ChatServer.isStarted);
		String port = super.getServletContext().getInitParameter("socketPort");
		String socketIp = super.getServletContext().getInitParameter("socketIp");
		String appIp = InetAddress.getLocalHost().getHostAddress();
		if (socketIp == null || socketIp.length() <= 0) {
			socketIp = appIp;
		}
		/*
		 * 如果server没有启动，启动server并设置启动状态为true，同时向所有连接广播数据；
		 * 如果server已经启动，说明有客户端正在广播数据，其他客户端只能接收消息或者只能给自己发送消息；
		 */
		if (!ChatServer.isStarted) {// server没有启动
			this.chatServer = new ChatServer(new InetSocketAddress(socketIp, Integer.parseInt(port)));
			ChatServer.ip = appIp;
			ChatServer.broswer = "";
			this.chatServer.start();
			sleepBeforeSend();
			this.highPrivilegedClientSendMsg(this.chatServer);
		} else {// server已经启动
			System.out.println("server已经启动，当前server连接的客户端数量为：" + this.chatServer.getConnections().size());
		}

	}

	private void highPrivilegedClientSendMsg(ChatServer chatServer) {
		while (true) {
			if (chatServer.getConnections().size() > 0) {
				this.sendMessage(chatServer);
				closeSocket(chatServer);
				break;
			} else {
				try {
					// System.out.println("...");
					TimeUnit.MICROSECONDS.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private static void closeSocket(ChatServer chatServer) {
		try {
			System.out.println("停止SocketServer");
			chatServer.stop(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void sendMessage(ChatServer chatServer) {
		long time = System.currentTimeMillis();
		for (int i = 0; i < 10; i++) {
			chatServer.broadcast("主机" + ChatServer.ip + "，浏览器【" + ChatServer.broswer + "】:"
					+ ChatServer.highPrivilegedConn + "--" + time);
			System.out.println("主机" + ChatServer.ip + "，浏览器【" + ChatServer.broswer + "】:"
					+ ChatServer.highPrivilegedConn + "--" + time);
			try {
				TimeUnit.SECONDS.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private static void sleepBeforeSend() {
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
