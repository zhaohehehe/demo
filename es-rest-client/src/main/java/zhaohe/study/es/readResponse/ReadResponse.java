package zhaohe.study.es.readResponse;

import java.io.IOException;

import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.ParseException;
import org.apache.http.RequestLine;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.client.Request;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;

public class ReadResponse {
	/**
	 * IOException : communication problem (e.g. SocketTimeoutException)
	 * ResponseException : a response was returned, but its status code indicated an error (not 2xx)
	 * A ResponseException is not thrown for HEAD requests that return a 404 status code.但是其他请求
	 * 可以返回404，除非配置ignore参数包含404(ignore is a special client parameter that doesn’t get sent to Elasticsearch and contains a comma separated list of error status codes)
	 * @param restClient
	 * @throws ParseException
	 * @throws IOException
	 */
	public void readResponse(RestClient restClient) throws ParseException, IOException{
		//Information about the performed request
		Response response = restClient.performRequest(new Request("GET", "/"));
		RequestLine requestLine = response.getRequestLine(); 
		HttpHost host = response.getHost(); 
		int statusCode = response.getStatusLine().getStatusCode(); 
		Header[] headers = response.getHeaders(); 
		String responseBody = EntityUtils.toString(response.getEntity()); 
	}
}
