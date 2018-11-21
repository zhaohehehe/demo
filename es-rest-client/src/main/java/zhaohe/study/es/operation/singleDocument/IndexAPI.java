package zhaohe.study.es.operation.singleDocument;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.http.entity.ContentType;
import org.apache.http.nio.entity.NStringEntity;
import org.elasticsearch.client.Request;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.ResponseListener;
import org.elasticsearch.client.RestClient;

import zhaohe.study.es.operation.utils.TypeConvertUtil;

/**
 * <a href='https://www.elastic.co/guide/en/elasticsearch/reference/6.4/docs-index_.html'>创建文档索引</a>
 * @author ZH
 *
 */
public class IndexAPI {
	
	/**
	 * $ curl -XPUT 'http://localhost:9200/twitter/tweet/1' -d '{ "user" :
	 * "kimchy", "post_date" : "2009-11-15T14:12:12", "message" :
	 * "trying out Elasticsearch" }'
	 * 
	 * @throws IOException
	 */

	public void put_index(RestClient restClient) throws IOException {
		Request request = new Request("PUT", "/posts/doc/2");
		// 添加请求参数
		request.addParameter("pretty", "true");
		// 设置请求体 to any HttpEntity
		String jsonString = "{" + "\"user\":\"kimchy\"," + "\"postDate\":\"2013-01-30\","
				+ "\"message\":\"trying out Elasticsearch\"" + "}";
		request.setEntity(new NStringEntity(jsonString, ContentType.APPLICATION_JSON));
		// 同步请求
		// Response response = restClient.performRequest(request);
		// 异步请求
		restClient.performRequestAsync(request, new ResponseListener() {
			@Override
			public void onSuccess(Response response) {
				// 处理response
				System.out.println("SUCCESS");
				try {
					System.out.println(TypeConvertUtil.streamToJsonString(response.getEntity().getContent()));
				} catch (UnsupportedOperationException | IOException e) {
					e.printStackTrace();
				}
			}
			@Override
			public void onFailure(Exception exception) {
				// 处理failure
				System.out.println("FAIL");
				System.out.println(exception.getMessage());
			}
		});
	}
	public void put_index_append_version(RestClient restClient) throws IOException {
		Request request = new Request("PUT", "/posts/doc/4");
		// 添加请求参数
		request.addParameter("version_type", "external"); 
		request.addParameter("version", "0"); 
		// 设置请求体 to any HttpEntity
		String jsonString = "{" + "\"user\":\"kimchy\"," + "\"postDate\":\"2013-01-30\","
				+ "\"message\":\"trying out Elasticsearch\"" + "}";
		request.setEntity(new NStringEntity(jsonString, ContentType.APPLICATION_JSON));
		// 同步请求
		// Response response = restClient.performRequest(request);
		// 异步请求
		restClient.performRequestAsync(request, new ResponseListener() {
			@Override
			public void onSuccess(Response response) {
				// 处理response
				System.out.println("SUCCESS");
				try {
					System.out.println(TypeConvertUtil.streamToJsonString(response.getEntity().getContent()));
				} catch (UnsupportedOperationException | IOException e) {
					e.printStackTrace();
				}
			}
			@Override
			public void onFailure(Exception exception) {
				// 处理failure
				System.out.println("FAIL");
				System.out.println(exception.getMessage());
			}
		});
	}
	public void put_index_append_auto_id(RestClient restClient) throws IOException {
		Request request = new Request("PUT", "/blog/page");
		// 设置请求体 to any HttpEntity
		String jsonString = "{" + "\"user\":\"kimchy\"," + "\"postDate\":\"2013-01-30\","
				+ "\"message\":\"trying out Elasticsearch\"" + "}";
		request.setEntity(new NStringEntity(jsonString, ContentType.APPLICATION_JSON));
		// 同步请求
		// Response response = restClient.performRequest(request);
		// 异步请求
		restClient.performRequestAsync(request, new ResponseListener() {
			@Override
			public void onSuccess(Response response) {
				// 处理response
				System.out.println("SUCCESS");
				try {
					System.out.println(TypeConvertUtil.streamToJsonString(response.getEntity().getContent()));
				} catch (UnsupportedOperationException | IOException e) {
					e.printStackTrace();
				}
			}
			@Override
			public void onFailure(Exception exception) {
				// 处理failure
				System.out.println("FAIL");
				System.out.println(exception.getMessage());
			}
		});
	}
}
