package zhaohe.study.datasource;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import com.alibaba.druid.pool.DruidDataSource;

@Configuration
public class DynamicDataSourceConfiguration {

	@Autowired
	Environment env;

	/**
	 * 使用DruidDataSource，只是配置方式不同，依赖注入Environment env，实际上和bmm和asset都是一样的
	 * @return
	 */
	@Bean(name = "mydb")
	public DataSource slaveMydb() {
		DruidDataSource dataSource = new DruidDataSource();
		dataSource.setUrl(env.getProperty("spring.datasource.mydb.url"));
		dataSource.setUsername(env.getProperty("spring.datasource.mydb.username"));
		dataSource.setPassword(env.getProperty("spring.datasource.mydb.password"));
		dataSource.setDriverClassName(env.getProperty("spring.datasource.mydb.driver-class-name"));
		return dataSource;
	}

	/**
	 * @ConfigurationProperties 注解用于从 application.properties 文件中读取配置，为 Bean 设置属性
	 * @Primary注解用于设置默认的数据源
	 * @return
	 */
	@Bean
	@Primary
	@ConfigurationProperties(prefix = "spring.datasource")
	public DataSource masterAsset() {
		// return DruidDataSourceBuilder.create().build();
		return DataSourceBuilder.create().build();
	}

	@Bean
	@ConfigurationProperties(prefix = "spring.datasource.bmm")
	public DataSource slaveBmm() {
		// return DruidDataSourceBuilder.create().build();
		return DataSourceBuilder.create().build();
	}

	/**
	 * 动态数据源注册
	 * 
	 * @return DataSource
	 */
	@Bean
	public DataSource dynamicDataSource() {
		DynamicRoutingDataSource dynamicRoutingDataSource = new DynamicRoutingDataSource();
		Map<Object, Object> dataSourceMap = new HashMap<>(2);
		dataSourceMap.put(DataSourceKey.MASTER_ASSET, masterAsset());
		dataSourceMap.put(DataSourceKey.SLAVE_BMM, slaveBmm());
		// 将 MASTER_ASSET数据源作为默认指定的数据源
		dynamicRoutingDataSource.setDefaultTargetDataSource(masterAsset());
		// 将 MASTER_ASSET和 SLAVE_BMM数据源作为指定的数据源
		dynamicRoutingDataSource.setTargetDataSources(dataSourceMap);
		// 将master数据源的 key放到数据源上下文的 key集合中，用于切换时判断数据源是否有效
		DynamicDataSourceContextHolder.dataSourceKeys.addAll(dataSourceMap.keySet());
		// 将 slave数据源的 key放在集合中，用于轮循
		DynamicDataSourceContextHolder.slaveDataSourceKeys.addAll(dataSourceMap.keySet());
		DynamicDataSourceContextHolder.slaveDataSourceKeys.remove(DataSourceKey.MASTER_ASSET);
		return dynamicRoutingDataSource;
	}

	/**
	 * 配置 mybatis SqlSessionFactoryBean
	 * 
	 * @ConfigurationProperties 在这里是为了将 MyBatis的
	 *                          mapper位置和持久层接口的别名设置到Bean的属性中，否则将会产生 invalid bond
	 *                          statement异常。
	 * @return SqlSessionFactoryBean
	 */
	@Bean
	@ConfigurationProperties(prefix = "mybatis")
	public SqlSessionFactoryBean sqlSessionFactory() {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		// 设置为mapper文件路径
		// sqlSessionFactoryBean.setMapperLocations(new
		// PathMatchingResourcePatternResolver().getResources("classpath:mapper/*.xml"));
		// 切换配置为动态数据源，否则不能实现切换
		sqlSessionFactoryBean.setDataSource(dynamicDataSource());
		return sqlSessionFactoryBean;
	}

	/*
	 * @Bean public SqlSessionTemplate sqlSessionTemplate() throws Exception {
	 * return new SqlSessionTemplate(sqlSessionFactory()); }
	 */

	/**
	 * 注入 DataSourceTransactionManager 用于事务管理
	 * 
	 * @return 事务管理实例
	 */
	@Bean
	public PlatformTransactionManager platformTransactionManager() {
		return new DataSourceTransactionManager(dynamicDataSource());
	}

}
