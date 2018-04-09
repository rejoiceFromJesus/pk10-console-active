/**
 * 系统项目名称
 * cn.jrjzx.supervise.manager.common.exception
 * InvalidParamException.java
 * 
 * 2017年11月16日-下午2:58:13
 *  2017金融街在线公司-版权所有
 *
 */
package com.pk10.active.console.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.pk10.active.console.common.bean.CodeMsg;

/**
 *
 * InvalidParamException
 * 
 * @author rejoice 948870341@qq.com
 * @date 2017年11月16日 下午2:58:13
 * 
 * @version 1.0.0
 *
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidParamException extends RuntimeException{
	
	private CodeMsg codeMsg;
	
	
	public CodeMsg getCodeMsg() {
		return codeMsg;
	}

	/**
	 * serialVersionUID:TODO（用一句话描述这个变量表示什么）
	 *
	 * @since 1.0.0
	 */
	
	private static final long serialVersionUID = -8694543243602524411L;

	public InvalidParamException(String message) {
		super(message);
	}
	
	public InvalidParamException(CodeMsg codeMsg) {
		super(codeMsg.getMsg());
		this.codeMsg = codeMsg;
	}

	public InvalidParamException() {
		super(InvalidParamException.class.getSimpleName());
	}
}
