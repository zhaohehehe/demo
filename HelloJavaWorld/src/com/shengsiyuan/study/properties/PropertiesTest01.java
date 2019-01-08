package com.shengsiyuan.study.properties;

import java.util.Iterator;
import java.util.Properties;
import java.util.Set;
/**
 * 通过Properties得到系统环境变量
 * @author zhaohe
 *
 */
public class PropertiesTest01 {

	public static void main(String[] args) {
		//Properties继承自Hashtable
		Properties p=System.getProperties();
		Set set=p.keySet();
		for(Iterator it=set.iterator();it.hasNext();){
			String key=(String) it.next();
			String value=p.getProperty(key);
			System.out.println(key+"="+value);
		}
	}
}
