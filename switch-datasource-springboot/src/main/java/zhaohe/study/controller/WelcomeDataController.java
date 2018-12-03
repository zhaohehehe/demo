package zhaohe.study.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import net.sf.json.JSONArray;
import zhaohe.study.service.WelcomeDataService;


@Controller
//@RequestMapping("/")
public class WelcomeDataController {
	@Autowired
	WelcomeDataService welcomeDataService;

	@RequestMapping(value = "/{code}/count", method = RequestMethod.GET)
	public @ResponseBody JSONArray count(@PathVariable(value = "code", required = true) String code) {
		switch (code) {
		case "table":
			return welcomeDataService.countTables();
		case "view":
			return welcomeDataService.countViews();
		default:
			return welcomeDataService.countOthers();
		}
	}

}
