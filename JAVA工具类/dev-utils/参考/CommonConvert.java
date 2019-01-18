

import java.sql.Clob;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CommonConvert {
	
	private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	private SimpleDateFormat formatDay = new SimpleDateFormat("yyyy-MM-dd");
	private SimpleDateFormat formatTime = new SimpleDateFormat("HH:mm");

	//日期时间与String转换
	public String date2String(Date time) {
		if(time != null)
			return "\""+format.format(time)+"\"";
		else
			return "";
	}
	public String day2String(Date time) {
		if(time != null){
			return "\""+formatDay.format(time)+"\"";
		}else{
			return "";
		}
	}
	public String time2String(Date time) {
		if(time != null){
			return "\""+formatTime.format(time)+"\"";
		}else{
			return "";
		}
	}
	public Date string2Date(String value,String cacheKey){
		Date date = null;
		if(value != null){
			value = value.substring(1, value.length()-1);
			date = StringUtils.parseDateWithFormat(value,"yyyy-MM-dd hh:mm:ss");
		}
		return date;
	}
	public Date string2Day(String value,String cacheKey){
		Date date = null;
		if(value != null){
			value = value.substring(1, value.length()-1);
			date = StringUtils.parseDateWithFormat(value,"yyyy-MM-dd");
		}
		return date;
	}
	public Date string2Time(String value,String cacheKey){
		Date date = null;
		if(value != null){
			value = value.substring(1, value.length()-1);
			String[] obj = value.split(":");
			date = new Date();
			date.setHours(Integer.parseInt(obj[0]));
			date.setMinutes(Integer.parseInt(obj[1]));
		}
		return date;
	}

	
	
	//Oracle中的Clob与String类型转换
	public String clob2String(Clob clob){
		String reslut = null;
		if(clob!=null){
			try {
				reslut = clob.getSubString(1, (int)clob.length()).trim();
				if(reslut.length()>32767){
					while (reslut.indexOf("  ")>-1){
						reslut = reslut.replaceAll("  ", " ");
					}
					reslut = reslut.replaceAll("\\n\\s", "\n");
					if(reslut.length()>32767){
//						"The maximum length of cell contents (text) is 32,767 characters"
						reslut = "文本内容超出了EXCEL单元格的最大限制32767";
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return reslut;
	}
	//mysql中  导出存储过程、函数、视图的过程sql转换
	public String clob2String(String str){
		return str;
	}

	public String string2(String value){
		return value;
	}
	
}
