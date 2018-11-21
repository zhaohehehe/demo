package zhaohe.study.es.client.init;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

public class InitTransportClient {
	public static void main(String[] args) throws UnknownHostException {
		// on startup
		// (1) EMPTY Settings
		@SuppressWarnings("resource")
		TransportClient client = new PreBuiltTransportClient(Settings.EMPTY)
				.addTransportAddress(new TransportAddress(InetAddress.getByName("host1"), 9300))
				.addTransportAddress(new TransportAddress(InetAddress.getByName("host2"), 9300));
		// (2)
		Settings settings = Settings.builder().put("cluster.name", "myClusterName").build();
		TransportClient client1 = new PreBuiltTransportClient(settings);
		// Add transport addresses and do something with the client...

		// (3)
		Settings settings1 = Settings.builder().put("client.transport.sniff", true).build();
		TransportClient client11 = new PreBuiltTransportClient(settings1);
		// Add transport addresses and do something with the client...

		// on shutdown
		client11.close();
	}
}
