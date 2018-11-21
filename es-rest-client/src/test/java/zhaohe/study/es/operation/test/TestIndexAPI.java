package zhaohe.study.es.operation.test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.elasticsearch.client.RestClient;
import org.junit.Before;
import org.junit.Test;

import zhaohe.study.es.client.init.InitRestClient;
import zhaohe.study.es.operation.singleDocument.CommonAPI;
import zhaohe.study.es.operation.singleDocument.IndexAPI;

public class TestIndexAPI {
	public static RestClient restClient = null;
	private static Map<String, String> params = null;
	private static IndexAPI index = null;
	private static CommonAPI common = null;

	@Before
	public void setup() {
		restClient = InitRestClient.createClient();
		if (params == null) {
			params = new HashMap<>();
		}
		if (index == null) {
			index = new IndexAPI();
		}
		if (common == null) {
			common = new CommonAPI();
		}
	}

	/**
	 * <p>
	 * 创建索引文档，默认版本号从1开始递增
	 * </p>
	 * 
	 * @throws IOException
	 */
	@Test
	public void test_index() throws IOException {
		index.put_index(restClient);
	}

	/**
	 * <p>
	 * 创建带有版本号的索引，需要将version_type设置为external，否则不能指定版本号，会有版本冲突，开启该功能后，版本号最小可以为0，
	 * 只有新的版本号大于当前版本号才会更新。
	 * </p>
	 * <p>
	 * A good example of a use case for versioning is performing a transactional
	 * read-then-update.
	 * </p>
	 * <p>
	 * version_type可选值为[internal|(external/external_gt)|external_gte]
	 * </p>
	 * 
	 * @throws IOException
	 */
	@Test
	public void test_index_append_version() throws IOException {
		index.put_index_append_version(restClient);
	}

	/**
	 * <p>
	 * 设置op_type=create(或者URL中带有_create，例如twitter/_doc/1/_create)，如果某个文档已经存在，
	 * 则创建失败
	 * </p>
	 * 
	 * @throws IOException
	 */
	@Test
	public void test_index_append_op_type() throws IOException {
	}

	/**
	 * <p>
	 * 自动生成id，必须是POST方法，不能是PUT
	 * </p>
	 * 
	 * @throws IOException
	 */
	@Test
	public void test_index_append_auto_id() throws IOException {
		index.put_index_append_auto_id(restClient);
	}
}
