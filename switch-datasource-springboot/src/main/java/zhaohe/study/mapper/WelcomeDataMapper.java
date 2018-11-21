package zhaohe.study.mapper;

import org.apache.ibatis.annotations.Mapper;

import zhaohe.study.datasource.DataSourceAnno;
import zhaohe.study.datasource.DataSourceKey;

@Mapper
public interface WelcomeDataMapper {
	
	@DataSourceAnno(DataSourceKey.SLAVE_BMM)
	int queryTableCount();

	int queryViewCount();

}
