package zhaohe.study.websocket.org;

import java.net.URI;
import java.net.URISyntaxException;

import org.junit.Test;

import zhaohe.study.websocket.org.ExampleClient;

public class ExampleClientTest {
	@Test
	public void testExampleClient() throws URISyntaxException {
		// more about drafts here:
		// http://github.com/TooTallNate/Java-WebSocket/wiki/Drafts
		ExampleClient c = new ExampleClient(new URI("ws://127.0.0.1:8888"));
		c.connect();
	}
}
