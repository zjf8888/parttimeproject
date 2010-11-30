package com.ucai.tool;

import com.ucai.po.Flight;
import com.ucai.tool.po.ToSerializationFlight;

public class FlightFromPage {
	public static ToSerializationFlight setFlightFromPage(Flight flightpo,int page){
		ToSerializationFlight setFilght=new ToSerializationFlight();
		setFilght.setTransId(flightpo.getTransId());
		setFilght.setErrorCode(flightpo.getErrorCode());
		setFilght.setErrorTips(flightpo.getErrorTips());
		setFilght.setStartcity(flightpo.getStartcity());
		setFilght.setEndcity(flightpo.getEndcity());
		setFilght.setStartdate(flightpo.getStartdate());
		int totalNums=flightpo.getSegmentList().size();
		int totalPages = totalNums / 10;
		if (totalNums % 10 > 0) {
			totalPages++;
		}
		setFilght.setTotalNums(totalNums);
		setFilght.setTotalPages(totalPages);
		return setFilght;
	}
}
