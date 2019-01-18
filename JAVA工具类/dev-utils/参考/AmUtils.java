

import java.util.List;

import DaoSupportUtil;

public class AmUtils {

	private AmUtils() {
	}

	public static class AmUtilsHolder {
		private static final AmUtils INSTANCE = new AmUtils();
	}

	public AmUtils getInstance() {
		return AmUtilsHolder.INSTANCE;
	}
	
	public static String getPath(String pathId,  DaoSupportUtil daoSupportUtil){
		StringBuilder builder = new StringBuilder("(");
		String[] pathArray = pathId.split("/");
		for (String id : pathArray) {
			builder.append("'" + id + "',");
		}
		if (builder.length() != 1) {
			String sql = "select amObject.name from AmObject amObject where amObject.oid in " + builder.substring(0, builder.length() - 1) + ") order by amObject.oid";
			List list = daoSupportUtil.findBySql(sql);
			builder.setLength(0);
			int length = list.size();
			for (int i = 0; i < length - 1; i++) {
				String name = list.get(i).toString();
				builder.append(name + "->");
			}
			String name = list.get(length - 1).toString();
			builder.append(name);
		}
		return builder.toString();
	}
}
