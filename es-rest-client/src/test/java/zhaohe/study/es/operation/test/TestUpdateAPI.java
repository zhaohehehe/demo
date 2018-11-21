package zhaohe.study.es.operation.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.elasticsearch.client.RestClient;
import org.junit.Before;
import org.junit.Test;

import net.sf.json.JSONObject;
import zhaohe.study.es.client.init.InitRestClient;
import zhaohe.study.es.operation.singleDocument.CommonAPI;
import zhaohe.study.es.operation.singleDocument.UpdateAPI;
import zhaohe.study.es.operation.utils.TypeConvertUtil;

public class TestUpdateAPI {
	public static RestClient restClient = null;
	private static Map<String, String> params = null;
	private static UpdateAPI update = null;
	private static CommonAPI common = null;

	@Before
	public void setup() {
		restClient = InitRestClient.createClient();
		if (params == null) {
			params = new HashMap<>();
		}
		if (update == null) {
			update = new UpdateAPI();
		}
		if (common == null) {
			common = new CommonAPI();
		}
	}
	/**
	 * Instead of sending a partial doc plus an upsert doc, 
	 * setting doc_as_upsert to true will use the contents of doc as the upsert value:
	 * 一个如果文档不存在，会将使用doc的内容作为一个新文档插入(只send一次就可以)
	 * @throws IOException
	 * @throws InterruptedException
	 */
	@Test
	public void test_update_doc_as_upsert() throws IOException, InterruptedException {
		common.post("/test/_doc/4/_update", "{ \"doc\" : {\"name\" : \"new_name\"},\"doc_as_upsert\": true}", params, restClient);
		System.out.println(common.get("/test/_doc/4/", null, restClient));
	}
	/**
	 * 不管文档是否存在都执行脚本
	 * @throws IOException
	 * @throws InterruptedException
	 */
	@Test
	public void test_update_scripted_upsert() throws IOException, InterruptedException {
		common.post("/test/_doc/3/_update",
				"{"
					+ "\"scripted_upsert\":true,"
					+ "\"script\" : {"
						+ "\"id\": \"my_web_session_summariser\","
						+ "\"lang\": \"painless\","
						+ "\"params\" : {"
							+ "\"pageViewEvent\" : {"
									+ "\"url\":\"foo.com/bar\","
									+ "+\"response\":404,"
									+ "\"time\":\"2014-01-01 12:32\""
							+ "}"
						+ "}"
					+ "},"
					+ "\"upsert\" : {}"
			+ "}", params, restClient);
		System.out.println(common.get("/test/_doc/3/", null, restClient));
	}
	/**
	 * 更新插入：如果文档不存在，会作为一个新文档插入；如果已经存在，就会执行相应的更新脚本
	 * @throws IOException
	 * @throws InterruptedException
	 */
	@Test
	public void test_update_upsert() throws IOException, InterruptedException {
		common.post("/test/_doc/2/_update",
					"{"
						+ "\"script\" : {"
							+ "\"source\": \"ctx._source.counter += params.count\","
							+ "\"lang\": \"painless\","
							+ "\"params\" : {"
								+ "\"count\" : 4"
							+ "}"
						+ "},"
						+ "\"upsert\" : {"
							+ "\"counter\" : 1"
						+ "}"
				+ "}", params, restClient);
		System.out.println(common.get("/test/_doc/2/", null, restClient));
		
	}
	/**
	 * 在 更新整个文档 , 我们已经介绍过 更新一个文档的方法是检索并修改它，然后重新索引整个文档，这的确如此。
	 * 然而，使用 update API 我们还可以部分更新文档，例如在某个请求时对计数器进行累加。
	 * 我们也介绍过文档是不可变的：他们不能被修改，只能被替换。 update API 必须遵循同样的规则。 
	 * 从外部来看，我们在一个文档的某个位置进行部分更新。然而在内部， update API 简单使用与之前描述相同的 检索-修改-重建索引 的处理过程。 
	 * 区别在于这个过程发生在分片内部，这样就避免了多次请求的网络开销。通过减少检索和重建索引步骤之间的时间，
	 * 我们也减少了其他进程的变更带来冲突的可能性。
	 * update 请求最简单的一种形式是接收文档的一部分作为 doc 的参数， 它只是与现有的文档进行合并。
	 * 对象被合并到一起，覆盖现有的字段，增加新的字段。 例如，我们增加字段 tags 和 views 到我们的博客文章，如下所示：
	 * @throws IOException
	 * @throws InterruptedException
	 */
	@Test
	public void test_update_merge() throws IOException, InterruptedException {
		//common.put("/test/_doc/1/", "{\"title\" : \"My first title\"}", null, restClient);
		//System.out.println(common.get("/test/_doc/1/", params, restClient));
		
		//重复执行以下update会出现"result": "noop"，更新忽略。可以设置"detect_noop": false来取消这种设置
		//common.post("/test/_doc/1/_update", "{ \"doc\" : {\"name\" : \"new_name\"} }", params, restClient);
		common.post("/test/_doc/1/_update", "{ \"doc\" : {\"name\" : \"new_name\"},\"detect_noop\": false}", params, restClient);
		//System.out.println(common.get("/test/_doc/1/", params, restClient));
		
	}
	/**
	 * @throws IOException
	 * @throws InterruptedException
	 */
	@Test
	public void test_update() throws IOException, InterruptedException {
		String updaet01_path = "src/main/resources/json/update01.properties";
		common.put("/update/doc/1", new JSONObject().accumulate("counter", 1).accumulate("tags", "[\"red\"]").toString(), null, restClient);
		//common.post("/update/doc/1/_update", TypeConvertUtil.streamToJsonString(new FileInputStream(new File(updaet01_path))), 
		//		null, restClient);
		String updaet02_path = "src/main/resources/json/update02.properties";
		//common.post("/update/doc/1/_update", TypeConvertUtil.streamToJsonString(new FileInputStream(new File(updaet02_path))), 
		//		null, restClient);
		//common.post("/update/doc/1/_update", "{\"script\" : \"ctx._source.new_field = 'value_of_new_field'\"}",null, restClient);
		//如果满足条件删除该索引，否则什么都不做
		//common.post("/update/doc/1/_update", "{\"script\" : \"ctx._source.remove('new_field')\"}",null, restClient);
		String updaet03_path = "src/main/resources/json/update03.properties";
		common.post("/update/doc/1/_update", TypeConvertUtil.streamToJsonString(new FileInputStream(new File(updaet03_path))), 
				null, restClient);
			
		System.out.println(common.get("/update/doc/1", null, restClient));
	}
}
