package zhaohe.study.es.operation.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TypeConvertUtil {
	/**
	 * 流转为字符串
	 * 参考blog:https://blog.csdn.net/lmy86263/article/details/60479350
	 * @param is
	 * @return
	 * @throws IOException
	 */
	public static String streamToJsonString(InputStream is) throws IOException {
		StringBuilder sb = new StringBuilder();
		String line;
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		while ((line = br.readLine()) != null) {
			sb.append(line);
		}
		String str = sb.toString();
		return str;
	}
}
