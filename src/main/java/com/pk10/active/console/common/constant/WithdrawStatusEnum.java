package com.pk10.active.console.common.constant;

import java.util.HashMap;
import java.util.Map;

public enum WithdrawStatusEnum{

	APPLY("申请提现",1),
	SUCCESS("提现成功",2),
	CANCEL("取消提现",3);
	
	private static Map<String, WithdrawStatusEnum> LOOK_UP = new HashMap<>();
	
	static {
		WithdrawStatusEnum[] values = WithdrawStatusEnum.values();
		   for (WithdrawStatusEnum value : values) {
			   LOOK_UP.put(value.value.toString(), value);
	    }
	}
	
	public String label() {
		return this.label;
	}
	public Integer value() {
		return this.value;
	}
	private String label; 
    private Integer value;  
    
    private WithdrawStatusEnum(String label, Integer value) {
    	this.label = label;
    	this.value = value;
    }
	public static WithdrawStatusEnum get(Integer value) {
		return LOOK_UP.get(value.toString());
	}
	public static String label(Integer value) {
		if(value == null) {
			return null;
		}
		WithdrawStatusEnum widthDrawStatusEnum = get(value);
		return widthDrawStatusEnum == null ? null : widthDrawStatusEnum.label;
	}
	public String getLabel() {
		return label;
	}
	public Integer getValue() {
		return value;
	}
	
}
