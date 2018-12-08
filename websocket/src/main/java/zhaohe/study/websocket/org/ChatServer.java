package zhaohe.study.websocket.org;
/*
 * Copyright (c) 2010-2018 Nathan Rajlich
 *
 *  Permission is hereby granted, free of charge, to any person
 *  obtaining a copy of this software and associated documentation
 *  files (the "Software"), to deal in the Software without
 *  restriction, including without limitation the rights to use,
 *  copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the
 *  Software is furnished to do so, subject to the following
 *  conditions:
 *
 *  The above copyright notice and this permission notice shall be
 *  included in all copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 *  EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
 *  OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 *  NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
 *  HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 *  WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 *  FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 *  OTHER DEALINGS IN THE SOFTWARE.
 */

import java.io.Closeable;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;

import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

/**
 * A simple WebSocketServer implementation. Keeps track of a "chatroom".
 */
public class ChatServer extends WebSocketServer implements Closeable {
	/**
	 * 修改超时时间。如果超时，自动断开连接。默认超时时间为60s。
	 */
	private final static int connectionLostTimeout = 10;
	/**
	 * 启动server的主机IP
	 */
	public static String ip;
	/**
	 * 启动server的浏览器类型
	 */
	public static String broswer;

	/**
	 * 启动server的WebSocket连接
	 */
	public static WebSocket highPrivilegedConn;

	/**
	 * 标记 socket server 是否启动，默认false
	 */
	public volatile static boolean isStarted = false;

	// private Map<String, WebSocket> connections =
	// Collections.synchronizedMap(new HashMap<String,WebSocket>());

	public ChatServer(int port) throws UnknownHostException {
		super(new InetSocketAddress(port));
	}

	public ChatServer(InetSocketAddress address) {
		super(address);
	}

	@Override
	public void close() throws IOException {
		isStarted = false;
		// TODO Auto-generated method stub
		try {
			super.stop();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void start() {
		isStarted = true;
		super.setConnectionLostTimeout(connectionLostTimeout);
		super.start();
	}

	@Override
	public void stop(int timeout) throws InterruptedException {
		isStarted = false;
		super.stop(timeout);
	}

	@Override
	public void onOpen(WebSocket conn, ClientHandshake handshake) {
		/*
		 * 条件：isStarted&&this.getConnections().size()==1&&this.getConnections().contains(conn)
		 * 以上条件会包含2种情况： 
		 * 第一种情况是高级websocket客户端加入会话并广播数据；
		 * 第二种情况是server已经启动，但是由于各种意外情况浏览器全部关闭，此时所有的客户端都会离开，session也会随之断开，
		 * 所以此时应该在session中关闭socket连接，确保下次server能正确启动。
		 * (关闭浏览器或者刷新都会导致客户端离线，但是server并不会关闭，所以server的数据会继续产生，只是无法广播出去)
		 * (出现这种异常情况会导致业务逻辑后台执行中断，这种情况只能是考虑在业务逻辑中保留断点，这里没有做任何处理，只是强制关闭socket
		 * server，但是后台业务逻辑还是可以继续执行的。)
		 */
		if (isStarted && this.getConnections().size() == 1 && this.getConnections().contains(conn)) {
			// 设置高级websocket连接
			System.out.println("重置高级websocket连接");
			highPrivilegedConn = conn;
		}
		// This method sends a message to the new client
		conn.send("Welcome to the server!");
		// This method sends a message to all clients connected
		broadcast("new connection: " + handshake.getResourceDescriptor());
		// 如果server已经启动并且当前连接并不是高级连接，那么只需要通知当前连接此时正在有高级连接正在广播即可
		if (isStarted && conn != ChatServer.highPrivilegedConn) {// 或者conn.toString().equals(ChatServer.highPrivilegedConn.toString())，因为conn没有重写toString()方法，比较的依然是内存地址
			conn.send("" + ChatServer.ip + "正在使用" + ChatServer.broswer + "浏览器广播数据，请认真接收！连接ID为："
					+ ChatServer.highPrivilegedConn);
		}
		System.out.println(conn.getRemoteSocketAddress().getAddress().getHostAddress() + " entered the room!");
	}

	@Override
	public void onClose(WebSocket conn, int code, String reason, boolean remote) {
		if (super.getConnections().size() <= 0) {
			// 如果客户端都离开，那么会关闭server。但是本次后台业务逻辑会继续执行。如果出现新的高级客户端，那么2次后台的执行逻辑会交叉执行，
			// 只不过第一次的后台不会广播出去，第二次的后台逻辑会光波导新的高级客户端在页面展示。
			try {
				this.stop(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		broadcast(conn + " has left the room!");
		System.out.println(conn + " has left the room!");
	}

	@Override
	public void onMessage(WebSocket conn, String message) {
		broadcast(message);
		System.out.println(conn + ": " + message);
	}

	@Override
	public void onMessage(WebSocket conn, ByteBuffer message) {
		broadcast(message.array());
		System.out.println(conn + ": " + message);
	}

	@Override
	public void onError(WebSocket conn, Exception ex) {
		ex.printStackTrace();
		if (conn != null) {
			// some errors like port binding failed may not be assignable to a
			// specific websocket
		}
	}

	@Override
	public void onStart() {
		System.out.println("Server started!");
		setConnectionLostTimeout(0);
		setConnectionLostTimeout(100);
	}

}