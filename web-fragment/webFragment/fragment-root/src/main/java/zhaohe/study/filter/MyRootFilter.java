package zhaohe.study.filter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class MyRootFilter implements Filter {
	private String moduleName;

	public MyRootFilter() {
		super();
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@SuppressWarnings("unchecked")
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		Map<String, Object> jarPathsMap = new LinkedHashMap<String, Object>();
		jarPathsMap.putAll((Map<String, String>) request.getServletContext().getAttribute("jars"));
		//注意：脚本名称不能重复，否则仅依靠脚本路径不能获取定位一条单独的SQL文件
		System.out.println("/sql/"+this.moduleName+".sql");
		InputStream is = this.getClass().getResourceAsStream("/sql/"+this.moduleName+".sql");
		BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
		System.out.println(
				"chain.doFilter(request, response)之前：" + moduleName + ":/sql/"+moduleName+".sql" + ",读取内容:" + reader.readLine());
		//注意：chain.doFilter(request, response)之前的打印顺序和之后的打印顺序正好是相反的
		chain.doFilter(request, response);
		is = this.getClass().getResourceAsStream("/sql/"+this.moduleName+".sql");
		reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
		System.out.println(
				"chain.doFilter(request, response)之后：" + moduleName + ":/sql/"+moduleName+".sql" + ",读取内容:" + reader.readLine());
		is.close();
		reader.close();
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

}
