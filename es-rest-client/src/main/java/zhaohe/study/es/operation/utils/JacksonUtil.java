package zhaohe.study.es.operation.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonUtil {
	/**
	 * 依赖： groupId:com.fasterxml.jackson.core 
	 * artifactId:jackson-databind
	 * latest-version:2.9.7
	 * 
	 * @param beanInstance
	 *            必须实现get/set方法
	 * @return
	 * @throws JsonProcessingException
	 */
	public static byte[] jackson(Object beanInstance) throws JsonProcessingException {
		// instance a json mapper
		ObjectMapper mapper = new ObjectMapper(); // create once, reuse
		// generate json
		byte[] json = mapper.writeValueAsBytes(beanInstance);
		System.out.println(new String(json));
		return json;
	}
}
