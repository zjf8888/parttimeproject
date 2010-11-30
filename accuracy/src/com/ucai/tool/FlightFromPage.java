package com.ucai.tool;

import java.util.ArrayList;
import java.util.List;

import com.ucai.po.Errinfo;
import com.ucai.po.Flight;
import com.ucai.po.Segment;
import com.ucai.tool.po.ToSerializationFlight;

public class FlightFromPage {
	public static ToSerializationFlight setFlightFromPage(Flight flightpo,
			int pageno) {
		ToSerializationFlight setFilght = new ToSerializationFlight();
		setFilght.setTransId(flightpo.getTransId());
		Errinfo errinfo = new Errinfo();
		if (flightpo.getSegmentList().size() > 0) {
			errinfo.setCode(flightpo.getErrorCode());
			errinfo.setDescription(flightpo.getErrorTips());
		} else {
			errinfo.setCode("104");
			errinfo.setDescription("该页数据不存在!");
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