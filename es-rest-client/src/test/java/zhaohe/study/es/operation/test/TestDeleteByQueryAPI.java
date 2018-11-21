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
import zhaohe.study.es.operation.singleDocument.DeleteByQueryAPI;
import zhaohe.study.es.operation.utils.TypeConvertUtil;

public class TestDeleteByQueryAPI {
	public static RestClient restClient = null;
	private static Map<String, String> params = null;
	private static DeleteByQueryAPI deleteByQueryAPI = null;
	private static CommonAPI common = null;

	@Before
	public void setup() {
		restClient = InitRestClient.createClient();
		if (params == null) {
			params = new HashMap<>();
		}
		if (deleteByQueryAPI == null) {
			deleteByQueryAPI = new DeleteByQueryAPI();
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
	 * <p>
	 * 删除query匹配出来的记录。
	 * </p>
	 * <p>
	 * 过程没有回滚，只有中断。所以可以通过设置conflicts=proceed统计公有多少个冲突.
	 * </p>
	 * <p>
	 * 如果提供routing的话，按照路由删除也可以：DELETE /twitter/_delete_by_query?routing=user1
	 * </p>
	 * <p>
	 * delete_by_query除了支持pretty之外，还支持：refresh, wait_for_completion,
	 * wait_for_active_shards, timeout and scroll <a href=
	 * 'https://www.elastic.co/guide/en/elasticsearch/reference/current/docs-delete-by-query.html'>
	 * 更多设置参见</a>
	 * </p>
	 * 
	 * @throws IOException
	 */
	@Test
	public void test_delete_by_query() throws IOException {
		//common.put("/twitter/_doc/1", "{\"counter\" : 1,\"tags\" : [\"red\"]}", null, restClient);
		//common.put("/twitter/_doc/2", "{\"counter\" : 2,\"tags\" : [\"white\"]}", null, restClient);
		//common.put("/twitter/_doc/3", "{\"counter\" : 3,\"tags\" : [\"black\"]}", null, restClient);
		params.clear();
		params.put("conflicts", "proceed");
		//默认批量是1000
		params.put("scroll_size", "5000");
		//删除索引twitter中的匹配出来的数据
		String json1 = common.delete_by_query("/twitter/_doc/_delete_by_query","{\"query\":{\"match\": {\"counter\": 1}}}",params, restClient);
		//String json2 = common.delete_by_query("/twitter/_delete_by_query","{\"query\":{\"match\": {\"counter\": 2}}}",params, restClient);
		//删除索引twitter中的_doc类型的数据
		//String json3 = common.delete_by_query("/twitter/_doc/_delete_by_query","{\"query\":{\"match_all\": {}}}",params, restClient);
		System.out.println("witter/_doc/1/:"+common.head("/twitter/_doc/1/", null, restClient));
		System.out.println("witter/_doc/2/:"+common.head("/twitter/_doc/2/", null, restClient));
		System.out.println("witter/_doc/3/:"+common.head("/twitter/_doc/3/", null, restClient));
		System.out.println(json1);
		//System.out.println(json2);
		//System.out.println(json3);
	}

}
