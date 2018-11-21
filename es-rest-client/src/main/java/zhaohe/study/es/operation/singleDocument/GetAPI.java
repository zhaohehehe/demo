package zhaohe.study.es.operation.singleDocument;

import java.io.IOException;

import org.elasticsearch.client.Request;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;

import zhaohe.study.es.operation.utils.TypeConvertUtil;

/**
 * <a href='https://www.elastic.co/guide/en/elasticsearch/reference/6.4/docs-get.html'>GET文档</a>
 * @author ZH
 *
 */
public class GetAPI {
	public String get_index(RestClient restClient) throws IOException {
		Request request = new Request("GET", "/posts/doc/2");
		// 添加请求参数
		request.addParameter("pretty", "true");
		request.addParameter("realtime", "false");
		request.addParameter("_source", "false");
		Response response = restClient.performRequest(request);
		return TypeConvertUtil.streamToJsonString(response.getEntity().getContent());
	}
	public String get_index_filter(RestClient restClient) throws IOException {
		Request request = new Request("GET", "/posts/doc/2");
		// 添加请求参数
		request.addParameter("pretty", "true");
		request.addParameter("realtime", "false");
		//_source_include=*.id&_source_exclude=entities
		//或者_source=*.id,retweeted
		request.addParameter("_source_include", "*.id,*ser");
		request.addParameter("_source_exclude", "postDate");
		Response response = restClient.performRequest(request);
		return TypeConvertUtil.streamToJsonString(response.getEntity().getContent());
	}
	
	public String get_index_append_stored_fields(RestClient restClient) throws IOException{
		Request request = new Request("GET", "/posts/doc/2");
		// 添加请求参数
		request.addParameter("pretty", "true");
		request.addParameter("realtime", "false");
		//_source_include=*.id&_source_exclude=entities
		//或者_source=*.id,retweeted
		request.addParameter("_source_include", "*.id,*ser");
		request.addParameter("_source_exclude", "postDate");
		Response response = restClient.performRequest(request);
		return TypeConvertUtil.streamToJsonString(response.getEntity().getContent());
		
		
	}
}
