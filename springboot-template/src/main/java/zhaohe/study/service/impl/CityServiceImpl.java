package zhaohe.study.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import zhaohe.study.dao.CityDao;
import zhaohe.study.service.CityService;

@Service
public class CityServiceImpl implements CityService {
	
	public CityServiceImpl() {
		System.out.println("================CityServiceImpl==============================");
	}
	@Autowired
	CityDao cityDao;

	@Override
	public int selectCount(String countryCode) {
		return cityDao.selectCount(countryCode);
	}

}
