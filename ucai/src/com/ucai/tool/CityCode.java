package com.ucai.tool;

/**
 * 机场信息 名字跟编号一一对应；航空公司信息，航空公司名字与编号一一对应
 * @author lin
 *
 */
public class CityCode {
	/**
	 * 
	 */
	public static String CityString[][] = {
			{ "北京", "上海", "广州", "深圳", "成都", "杭州", "武汉", "西安", "重庆", "青岛", "长沙",
					"南京", "厦门", "昆明", "大连", "天津", "郑州", "三亚", "济南", "福州" },
			{ "阿克苏", "阿勒泰", "阿里", "安吉", "安康", "安庆", "鞍山", "安顺", "安图", "安溪",
					"安阳", "澳门" },
			{ "百色", "保山", "保定", "保亭", "包头", "宝鸡", "北戴河", "北海", "北京首都", "本溪",
					"蚌埠", "毕节", "滨州", "博鳌", "布尔津" },
			{ "常熟", "长治", "长春", "长乐", "潮州", "潮阳", "巢湖", "常州", "昌黎", "常德", "长沙",
					"澄迈县", "承德", "郴州", "成都", "澄海", "赤峰", "重庆", "滁州", "楚雄", "慈溪" },
			{ "大连", "大理", "丹东", "儋州", "丹阳", "大庆", "大同", "达县", "德化", "登封", "德清",
					"德阳", "定安县", "迪庆", "东莞", "东方", "东山", "东营", "都江堰", "敦煌",
					"都匀", "德州" },
			{ "鄂尔多斯", "峨眉山", "恩平", "恩施" },
			{ "防城港", "奉化", "凤凰县", "福鼎", "福清", "佛山", "阜新", "富蕴", "富阳", "阜阳",
					"福州" },
			{ "赣州", "格尔木", "广州", "广安", "广汉", "广元", "桂林", "贵阳", "贵港" },
			{ "哈尔滨", "海盐", "海拉尔", "海城", "海安", "海宁", "海螺沟", "海口", "哈密", "韩城",
					"邯郸", "杭州", "汉中", "鹤壁", "河池", "合肥", "鹤岗", "黑河", "横店", "衡水",
					"衡阳", "鹤山", "和田", "河源", "贺州", "淮北", "淮南", "黄山", "黄岩", "怀化",
					"淮安", "呼和浩特", "惠安", "惠州", "呼伦贝尔", "湖州" },
			{ "江门", "江阴", "江都", "吉安", "嘉善", "佳木斯", "胶州", "嘉兴", "建德", "江油",
					"嘉峪关", "焦作", "揭阳", "吉林", "即墨", "晋中", "锦州", "金坛", "景德镇",
					"缙云", "荆门", "金华", "井冈山", "晋江", "济南", "济宁", "晋城", "吉首",
					"九华山", "九江", "九寨沟", "酒泉", "句容" },
			{ "开元", "开平", "凯里", "开封", "喀纳斯", "喀什", "克拉玛依", "库车", "库尔勒", "昆山",
					"昆明" },
			{ "廊坊", "兰州", "兰溪", "拉萨", "乐清", "乐山", "辽阳", "辽源", "连云港", "连城",
					"丽江", "临汾", "陵水", "林芝", "临沧", "临海", "临安", "临沂", "丽水",
					"六盘水", "柳州", "溧阳", "龙岩", "龙游", "龙海", "娄底", "漯河", "洛阳",
					"庐山", "泸州" },
			{ "芒市", "满洲里", "茂名", "梅州", "绵阳", "牡丹江" },
			{ "南阳", "南戴河", "南京", "南充", "南通", "南平", "南宁", "南安", "南昌", "内江",
					"宁波", "宁德", "宁海" },
			{ "盘锦", "攀枝花", "蓬莱", "平山县", "萍乡", "平遥", "平湖", "普宁", "莆田", "濮阳" },
			{ "千岛湖", "启东", "且末", "庆阳", "钦州", "秦皇岛", "青岛", "清远", "琼海", "齐齐哈尔",
					"泉港", "泉州", "曲阜", "曲靖", "衢州" },
			{ "任丘", "日喀则", "日照", "瑞安" },
			{ "三门峡", "三河", "三亚", "三明", "邵阳", "上饶", "汕头", "昭通", "沙市", "上海虹桥",
					"上海浦东", "上虞", "韶关", "汕尾", "嵊州", "神木", "沈阳", "深圳", "十堰",
					"石家庄", "石狮", "寿宁", "寿光", "顺德", "思茅", "四平", "泗阳", "松潘",
					"绥化", "绥芬河", "遂宁", "宿迁", "苏州" },
			{ "塔城", "泰州", "泰兴", "太原", "泰安", "泰顺", "台州", "台山", "唐山", "腾冲",
					"天柱山", "天台", "天津", "铁岭", "铜仁", "通辽", "桐乡", "通州",

					"同里", "通什", "桐庐", "通化", "吐鲁番" },

			{ "汪清县", "万县", "万宁", "潍坊", "威海", "温州", "文昌", "温岭", "芜湖",

			"武汉", "乌海", "吴江", "乌兰浩特", "乌鲁木齐", "抚顺", "五台山", "无锡", "武夷山", "武义",
					"梧州", "抚州" },
			{ "湘西", "湘潭", "西安", "咸阳", "香格里拉", "厦门", "象山", "香港", "仙居", "襄樊",
					"西昌", "锡林浩特", "兴义", "新沂", "新昌", "西宁", "忻州", "新乡", "邢台",
					"许昌", "徐州"

			},
			{

			"雅安", "亚布力", "阳江", "延吉", "阳朔", "雁荡山", "阳泉", "烟台", "扬中",

			"延安", "盐城", "延平", "扬州", "宜宾", "宜昌", "宜春", "伊宁", "英德",

			"营口", "银川", "鹰潭", "义乌", "宜兴", "益阳", "仪征", "永嘉", "永康", "永州",

			"永安", "岳阳", "榆林", "玉林", "运城", "云浮", "玉溪", "余姚"

			},
			{ "湛江", "朝阳", "张掖", "肇庆", "张家界", "张家港", "张家口", "镇江", "郑州", "芷江",
					"舟山", "周庄", "中甸", "中山", "株洲", "珠海", "诸暨",

					"淄博", "自贡", "遵义", "富山", "北京南苑", "绍兴", "舟山普陀山", "新泻", "文山",
					"加德满都", "昌都", "梅县", "景洪(西双版纳)"

			} };
	public static String cityMa[][] = {
			{ "PEK", "SHA", "CAN", "SZX", "CTU", "HGH", "WUH", "XIY", "CKG",
					"TAO", "CSX", "NKG", "XMN", "KMG", "DLC", "TSN", "CGO",
					"SYX", "TNA", "FOC" },

			{ "AKU", "AAT", "ALI", "ANJ", "AKA", "AQG", "AOG", "AVA", "ANT",
					"AXI", "ANY", "MAC" },
			{

			"AEB", "BSD", "BDG", "WAN", "BAV", "BJI", "BDH", "BHY", "PEK",
					"BEX", "BFU", "BJE", "BIZ", "BAO", "BEJ"

			},
			{

			"CHS", "CIH", "CGQ", "CLE", "CHE", "CHY", "COH", "CZX", "CHL",
					"CGD", "CSX", "CMA", "CDE", "CHZ", "CTU", "SWB", "CIF",
					"CKG", "CZH", "CUX", "CIX"

			},
			{

			"DLC", "DLU", "DDG", "DAZ", "DAY", "DQG", "DAT", "DAX", "DHU",
					"DFE", "DEQ", "DEY", "DIA", "DIG", "DNG", "DOA", "DSH",
					"DOY", "DJY", "DNH", "DYU", "DEZ"

			},
			{

			"DSN", "EMS", "EPG", "ENH"

			},
			{

			"FGC", "FEH", "FHX", "FUD", "FUQ", "FOH", "BXN", "FYN", "FUY",
					"FUG", "FOC"

			},
			{

			"KOW", "GOQ", "CAN", "GUA", "GHN", "GUY", "KWL", "KWE", "GUG"

			},
			{

			"HRB", "HIY", "HLD", "HCG", "HIA", "HIN", "HLG", "HAK", "HMI",
					"HCN", "HDG", "HGH", "HZG", "HEB", "HCH", "HFE", "HEG",
					"HEH", "HED", "HES", "HGY", "HSO", "HTN", "HEY", "HEZ",
					"HAB", "HNA", "TXN", "HYN", "HHU", "HNB", "HET", "HAN",
					"HUI", "HLB", "HUZ"

			},

			{

			"JMO", "JYN", "JID", "JIA", "JIS", "JMU", "JZO", "JIX", "JDE",
					"JIQ", "JGN", "JIZ", "JIY", "JIL", "JIO", "JOZ", "JNZ",
					"JTA", "JDZ", "JUY", "JIM", "JIH", "JGS", "JJN", "TNA",
					"JIN", "JIC", "JSH", "JHS", "JIU", "JZH", "CHW", "JUR"

			},
			{

			"KYN", "KPG", "KAL", "KFG", "KNS", "KHG", "KRY", "KCA", "KRL",
					"KUS", "KMG"

			},
			{ "LAF", "LHW", "LAX", "LXA", "LEQ", "LSN", "LIZ", "LIY", "LYG",
					"LCX", "LJG", "LIF", "LNS", "LZY", "LNJ", "LIH", "LIA",
					"LYI", "LIS", "LPS", "LZH", "LYO", "LNY", "LOY", "LHA",
					"LOD", "LUH", "LYA", "LUS", "LZO"

			},
			{

			"LUM", "NZH", "MMI", "MEZ", "MIG", "MDG"

			},
			{

			"NNY", "NDH", "NKG", "NAO", "NTG", "NAP", "NNG", "NAN", "KHN",
					"NEJ", "NGB", "NID", "NHI" },

			{

			"PJN", "PZI", "PLI", "PSX", "PIX", "PYO", "PIH", "PLN", "PUT",
					"PUY"

			},
			{ "QDH", "QID", "IQM", "IQN", "QZH", "SHP", "TAO", "QIN", "QIH",
					"NDG", "QGA", "QHU", "QF", "QUJ", "JUZ"

			},
			{ "REQ", "RKZ", "RZO", "RUA" },
			{ "SMX", "SAH", "SYX", "SMI", "SYA", "SHN", "SWA", "ZAT", "SHS",
					"SHA", "PVG", "SHY", "SHG", "SHW", "SHZ", "SMU", "SHE",
					"SZX", "SYN", "SJW", "SIS", "SNI", "SOG", "SHD", "SYM",
					"SIP", "SIY", "SOP", "SUH", "SFH", "SUN", "SUQ", "SZV"

			},
			{

			"TCG", "TIZ", "TIX", "TYN", "TAN", "TIS", "TZH", "TSH", "TNS",
					"TEC", "TZS", "TIT", "TSN", "TLG", "TEN", "TGO", "TOX",
					"TOZ", "TOL", "TOS", "TOU", "TNH", "TLF"

			},

			{

			"WQX", "WXN", "XGL", "WEF", "WEH", "WNZ", "WEC", "WEL", "WHU",
					"WUH", "WUA", "WUJ", "HLH", "URC", "FSN", "WU", "WUX",
					"WUS", "WUY", "WUZ", "FUZ" },
			{ "XXI", "XIT", "XIY", "XYG", "XGLL", "XMN", "XIS", "HKG", "XIJ",
					"XFN", "XIC", "XIL", "ACX", "XIQ", "XCH", "XNN", "XIZ",
					"XIX", "XTA", "XUC", "XUZ" },
			{

			"YAA", "YBL", "YJI", "YNJ", "YAS", "YDS", "YQU", "YNT", "YAZ",
					"ENY", "YNZ", "YPI", "YGH", "YBP", "YIH", "YIC", "YIN",
					"YID", "YKU", "INC", "YIT", "YIW", "YIX", "YIY", "YIZ",
					"YOJ", "YKN", "YZH", "YOA", "YYA", "UYN", "YUL", "YCU",
					"YFU", "YUX", "YUY"

			},
			{

			"ZHA", "CHG", "ZHY", "ZHQ", "DYG", "ZJG", "ZJK", "ZJI", "CGO",
					"HJJ", "PTS", "ZHZ", "ZHD", "ZSO", "ZZU", "ZUH", "ZUJ",
					"ZBO", "ZGG", "ZYI", "TOY", "NAY", "SXG", "HSN", "KIJ",
					"WNH", "KTM", "BPX", "MXZ", "JHG"

			} };

	public static String[] hangKongName = { "国际", "南方", "东方", "上海", "海南", "山东",
			"厦门", "四川", "深圳", "吉祥", "中联", "祥鹏", "奥凯", "成都", "东星", "华夏", "西部",
			"重庆", "东北", "大新华", "天津", "幸福" };
	public static String[] hangKongCode = { "CA", "CZ", "MU", "FM", "HU", "SC",
			"MF", "3U", "ZH", "HO", "KN", "8L", "BK", "EU", "8C", "G5", "PN",
			"OQ", "NS", "CN", "GS", "JR" };

}
