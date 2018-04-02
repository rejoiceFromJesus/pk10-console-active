/**
 * 系统项目名称
 * com.pk10.active.console.handler
 * UncertificatedException.java
 * 
 * 2018年4月2日-上午9:39:11
 *  2018金融街在线公司-版权所有
 *
 */
package com.pk10.active.console.handler;

/**
 *
 * UncertificatedException
 * 
 * @author rejoice 948870341@qq.com
 * @date 2018年4月2日 上午9:39:11
 * 
 * @version 1.0.0
 *
 */
public class UncertificatedException extends RuntimeException{
	
	/**
	 * serialVersionUID:TODO（用一句话描述这个变量表示什么）
	 *
	 * @since 1.0.0
	 */
	
	private static final long serialVersionUID = -8694543243602524411L;

	public UncertificatedException(String message) {
		super(message);
	}

	public UncertificatedException() {
		super(UncertificatedException.class.getSimpleName());
	}
}
