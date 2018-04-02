package com.pk10.active.console.common.bean;


public enum CodeMsg {
	/**
	 * 通用模块
	 */
	SUCCESS("0","操作成功"),
	SERVER_ERROR("500100","服务繁忙"),
	PARAM_ERROR("500101","参数有误"),
	
	
	/**
	 * 登录注册
	 */
	LOGIN_ERROR("500200","登录失败"),
	USER_NOT_EXIST("500201","用户不存在"),
	MOBILE_EXIST("500202", "手机号已存在"),
	UNAUTHORIZED("500203", "未授权"),
	UNCERTIFICATED("500204", "未认证"),
	
	/**
	 * 投注
	 */
	BET_CLOSED("500300", "已封盘"),
	BET_INSUFFICIENT_BALANCE("500301", "余额不足"),
	
	/**
	 * 用户中心
	 */
	WITHDRAW_INSUFFICIENT_BALANCE("500400", "余额不足"),
	OLD_PWD_WRONG("500401", "旧密码错误"),
	
	
	
	
	DEFAUT_ERROR("-1","操作失败");
	
	private final String code;
	private String msg;
	
	private CodeMsg(String code, String msg){
		this.code = code;
		this.msg = msg;
	}

	public String getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}
	public CodeMsg setMsg(String msg) {
		this.msg = msg;
		return this;
	}

	public CodeMsg fillMsg(Object... msgs) {
		for (int i = 0; i < msgs.length; i++) {
			this.msg +=msgs[i]+",";
			
		}
	    return this;
	}

	
}