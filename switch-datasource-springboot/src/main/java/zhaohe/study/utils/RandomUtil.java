package zhaohe.study.utils;

import java.util.Calendar;
import java.util.Random;

public class RandomUtil {
	public static int getRandom(int scale) {
		return new Random(Calendar.getInstance().getTimeInMillis()).nextInt(scale);
	}

}
