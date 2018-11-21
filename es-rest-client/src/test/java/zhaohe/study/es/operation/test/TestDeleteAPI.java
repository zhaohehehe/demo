package zhaohe.study.es.operation.test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.elasticsearch.client.RestClient;
import org.junit.Before;
import org.junit.Test;

import zhaohe.study.es.client.init.InitRestClient;
import zhaohe.study.es.operation.singleDocument.CommonAPI;
import zhaohe.study.es.operation.singleDocument.DeleteAPI;

public class TestDeleteAPI {
	public static RestClient restClient = null;
	private static Map<String, String> params = null;
	private static DeleteAPI delete = null;
	private static CommonAPI common = null;

	@Before
	public void setup() {
		restClient = InitRestClient.createClient();
		if (params == null) {
			params = new HashMap<>();
		}
		if (delete == null) {
			delete = new DeleteAPI();
		}
		if (common == null) {
			common = new CommonAPI();
		}
	}
	/**
	 * 超时删除：DELETE /twitter/_doc/1?timeout=5m
	 * 按照指定路由删除：DELETE /twitter/_doc/1?routing=kimchy
	 * @throws IOException
	 */
	@Test
	public void test_delete() throws IOException {
		String json = common.delete("twitter/_doc/3/", null, restClient);
		int code1 = common.head("twitter/_doc/3/", null, restClient);
		System.out.println(code1);
		System.out.println(json);
	}

}
