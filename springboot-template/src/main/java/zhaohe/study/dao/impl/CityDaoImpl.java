package zhaohe.study.dao.impl;

import org.springframework.stereotype.Repository;

import zhaohe.study.dao.AbstractDao;
import zhaohe.study.dao.CityDao;

@Repository
public class CityDaoImpl extends AbstractDao implements CityDao {
	
	public CityDaoImpl() {
		System.out.println("================CityDaoImpl==============================");
	}

	@Override
	public int selectCount(String countryCode) {
		String sql = "select count(1) from city where countryCode = ?";
		return super.getJdbcTemplate().queryForObject(sql, Integer.class, countryCode);
	}

}
