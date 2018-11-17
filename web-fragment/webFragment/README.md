使用说明：

      1.cd 到/webFragment路径；
  
      2.执行 mvn clean install 安装所有模块；
  
      3.切换到/webFragment/fragment-web路径 执行 mvn tomcat7:run，使用插件启动应用(http://localhost:8080/fragment)；
  
      4.点击测试查看后台打印情况（每个fragment模块中的文件按照顺序打印）；
  
PS：

      1./src/main/resources/META-INF/resources/*下的文件打成JAR包之后，其他JAR包可以访问到，相当于是classpath；
  
      2./src/main/resources/*和/src/main/java/*都会打到classpath；
  
      3.web-fragment.xml文件存储路径为：src/main/resources/META-INF/web-fragment.xml；
  
      4.web.xml中的metadata-complete属性要设置成false,否则运行时不会去扫描web-fragment.xml；
  

