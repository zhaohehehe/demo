package zhaohe.study.websocket.javax;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.concurrent.TimeUnit;

import javax.websocket.CloseReason;
import javax.websocket.Endpoint;
import javax.websocket.EndpointConfig;
import javax.websocket.MessageHandler;
import javax.websocket.RemoteEndpoint;
import javax.websocket.Session;
/**
 * tomcat 给的javax的websocket源码
 * @author ZH
 *
 */
public class EchoEndpoint extends Endpoint {

	@Override
	public void onOpen(Session session, EndpointConfig endpointConfig) {
		RemoteEndpoint.Basic remoteEndpointBasic = session.getBasicRemote();
		for (int i = 0; i < 100; i++) {
			try {
				remoteEndpointBasic.sendText("hello" + i);
			} catch (Exception e) {
				try {
					session.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		session.addMessageHandler(new EchoMessageHandlerText(remoteEndpointBasic));
		session.addMessageHandler(new EchoMessageHandlerBinary(remoteEndpointBasic));
	}

	@Override
	public void onClose(Session session, CloseReason closeReason) {
		System.out.println(session + " 连接关闭，关闭原因：" + closeReason);
	}

	private static class EchoMessageHandlerText implements MessageHandler.Partial<String> {

		private final RemoteEndpoint.Basic remoteEndpointBasic;

		private EchoMessageHandlerText(RemoteEndpoint.Basic remoteEndpointBasic) {
			this.remoteEndpointBasic = remoteEndpointBasic;
		}

		@Override
		public void onMessage(String message, boolean last) {
			try {
                if (remoteEndpointBasic != null) {
                    remoteEndpointBasic.sendText(message, last);
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
		}
	}

	private static class EchoMessageHandlerBinary implements MessageHandler.Partial<ByteBuffer> {

		private final RemoteEndpoint.Basic remoteEndpointBasic;

		private EchoMessageHandlerBinary(RemoteEndpoint.Basic remoteEndpointBasic) {
			this.remoteEndpointBasic = remoteEndpointBasic;
		}

		@Override
		public void onMessage(ByteBuffer message, boolean last) {
			try {
				if (remoteEndpointBasic != null) {
					remoteEndpointBasic.sendBinary(message, last);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
