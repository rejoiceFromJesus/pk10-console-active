package com.pk10.active.console.common.bean;


public enum CodeMsg {
	/**
	 * 通用模块
	 */
	SUCCESS("0","操作成功"),
	SERVER_ERROR("500100","internal server error"),
	PARAM_ERROR("500101","param error"),
	
	
	/**
	 * 登录注册
	 */
	LOGIN_ERROR("500200","login error"),
	USER_NOT_EXIST("500201","user not exist"),
	MOBILE_EXIST("500202", "mobile already exists"),
	
	/**
	 * 投注
	 */
	BET_CLOSED("500300", "已封盘"),
	BET_INSUFFICIENT_BALANCE("500301", "insufficient balance"),
	
	/**
	 * 用户中心
	 */
	WITHDRAW_INSUFFICIENT_BALANCE("500400", "insufficient balance"),
	OLD_PWD_WRONG("500401", "old password wrong"),
	
	
	
	
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