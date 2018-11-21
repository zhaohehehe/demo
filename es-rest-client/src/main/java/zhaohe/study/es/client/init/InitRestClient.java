package zhaohe.study.es.client.init;

import java.io.IOException;

import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.apache.http.message.BasicHeader;
import org.elasticsearch.action.support.WriteRequest.RefreshPolicy;
import org.elasticsearch.client.Node;
import org.elasticsearch.client.NodeSelector;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;

public class InitRestClient {
	/**
	 * RestClient是线程安全的
	 * 
	 * @return
	 * @throws IOException
	 */
	public static RestClient createSimpleClient() throws IOException {
		RestClient restClient = RestClient
				.builder(new HttpHost("localhost", 9200, "http"), new HttpHost("localhost", 9201, "http")).build();
		// restClient.close();
		return restClient;
	}

	/**
	 * RestClient是线程安全的
	 * 
	 * @return
	 * @throws IOException
	 */
	public static RestClient createClient() {
		RestClientBuilder builder = RestClient.builder(new HttpHost("localhost", 9200, "http"));
		// 统一设置header，不需要每个请求都设置
		Header[] defaultHeaders = new Header[] { new BasicHeader("header", "value") };
		builder.setDefaultHeaders(defaultHeaders);
		// 设置retry超时时间【推荐设置】,默认30s，和socket的超时时间一样.万一socket的超时时间被自定义了，最大重试超时应相应调整。
		builder.setMaxRetryTimeoutMillis(10000);
		// 设置节点fail监听,需要开启失败探测功能。
		builder.setFailureListener(new RestClient.FailureListener() {
			@Override
			public void onFailure(Node node) {

			}
		});
		// 过滤客户端将会发送请求的节点，默认客户端会发送请求给所有节点
		builder.setNodeSelector(NodeSelector.SKIP_DEDICATED_MASTERS);
		// Set a callback that allows to modify the default request
		// configuration (e.g. request timeouts, authentication, or anything
		// that the org.apache.http.client.config.RequestConfig.Builder allows
		// to set)
		builder.setRequestConfigCallback(new RestClientBuilder.RequestConfigCallback() {
			@Override
			public RequestConfig.Builder customizeRequestConfig(RequestConfig.Builder requestConfigBuilder) {
				return requestConfigBuilder.setSocketTimeout(10000);
				// return
				// requestConfigBuilder.setConnectTimeout(5000).setSocketTimeout(60000);//参考
			}
		})// .setMaxRetryTimeoutMillis(60000)//参考
		;
		// Set a callback that allows to modify the http client configuration
		// (e.g. encrypted communication over ssl, or anything that the
		// org.apache.http.impl.nio.client.HttpAsyncClientBuilder allows to set)
		builder.setHttpClientConfigCallback(new RestClientBuilder.HttpClientConfigCallback() {
			@Override
			public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpClientBuilder) {
				return httpClientBuilder;
				// 这里修改配置，例如下面的代码配置代理
				// return httpClientBuilder.setProxy(new HttpHost("proxy", 9000,"http"))
				// The Apache Http Async Client starts by default one
				// dispatcher thread,
				// and a number of worker threads used by the connection
				// manager,
				// as many as the number of locally detected processors
				// (depending on what
				// Runtime.getRuntime().availableProcessors() returns).
				// The number of threads can be modified as follows:
				// .setDefaultIOReactorConfig(IOReactorConfig.custom().setIoThreadCount(1).build());
			}
		});
		return builder.build();
	}

	public void closeClient(RestClient restClient) {
		try {
			restClient.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
