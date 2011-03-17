package com.ucai.tool;

/**
 * 工具类
 * @author lin
 *
 */
public class MethodTool {
	/**
	 * 查询航空公司方法
	 * 
	 * @param s 提交的查询关键字
	 * @return 航空公司名字
	 */
	public static String searchName(String s) {
		String hangKongCode[] = CityCode.hangKongCode;
		String hangKongName[] = CityCode.hangKongName;
		for (int i = 0; i < hangKongCode.length; i++) {
			String code = hangKongCode[i];
			if (code.indexOf(s) != -1) {
				String name = hangKongName[i] + "航空";
				return name;

			}
		}
		return s;
	}
}
