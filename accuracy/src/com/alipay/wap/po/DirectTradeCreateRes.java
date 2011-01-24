package com.alipay.wap.po;

import org.nuxeo.common.xmap.annotation.XNode;
import org.nuxeo.common.xmap.annotation.XObject;
/**
 * 交易的RequestToken存储类
 * @author 李卓林
 *
 */
@XObject("direct_trade_create_res")
public class DirectTradeCreateRes {
	 /**
     * 获得的创建交易的RequestToken
     */
    @XNode("request_token")
    private String requestToken;

    public String getRequestToken() {
        return requestToken;
    }
}
