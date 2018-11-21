package zhaohe.study.controller.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import zhaohe.study.mapper.WelcomeDataMapper;
import zhaohe.study.utils.RandomUtil;

@Service
public class WelcomeDataService {

	private static final Logger log = LoggerFactory.getLogger(WelcomeDataService.class);

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@Autowired
	WelcomeDataMapper welcomeDataMapper;
	/**
	 * 表
	 * @Async
	 * @return
	 */
	public JSONArray countTables() {
		log.info("The time is now {}", dateFormat.format(Calendar.getInstance().getTime()));
		JSONArray returnInfo = new JSONArray();
		returnInfo.add(new JSONObject().accumulate("data",
				welcomeDataMapper.queryTableCount() + RandomUtil.getRandom(100)));
		return returnInfo;
	}
	
	/**
	 * 视图
	 * @Async
	 * @return
	 */
	public JSONArray countViews() {
		log.info("The time is now {}", dateFormat.format(Calendar.getInstance().getTime()));
		JSONArray returnInfo = new JSONArray();
		returnInfo.add(new JSONObject().accumulate("data",
				welcomeDataMapper.queryViewCount() + RandomUtil.getRandom(100)));
		return returnInfo;
	}

	public JSONArray countOthers() {
		log.info("The time is now {}", dateFormat.format(Calendar.getInstance().getTime()));
		JSONArray returnInfo = new JSONArray();
		returnInfo.add(new JSONObject().accumulate("data",RandomUtil.getRandom(500)));
		return returnInfo;
	}

}
