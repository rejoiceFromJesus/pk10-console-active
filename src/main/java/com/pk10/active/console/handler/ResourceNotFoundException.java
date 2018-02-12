package com.pk10.active.console.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * ResourceNotFoundException
 * 
 * @author rejoice 948870341@qq.com
 * @date 2017年7月25日 下午4:52:29
 * 
 * @version 1.0.0
 *
 */
@SuppressWarnings("serial")
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
	private final static String DEFAULT_MESSAGE = "ResourceNotFoundException";
	/**
	 * serialVersionUID:TODO（用一句话描述这个变量表示什么）
	 *
	 * @since 1.0.0
	 */
	
	private static final long serialVersionUID = -8694543243602524411L;

	public ResourceNotFoundException(String message) {
		super(message);
	}

	public ResourceNotFoundException() {
		super(ResourceNotFoundException.class.getSimpleName());
	}
}
