/**
 * 系统项目名称
 * com.pk10.active.console.util
 * Constant.java
 * 
 * 2017年5月11日-上午9:10:39
 *  2017金融街在线公司-版权所有
 *
 */
package com.pk10.active.console.common.constant;


/**
 *
 * Constant
 * 
 * @author rejoice 948870341@qq.com
 * @date 2017年5月11日 上午9:10:39
 * 
 * @version 1.0.0
 *
 */
public abstract class Constant {

	public static final String PREFIX="rejoice.activeconsole";
	public static final String SUCCESS="success";
	public static final String SESSION_KEY = "loginUser";
	public static final String COOKIE_TOKEN = "token";
	
	
	public static final String[] RULE_TITLES = {"冠亚和","第一名","第二名","第三名","第四名","第五名","第六名","第七名","第八名","第九名","第十名"};
	/**
	 * yyyy-MM-dd
	 */
	public static final String DATE_FORMAT_PATTERN1 = "yyyy-MM-dd";
	/**
	 * yyyy-MM-dd HH:mm:ss
	 */
	public static final String DATE_FORMAT_PATTERN2 = "yyyy-MM-dd HH:mm:ss";
	/**
	 * yyyyMMdd
	 */
	public static final String DATE_FORMAT_PATTERN3 = "yyyyMMdd";
	/**
	 * yyyy-MM-dd HH:mm:ss:SSS
	 */
	public static final String DATE_FORMAT_PATTERN4 = "yyyy-MM-dd HH:mm:ss:SSS";
	/**
	 * yyyyMM
	 */
	public static final String DATE_FORMAT_YYYYMM = "yyyyMM";
	//no auth paths
	public static final String[] PASS_PATHS = new String[]{
		"/page/login.html",
		"/logout","/user/login",
		"/company/active",
		"/user/client/login",
		"/swagger-resources/configuration/ui",
		"/swagger-resources",
		"/user/register",
		"/client/user/login",
		"/client/user/register",
		"/client/user/auto-login",
		"/client/user/logout",
		"/error"};

}
