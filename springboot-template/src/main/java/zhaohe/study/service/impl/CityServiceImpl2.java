package zhaohe.study.service.impl;

import zhaohe.study.config.SpringContextUtil;
import zhaohe.study.dao.CityDao;
import zhaohe.study.service.CityService;

public class CityServiceImpl2 implements CityService {
	
	public CityServiceImpl2() {
		System.out.println("================CityServiceImpl2==============================");
	}
	
	CityDao cityDao =(CityDao) SpringContextUtil.getBean("cityDaoImpl");

	@Override
	public int selectCount(String countryCode) {
		return cityDao.selectCount(countryCode);
	}

}
