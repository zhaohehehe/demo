package zhaohe.study.service;

import net.sf.json.JSONArray;

public interface WelcomeDataService {
	JSONArray countTables();
	
	JSONArray countViews();
	
	JSONArray countOthers();


}
