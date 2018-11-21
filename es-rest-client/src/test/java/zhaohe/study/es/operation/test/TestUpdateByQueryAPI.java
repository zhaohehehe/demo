package zhaohe.study.es.operation.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.elasticsearch.client.RestClient;
import org.junit.Before;
import org.junit.Test;

import zhaohe.study.es.client.init.InitRestClient;
import zhaohe.study.es.operation.singleDocument.CommonAPI;
import zhaohe.study.es.operation.singleDocument.UpdateByQueryAPI;
import zhaohe.study.es.operation.utils.TypeConvertUtil;

public class TestUpdateByQueryAPI {
	public static RestClient restClient = null;
	private static Map<String, String> params = null;
	private static UpdateByQueryAPI updateByQueryAPI = null;
	private static CommonAPI common = null;

	@Before
	public void setup() {
		restClient = InitRestClient.createClient();
		if (params == null) {
			params = new HashMap<>();
		}
		if (updateByQueryAPI == null) {
			updateByQueryAPI = new UpdateByQueryAPI();
		}
		if (common == null) {
			common = new CommonAPI();
		}
	}
	/**
	 * 并行删除进程
	 * @throws IOException
	 */
	@Test
	public void test_slice_for_delete_by_query() throws IOException {
		String slice1_path = "src/main/resources/json/slice_delete_by_query01.properties";
		String slice2_path = "src/main/resources/json/slice_delete_by_query02.properties";
		String slice3_path = "src/main/resources/json/slice_delete_by_query03.properties";
		common.post("/twitter/_delete_by_query", TypeConvertUtil.streamToJsonString(new FileInputStream(new File(slice1_path))),
				null, restClient);
		common.post("/twitter/_delete_by_query", TypeConvertUtil.streamToJsonString(new FileInputStream(new File(slice2_path))),
				null, restClient);
		common.get("_refresh", null, restClient);
		params.clear();
		params.put("size", "0");
		params.put("filter_path", "hits.total");
		common.post("/twitter/_search", TypeConvertUtil.streamToJsonString(new FileInputStream(new File(slice3_path))),
				params, restClient);
		//返回值应该这样才对 ：{  "hits" : {    "total" : 0  }}
	}

	/**
	 * 使用Task API来获取任何一个正在运行的delete-by-query请求的状态
	 * @throws IOException
	 */
	@Test
	public void test_task_api_for_delete_by_query() throws IOException {
		//curl -X GET "localhost:9200/_tasks?detailed=true&actions=*/delete/byquery"
		params.clear();
		params.put("detailed", "true");
		params.put("actions", "*/delete/byquery");
		String json1 = common.get("/_tasks", params, restClient);
		
		System.out.println(json1);
	}

	/**
	 * 最简单的用法是不改变
	 * @throws IOException
	 */
	@Test
	public void test_update_by_query() throws IOException {
		
	}

}
