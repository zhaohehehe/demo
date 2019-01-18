

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

public class BeafGlobals {
	private final static Logger log = Logger.getLogger(BeafGlobals.class);
	private final static String defaultConfig = "beaf";

	static ResourceBundle resource;
	//系统固定目录输出
	//value是相对于webAppRootRealPath时，需要改变的目录
	static Map paramsMap=new HashMap();

	static {
		try {
			String path = BeafGlobals.class.getResource("/").getPath();
			if(path.indexOf("app/WEB-INF")<0){
				paramsMap.put("webAppRootRealPath", "");
				paramsMap.put("reportRootPath", "templ");
				paramsMap.put("templateDir", "generater/templates");
				paramsMap.put("srcOutput", "/src");
				paramsMap.put("standardOutput", "");
				paramsMap.put("catalogOutput", "");
			}else{
				paramsMap.put("webAppRootRealPath", "app/");
				paramsMap.put("reportRootPath", "app/templ");
				paramsMap.put("templateDir", "app/generater/templates");
				paramsMap.put("srcOutput", "/src");
				paramsMap.put("standardOutput", "app/");
				paramsMap.put("catalogOutput", "app/");
			}
			
			
			resource = ResourceBundle.getBundle(defaultConfig);
		} catch (Exception e) {
			log.error(e);
		}
	}

	public static int getIntParameter(String parameterName) {
		String param = getParameter(parameterName, "utf-8");
		return new Integer(param).intValue();
	}

	public static long getLongParameter(String parameterName) {
		String param = getParameter(parameterName, "utf-8");
		return new Long(param).longValue();
	}

	public static String getParameter(String parameterName) {
		return getParameter(parameterName, "utf-8");
	}

	public static String getParameter(String parameterName, String enCode) {
		try {
			String s=null;
			if((s=dealSpecialParam(parameterName))==null)
				s= new String(resource.getString(parameterName).getBytes(
					"utf-8"), enCode);
			return s;
		} catch (Exception e) {
			return "";
		}

	}
	
	private static String dealSpecialParam(String parameterName){
		String paramValue=null;
		if(null!=parameterName && (paramValue=(String)paramsMap.get(parameterName))!=null){
			return getWebAppRootRealPath()+paramValue;
		}
		return null;
	}

	private static String getWebAppRootRealPath() {
		String path = BeafGlobals.class.getResource("/").getPath();
		if(path.indexOf("app/WEB-INF")<0)
			return path.substring(0, path.indexOf("WEB-INF"));
		else
			return path.substring(0, path.indexOf("app/WEB-INF"));
	}

}
