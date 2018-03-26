package com.pk10.active.console.common.constant;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public enum TradeTypeEnum{

	RECHARGE("充值",1),
	BET("投注",2),
	SETTLE("结算",3);
	
	
	private static Map<String, TradeTypeEnum> LOOK_UP = new HashMap<>();
	
	static {
		TradeTypeEnum[] values = TradeTypeEnum.values();
		   for (TradeTypeEnum value : values) {
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
    
    private TradeTypeEnum(String label, Integer value) {
    	this.label = label;
    	this.value = value;
    }
	public static TradeTypeEnum get(Integer value) {
		return LOOK_UP.get(value.toString());
	}
	public static String label(Integer value) {
		if(value == null) {
			return null;
		}
		TradeTypeEnum tradeTypeEnum = get(value);
		return tradeTypeEnum == null ? null : tradeTypeEnum.label;
	}
	public String getLabel() {
		return label;
	}
	public Integer getValue() {
		return value;
	}
	
}
