package zhaohe.study.websocket.org;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;

import org.junit.Test;

import zhaohe.study.websocket.org.ChatServer;

public class ChatServerTest {
	@Test
	public void testChatServer() throws InterruptedException, IOException {
		@SuppressWarnings("resource")
		ChatServer s = new ChatServer(new InetSocketAddress("127.0.0.1", 8888));
		s.start();
		System.out.println("ChatServer started on port: " + s.getPort());
		BufferedReader sysin = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			String in = sysin.readLine();
			s.broadcast(in);
			if (in.equals("exit")) {
				s.stop(1000);
				break;
			}
		}
	}
}
