package com.ucai.tool;

import java.util.ArrayList;
import java.util.List;

import com.ucai.po.Errinfo;
import com.ucai.po.Flight;
import com.ucai.po.Segment;
import com.ucai.tool.po.ToSerializationFlight;

/**
 * 查询显示工具类
 * 
 * @author lin
 * 
 */
public class FlightFromPage {
	/**
	 * 通过已查询到的信息对象，打包成相应页面的信息对象
	 * 
	 * @param flightpo航班信息对象
	 * @param pageno页码
	 * @return返回的带页码的航班信息
	 */
	public static ToSerializationFlight setFlightFromPage(Flight flightpo,
			int pageno) {
		ToSerializationFlight setFilght = new ToSerializationFlight();
		setFilght.setTransId(flightpo.getTransId());
		Errinfo errinfo = new Errinfo();
		//判断信息是否合法
		if (flightpo.getSegmentList() != null
				&& flightpo.getSegmentList().size() > 0) {
			errinfo.setCode(flightpo.getErrorCode());
			errinfo.setDescription(flightpo.getErrorTips());
		} else {
			errinfo.setCode("104");
			errinfo.setDescription("该页数据不存在!");
			setFilght.setErrinfo(errinfo);
			return setFilght;
		}
		setFilght.setErrinfo(errinfo);
		setFilght.setStartcity(flightpo.getStartcity());
		setFilght.setEndcity(flightpo.getEndcity());
		setFilght.setStartdate(flightpo.getStartdate());
		int totalNums = flightpo.getSegmentList().size();
		int totalPages = totalNums / 10;
		if (totalNums % 10 > 0) {
			totalPages++;
		}
		setFilght.setTotalNums(totalNums);
		setFilght.setTotalPages(totalPages);
		setFilght.setPageNo(pageno);
		int page = pageno - 1;
		//打包航班信息，不超过十班
		List<Segment> segmentList = new ArrayList<Segment>();
		if (page * 10 + 10 < totalNums) {
			for (int i = page * 10; i < page * 10 + 10; i++) {
				Segment segment = flightpo.getSegmentList().get(i);
				segmentList.add(segment);
			}
		} else {
			for (int i = page * 10; i < totalNums; i++) {
				Segment segment = flightpo.getSegmentList().get(i);
				segmentList.add(segment);
			}
		}
		setFilght.setSegmentList(segmentList);
		return setFilght;
	}
}
