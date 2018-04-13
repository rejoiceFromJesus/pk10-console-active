package com.pk10.active.console.common.constant;

import java.util.HashMap;
import java.util.Map;

public enum ContentTypeEnum{

	RULE("游戏规则",1),
	PK10_ADDRESS("前端二维码地址",2),
	ADMIN_QRCODE("管理员微信",3);
	
	private static Map<String, ContentTypeEnum> LOOK_UP = new HashMap<>();
	
	static {
		ContentTypeEnum[] values = ContentTypeEnum.values();
		   for (ContentTypeEnum value : values) {
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
    
    private ContentTypeEnum(String label, Integer value) {
    	this.label = label;
    	this.value = value;
    }
	public static ContentTypeEnum get(Integer value) {
		return LOOK_UP.get(value.toString());
	}
	public static String label(Integer value) {
		if(value == null) {
			return null;
		}
		ContentTypeEnum contentTypeEnum = get(value);
		return contentTypeEnum == null ? null : contentTypeEnum.label;
	}
	public String getLabel() {
		return label;
	}
	public Integer getValue() {
		return value;
	}
	
}
