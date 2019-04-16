package com.test.dubbox.consumer;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.alibaba.fastjson.JSONObject;
import com.test.dubbox.api.UserService;
import com.test.dubbox.entity.User;

/**
 * Spring获取Bean的几种方式
 * 
 * @author ZH
 *
 */
public class ConsumerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final Logger log = LoggerFactory.getLogger(ConsumerServlet.class);

	@Autowired
	private UserService userService;

	public ConsumerServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		log.info("====================doGet()==============================");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<HTML>");
		out.println("<BODY>");
		/**
		 * 1.@Autowired 注解获取service 参见com.dubbox.ConsumerTest main函数
		 */
		try {
			User user2 = userService.getUserByPhone("1233523452");
			log.info("============================================================");
			log.info(JSONObject.toJSONString(user2));
			out.println(JSONObject.toJSONString(user2));
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		/**
		 * 2.Spring提供的工具类获取ApplicationContext对象 ，获取bean失败时抛出异常
		 */
		// 等价于ServletContext sc = this.getServletContext();
		ServletContext sc = request.getSession().getServletContext();
		ApplicationContext ac1 = WebApplicationContextUtils.getRequiredWebApplicationContext(sc);
		UserService userService2 = (UserService) ac1.getBean("userService");
		try {
			User user2 = userService2.getUserByPhone("1233523452");
			log.info("============================================================");
			log.info(JSONObject.toJSONString(user2));
			out.println(JSONObject.toJSONString(user2));
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		/**
		 * 3.Spring提供的工具类获取ApplicationContext对象 ，获取bean失败时返回null
		 */
		ApplicationContext ac2 = WebApplicationContextUtils.getWebApplicationContext(sc);
		UserService userService3 = (UserService) ac2.getBean("userService");
		try {
			User user3 = userService3.getUserByPhone("1233523452");
			log.info("============================================================");
			log.info(JSONObject.toJSONString(user3));
			out.println(JSONObject.toJSONString(user3));
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		/**
		 * 4.Spring提供的工具类获取ApplicationContext对象 ，获取bean
		 */
		WebApplicationContext wac = (WebApplicationContext) sc
				.getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
		UserService userService4 = (UserService) wac.getBean("userService");
		try {
			User user4 = userService4.getUserByPhone("1233523452");
			log.info("============================================================");
			log.info(JSONObject.toJSONString(user4));
			out.println(JSONObject.toJSONString(user4));
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.info("==============doPost==============================================");
		doGet(request, response);
	}

	@Override
	public void destroy() {
		log.info("==============destroy()=====================================");
		super.destroy();
	}

}
