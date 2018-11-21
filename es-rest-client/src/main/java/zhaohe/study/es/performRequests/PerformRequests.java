package zhaohe.study.es.performRequests;

import java.io.IOException;

import org.apache.http.entity.ContentType;
import org.apache.http.nio.entity.NStringEntity;
import org.elasticsearch.client.HttpAsyncResponseConsumerFactory;
import org.elasticsearch.client.Request;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.ResponseListener;
import org.elasticsearch.client.RestClient;

public class PerformRequests {
	//start==================================================
	/**
	 * RequestOptions类可以设置部分请求配置在所有请求中共享。可以使用单例模式共享请求
	 * The RequestOptions class holds parts of the request that should be shared between
	 * many requests in the same application.
	 * You can make a singleton instance and share it between all requests:
	 */
	private static final RequestOptions COMMON_OPTIONS;
	private static final String TOKEN = null;
	static {
	    RequestOptions.Builder builder = RequestOptions.DEFAULT.toBuilder();
	    //Add any headers needed by all requests.
	    //required for authorization or to work with a proxy in front of Elasticsearch
	    //没有必要设置Content-Type header because the client will automatically set that from the HttpEntity attached to the request.
	    builder.addHeader("Authorization", "Bearer " + TOKEN); 
	    builder.setHttpAsyncResponseConsumerFactory(    //Customize the response consumer  
	    //You can also customize the response consumer used to buffer the asynchronous responses. 
	    //The default consumer will buffer up to 100MB of response on the JVM heap. 
	    //If the response is larger then the request will fail.
	    //You could, for example, lower the maximum size which might be useful if you are running in a heap constrained environment like the exmaple above.
	        new HttpAsyncResponseConsumerFactory.HeapBufferedResponseConsumerFactory(30 * 1024 * 1024 * 1024));
	    COMMON_OPTIONS = builder.build();
	}
	/**
	 * 自定义共享 RequestOptions
	 * @param request
	 */
	public void requestOptionsBuild(Request request){
		//使用单例模式的RequestOptions
		request.setOptions(COMMON_OPTIONS);
		//在单例模式的RequestOptions的基础上自定义
		RequestOptions.Builder options = COMMON_OPTIONS.toBuilder();
		options.addHeader("cats", "knock things off of other things");
		request.setOptions(options);
	}
	//end=================================================
	/**
	 * 同步请求
	 * 
	 * @param restClient
	 * @throws IOException
	 */
	public void synchronousRequest(RestClient restClient) throws IOException {
		Request request = new Request("GET", // The HTTP method (GET, POST,
												// HEAD, etc)
				"/"); // The endpoint on the server
		Response response = restClient.performRequest(request);
	}
	
	/**
	 * 异步请求
	 * @param restClient
	 */
	public void asynchronousRequest(RestClient restClient) {
		Request request = new Request("GET", "/");
		restClient.performRequestAsync(request, new ResponseListener() {
			@Override
			public void onSuccess(Response response) {
				// 处理response
			}

			@Override
			public void onFailure(Exception exception) {
				// 处理failure
			}
		});
		// 添加请求参数
		request.addParameter("pretty", "true");
		//设置请求体 to any HttpEntity
		request.setEntity(new NStringEntity(
		        "{\"json\":\"text\"}",
		        ContentType.APPLICATION_JSON));
		//同上，默认ContentType=application/json
		//request.setJsonEntity("{\"json\":\"text\"}");
	}
}
