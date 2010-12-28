package com.alipay.wap.po;

public class ResponseResult {
	/**
	 * 是否调用成功 默认为false 所以在每次调用都必须设置这个值为true；
	 */
	private boolean isSuccess = false;

	/**
	 * 调用的业务成功结果 如果调用失败 这个将是空值
	 */
	private String businessResult;

	/**
	 * 错误信息
	 */
	private ErrorCode errorMessage;

	public boolean isSuccess() {
		return isSuccess;
	}

	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	public String getBusinessResult() {
		return businessResult;
	}

	public void setBusinessResult(String businessResult) {
		this.businessResult = businessResult;
	}

	public ErrorCode getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(ErrorCode errorMessage) {
		this.errorMessage = errorMessage;
	}
}
