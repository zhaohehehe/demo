<%@ page language="java" import="java.util.*" contentType="text/html;charset=UTF-8"%>
<html>
<body>
<h2>Hello World!</h2>
<h3>本地部署dubbo服务步骤：<span>核心：dubbo-consumer模块不需要依赖dubbo-provider模块也可以调用其service。</span></h3>
<ol>
	<li><p>提供服务：启动dubbo-provider模块的测试main函数或者在tomcat中运行，即可提供服务；</p></li>
	<li><p>调用服务：在tomcat中启动dubbo-consumer模块浏览器访问或者启动该模块的测试main函数，即可提供服务。</p></li>
</ol>
<a href="ConsumerServlet">测试dubbo,调用service</a>
</body>
</html>
