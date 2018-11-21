package zhaohe.study.datasource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

//https://blog.csdn.net/xiaosheng_papa/article/details/80218006
//https://blog.csdn.net/neosmith/article/details/61202084
//https://blog.csdn.net/twomr/article/details/79137056
//https://blog.csdn.net/u013360850/article/details/78861442
public class DynamicRoutingDataSource extends AbstractRoutingDataSource {
	private static final Logger log = LoggerFactory.getLogger(DynamicRoutingDataSource.class);

	@Override
	protected Object determineCurrentLookupKey() {
		log.debug("当前数据源为{}", DynamicDataSourceContextHolder.getDataSourceKey());

		return DynamicDataSourceContextHolder.getDataSourceKey();
	}

}
