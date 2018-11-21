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
import zhaohe.study.es.operation.singleDocument.GetAPI;
import zhaohe.study.es.operation.utils.TypeConvertUtil;

public class TestGetAPI {
	public static RestClient restClient = null;
	private static Map<String, String> params = null;
	private static GetAPI get = null;
	private static CommonAPI common = null;

	@Before
	public void setup() {
		restClient = InitRestClient.createClient();
		if (params == null) {
			params = new HashMap<>();
		}
		if (get == null) {
			get = new GetAPI();
		}
		if (common == null) {
			common = new CommonAPI();
		}
	}
	
	/**
	 * 原来的查询语句：“请告诉我，USER1的文档数量一共有多少”
	 * 使用自定义Routing（在USESR　ID上）后的查询语句：“请告诉我，USER1的文档数量一共有多少，它就在第三个分片上，其它的分片就不要去扫描了”
	 * 这样一个个性化的routing值可以确保相关的文档存储到同样的分片上，大大减少系统资源的浪费。
	 * @throws IOException
	 */
	@Test
	public void test_routing() throws IOException {
		params.clear();
		params.put("routing", "user2");
		common.put("twitter/_doc/3", "{\"counter\" : 1,\"tags\" : [\"red\"]}", params, restClient);
		String json05 = common.get("twitter/_doc/3", null, restClient);
		System.out.println(json05);// 只有tags，因为counter的store=false
	}

	/**
	 * <p>
	 * 通过设置store=true/false,可以只存储部分fields,然后检索的时候同样指定stored_fields就会显示相应的fields，没有设置的不会检索到。
	 * 设置方式参见stored_fields.properties
	 * </p>
	 * 
	 * @throws IOException
	 */
	@Test
	public void test_stored_fields() throws IOException {
		String stored_fields_path = "src/main/resources/json/stored_fields.properties";
		common.put("/twitter", TypeConvertUtil.streamToJsonString(new FileInputStream(new File(stored_fields_path))),
				null, InitRestClient.createClient());
		common.put("twitter/_doc/1", "{\"counter\" : 1,\"tags\" : [\"red\"]}", null, InitRestClient.createClient());
		params.clear();
		params.put("stored_fields", "tags,counter");
		String json05 = common.get("twitter/_doc/1", params, restClient);
		System.out.println(json05);// 只有tags，因为counter的store=false
	}

	/**
	 * <p>
	 * 设置显示一部分source,例如：_source_include=*.id&_source_exclude=entities或者_source=*
	 * .id,retweeted
	 * </p>
	 * <p>直接获取source:GET twitter/_doc/1/_source</p>
	 * <p>直接获取source的一部分：GET twitter/_doc/1/_source?_source_include=*.id&_source_exclude=entities'</p>
	 * <p>
	 * 如果有重复_source_exclude会覆盖掉_source_include
	 * </p>
	 * 
	 * @throws IOException
	 */
	@Test
	public void test_source_filter() throws IOException {
		String json = get.get_index_filter(restClient);
		System.out.println(json);
	}

	/**
	 * <p>
	 * realtime=false,ES默认是true（实时的）。但是实时要么是牺牲索引的效率，每次都索引之后都刷新，
	 * 要么就是牺牲查询的效率每次查询之前都进行刷新。无论哪一种，都会让你的性能下降10倍以上，所以只能采取一种折中的方案，每隔n秒自动刷新，
	 * 就是所谓的准实时(near real-time)。
	 * </p>
	 * <p>
	 * 关掉source,设置_source =false
	 * </p>
	 * 
	 * @throws IOException
	 */
	@Test
	public void test_realtime_source() throws IOException {
		String json = get.get_index(restClient);
		System.out.println(json);
	}

}
