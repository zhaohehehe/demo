使用说明：

      1.在application.properties配置数据库信息，执行job.sql文件（例子中给的是mysql版本的sql）;
  
      2.启动main函数DemoApplication.java；
	  
	  3.浏览器访问http://localhost:8080/，查看效果；

PS：
	1.加载quartz.properties配置文件目前报错，未解决；
	2.启动main函数DemoApplication.java项目启动时，DefaultRunJobIfAppStart1.java和DefaultRunJobIfAppStart2.java中的任务会自动执行，
	      因为实现了org.springframework.boot.ApplicationRunner接口；
	3.使用application.yml配置文件有格式要求(不能有tab)，容易报错，使用application.properties文件 
