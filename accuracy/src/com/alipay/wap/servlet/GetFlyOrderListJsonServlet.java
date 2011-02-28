package com.alipay.wap.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import com.ucai.po.ResultOrder;
import com.ucai.webservices.ucai.SetOrderImp;

/**
 * 通过订单号获取订单 json版,<br>
 * 与GetFlyOrderListServlet类似，区别只是返回的文件类型不同
 * 主要是提供给客户端显示订单具体信息使用，<br>
 * 该方法的调用路径为：/getFlyOrderListJsonServlet<br>
 * 需要的参数为<br>
 * forderid:订单编号
 * 
 * @author 李卓林
 * @see GetFlyOrderListServlet
 * 
 */
public class GetFlyOrderListJsonServlet extends HttpServlet {
	/**
	 * 返回的文件制式
	 */
	private static final String CONTENT_TYPE = "text/xml;charset=UTF-8";

	/**
	 * 序列化时为了保持版本的兼容性
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 初始化方法,无需调用，系统自动完成
	 */
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

	/**
	 * 获取订单接口方法，具体是通过doPost(HttpServletRequest, HttpServletResponse)实现
	 * 
	 * @see #doPost(HttpServletRequest, HttpServletResponse)
	 */
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * 获取订单接口方法,该方法为此类的核心方法，获取具体订单信息是通过远程方法实现，具体是通过SetOrderImp.getFlyOrderList(String,
	 * String)实现
	 * 
	 * @see SetOrderImp#getFlyOrderList(String, String)
	 */
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		response.setContentType(CONTENT_TYPE);
		response.setCharacterEncoding("UTF-8");
		try {
			String forderid = request.getParameter("forderid");
			SetOrderImp orderImp = new SetOrderImp();
			ResultOrder resultOrder = orderImp.getFlyOrderList(forderid, "");
			JSONObject jsonObject = JSONObject.fromObject(resultOrder);
			String json = jsonObject.toString();
			System.out.println(json);
			PrintWriter pw = response.getWriter();
			pw.write(json);
			pw.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
