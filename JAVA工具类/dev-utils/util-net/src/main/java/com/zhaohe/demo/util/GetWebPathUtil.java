package com.zhaohe.demo.util;

import java.net.MalformedURLException;
import java.net.URL;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;

public class GetWebPathUtil {
	public static String getPathByServletContext(ServletContext context,String targetSource){
		System.out.println(GetWebPathUtil.class.getResource("/").getPath());
		System.out.println(GetWebPathUtil.class.getResourceAsStream("/"));
		System.out.println(GetWebPathUtil.class.getResourceAsStream("/test.properties"));
		System.out.println(GetWebPathUtil.class.getResourceAsStream("/test1.properties"));
		URL url = null;
		try {
			url =context.getResource("/");
			/*/D:/zhaohe/workspace/workspace_JavaW/zhaohe/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/util-net/
			/D:/zhaohe/workspace/workspace_JavaW/zhaohe/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/util-net/
			file
			null*/
			//url =context.getResource(targetSource);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		System.out.println(url.getFile());
		System.out.println(url.getPath());
		System.out.println(url.getProtocol());
		System.out.println(url.getQuery());
		//tomcat
		/*/D:/zhaohe/workspace/workspace_JavaW/zhaohe/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/util-net/WEB-INF/
		/D:/zhaohe/workspace/workspace_JavaW/zhaohe/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/util-net/WEB-INF/
		file
		null*/
		//插件
		/*/localhost/dp/WEB-INF
		/localhost/dp/WEB-INF
		jndi
		null*/
		//tomcat path下
		/*/D:/zhaohe/workspace/workspace_JavaW/zhaohe/dev-utils/util-net/target/util-net/WEB-INF/
		/D:/zhaohe/workspace/workspace_JavaW/zhaohe/dev-utils/util-net/target/util-net/WEB-INF/
		file
		null*/
		return url.getFile();
		
	}
	private static final String  PROTOCOL_JNDI = "jndi";
	public static String check_path_if_terminator_exist(String path){
		int len = path.length();
		int index = path.lastIndexOf("/")==len-1?path.lastIndexOf("/"):path.lastIndexOf("\\");
		if(index!=len-1){
			path = path+"/";
		}
		return path;
	}
	public static String handle_separator(String path){
		path = path.replaceAll("//|\\\\/", "/");
		return path;
	}
	/**
	 * 
	 * @Description: TODO()
	 * @param context 应用上下文
	 * @param targetSource 资源路径，如/WEB-INF/XXX
	 * @return
	 * @return String
	 */
	public static String obtain_path_through_servletContext_and_targetSource(ServletContext context,String targetSource){
		String res = "";
		try {
			URL url = context.getResource(targetSource);
			if(PROTOCOL_JNDI.equals(url.getProtocol())){
				res = check_path_if_terminator_exist(context.getRealPath("/"))+targetSource;
			}else{
				res = url.getFile();
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}catch(NullPointerException e){
			res = check_path_if_terminator_exist(context.getRealPath("/"))+targetSource;
		}
		return handle_separator(res);
	}
	public static String obtain_path_through_request_and_targetSource(ServletRequest request,String targetSource){
		ServletContext context = request.getServletContext();
		return obtain_path_through_servletContext_and_targetSource(context, targetSource);
	}
	public static void main(String[] args) throws MalformedURLException {
		String path = "e:\\aa";
		System.out.println(check_path_if_terminator_exist(path));
		path = "/home/tomcat/tomcat7-deployment/webapps/webTest//WEB-INF/config/";
		System.out.println(handle_separator(path));
		path = "D:\\apache-tomcat-7.0.42\\webapps\\webTest\\/WEB-INF/config/";
		System.out.println(handle_separator(path));
	}
}

