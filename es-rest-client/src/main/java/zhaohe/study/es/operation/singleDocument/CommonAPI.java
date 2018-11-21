package zhaohe.study.es.operation.singleDocument;

import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import org.apache.http.entity.ContentType;
import org.apache.http.nio.entity.NStringEntity;
import org.elasticsearch.client.Request;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.ResponseListener;
import org.elasticsearch.client.RestClient;

import zhaohe.study.es.operation.utils.TypeConvertUtil;

public class CommonAPI {
	private static long refreshInterval = 1l;

	/**
	 * 问题 ：异步执行performRequestAsync不会马上返回数据(虽然可以换成同步请求，但是为什么会这样？？？？？？)
	 * ES有刷新间隔refresh_interval，默认刷新间隔1s.所以睡眠refresh_interval，暂时可以解决没有返回的问题
	 * 如果数据量比较大，不建议频繁刷新
	 * 
	 * @param restClient
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public void sleepRefreshInterval() {
		try {
			TimeUnit.SECONDS.sleep(refreshInterval);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void post(String endpoint, String jsonStr, Map<String, String> params, RestClient restClient) {
		Request request = new Request("POST", endpoint);
		// 添加请求参数
		request.addParameter("pretty", "true");
		if (params != null) {
			for (Entry<String, String> entry : params.entrySet()) {
				request.addParameter(entry.getKey(), entry.getValue());
			}
		}
		request.setEntity(new NStringEntity(jsonStr, ContentType.APPLICATION_JSON));
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
		this.sleepRefreshInterval();
	}

	public void put(String endpoint, String jsonStr, Map<String, String> params, RestClient restClient)
			throws IOException {
		Request request = new Request("PUT", endpoint);
		// 添加请求参数
		request.addParameter("pretty", "true");
		if (params != null) {
			for (Entry<String, String> entry : params.entrySet()) {
				request.addParameter(entry.getKey(), entry.getValue());
			}
		}
		request.setEntity(new NStringEntity(jsonStr, ContentType.APPLICATION_JSON));
		// 同步请求
		// Response response = restClient.performRequest(request);
		// System.out.println(TypeConvertUtil.streamToJsonString(response.getEntity().getContent()));
		// 异步请求
		restClient.performRequestAsync(request, new ResponseListener() {
			@Override
			public void onSuccess(Response response) {
				// 处理response
				System.out.println("PUT SUCCESS");
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
		this.sleepRefreshInterval();
	}

	public String get(String endpoint, Map<String, String> params, RestClient restClient) throws IOException {
		Request request = new Request("GET", endpoint);
		// 添加请求参数
		request.addParameter("pretty", "true");
		if (params != null) {
			for (Entry<String, String> entry : params.entrySet()) {
				request.addParameter(entry.getKey(), entry.getValue());
			}
		}
		Response response = restClient.performRequest(request);
		return TypeConvertUtil.streamToJsonString(response.getEntity().getContent());
	}

	public int head(String endpoint, Map<String, String> params, RestClient restClient) throws IOException {
		Request request = new Request("HEAD", endpoint);
		// 添加请求参数
		request.addParameter("pretty", "true");
		if (params != null) {
			for (Entry<String, String> entry : params.entrySet()) {
				request.addParameter(entry.getKey(), entry.getValue());
			}
		}
		Response response = restClient.performRequest(request);
		return response.getStatusLine().getStatusCode();
	}

	public String delete(String endpoint, Map<String, String> params, RestClient restClient) throws IOException {
		Request request = new Request("DELETE", endpoint);
		// 添加请求参数
		request.addParameter("pretty", "true");
		if (params != null) {
			for (Entry<String, String> entry : params.entrySet()) {
				request.addParameter(entry.getKey(), entry.getValue());
			}
		}
		Response response = restClient.performRequest(request);
		return TypeConvertUtil.streamToJsonString(response.getEntity().getContent());
	}

	public String delete_by_query(String endpoint, String jsonStr, Map<String, String> params, RestClient restClient)
			throws IOException {
		Request request = new Request("POST", endpoint);
		// 添加请求参数
		request.addParameter("pretty", "true");
		if (params != null) {
			for (Entry<String, String> entry : params.entrySet()) {
				request.addParameter(entry.getKey(), entry.getValue());
			}
		}
		request.setEntity(new NStringEntity(jsonStr, ContentType.APPLICATION_JSON));
		Response response = restClient.performRequest(request);
		return TypeConvertUtil.streamToJsonString(response.getEntity().getContent());
	}

	public static long getRefreshInterval() {
		return refreshInterval;
	}

	public static void setRefreshInterval(long refreshInterval) {
		CommonAPI.refreshInterval = refreshInterval;
	}

}
