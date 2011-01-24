package com.alipay.wap.po;

import java.util.List;

import com.ucai.po.ResultOrder;
/**
 * 存储返回结果列表
 * @author 李卓林
 *
 */
public class ResultPo {
	List<ResultOrder> resultList;

	public List<ResultOrder> getResultList() {
		return resultList;
	}

	public void setResultList(List<ResultOrder> resultList) {
		this.resultList = resultList;
	}
}
