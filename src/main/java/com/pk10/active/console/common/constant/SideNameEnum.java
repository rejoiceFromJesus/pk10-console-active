package com.pk10.active.console.common.constant;

import java.util.HashMap;
import java.util.Map;

public enum SideNameEnum{

	ODD("单",1),
	EVEN("双",2),
	BIG("大",3),
	SMALL("小",4),
	DRAGON("龙",5),
	TIGER("虎",6);
	
	private static Map<String, SideNameEnum> LOOK_UP = new HashMap<>();
	
	static {
		SideNameEnum[] values = SideNameEnum.values();
		   for (SideNameEnum value : values) {
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
    
    private SideNameEnum(String label, Integer value) {
    	this.label = label;
    	this.value = value;
    }
	public static SideNameEnum get(Integer value) {
		return LOOK_UP.get(value.toString());
	}
	public static String label(Integer value) {
		if(value == null) {
			return null;
		}
		SideNameEnum sideNameEnum = get(value);
		return sideNameEnum == null ? null : sideNameEnum.label;
	}
}
