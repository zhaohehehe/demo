package zhaohe.study.service.impl;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import zhaohe.study.datasource.annotation.DataSource;
import zhaohe.study.service.TestService;

public class TestServiceImpl extends JdbcDaoSupport implements TestService {

	@Override
	@DataSource(name = DataSource.dataSource2)
	public void save() {
		String SQL = "select '123'";
		this.getJdbcTemplate().execute(SQL);
	}

}
