package com.pk10.active.console.common.constant;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public enum RuleTypeEnum{

	NUMBER("排名",1),
	SIDE("两面反",2);
	
	private static Map<String, RuleTypeEnum> LOOK_UP = new HashMap<>();
	
	static {
		RuleTypeEnum[] values = RuleTypeEnum.values();
		   for (RuleTypeEnum value : values) {
			   LOOK_UP.put(value.value.toString(), value);
	    }
	}
	
	public String label() {
		return this.label;
	}
	public Serializable value() {
		return this.value;
	}
	private String label; 
    private Integer value;  
    
    private RuleTypeEnum(String label, Integer value) {
    	this.label = label;
    	this.value = value;
    }
	public static RuleTypeEnum get(Integer value) {
		return LOOK_UP.get(value.toString());
	}
	public static String label(Integer value) {
		if(value == null) {
			return null;
		}
		RuleTypeEnum ruleTypeEnum = get(value);
		return ruleTypeEnum == null ? null : ruleTypeEnum.label;
	}
	public String getLabel() {
		return label;
	}
	public Integer getValue() {
		return value;
	}
	
}
