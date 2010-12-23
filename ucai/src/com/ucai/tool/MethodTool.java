package com.ucai.tool;

public class MethodTool {
	/**
	 * 查询航空公司方法
	 * 
	 * @param s
	 * @return
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
