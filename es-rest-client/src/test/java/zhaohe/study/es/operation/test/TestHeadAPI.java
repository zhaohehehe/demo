package zhaohe.study.es.operation.test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.elasticsearch.client.RestClient;
import org.junit.Before;
import org.junit.Test;

import zhaohe.study.es.client.init.InitRestClient;
import zhaohe.study.es.operation.singleDocument.CommonAPI;
import zhaohe.study.es.operation.singleDocument.HeadAPI;

public class TestHeadAPI {
	public static RestClient restClient = null;
	private static Map<String, String> params = null;
	private static HeadAPI head = null;
	private static CommonAPI common = null;

	@Before
	public void setup() {
		restClient = InitRestClient.createClient();
		if (params == null) {
			params = new HashMap<>();
		}
		if (head == null) {
			head = new HeadAPI();
		}
		if (common == null) {
			common = new CommonAPI();
		}
	}
	/**
	 * head判断文档是否存在
	 * @throws IOException
	 */
	@Test
	public void test_head() throws IOException {
		params.clear();
		params.put("routing", "user2");
		int code1 = common.head("twitter/_doc/3/", null, restClient);
		int code2 = common.head("twitter/_doc/3/_source", null, restClient);
		System.out.println(code1);
		System.out.println(code2);
	}
	public static void main(String[] args) {
		TestHeadAPI api = new TestHeadAPI();
		api.setup();
		try {
			api.test_head();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
