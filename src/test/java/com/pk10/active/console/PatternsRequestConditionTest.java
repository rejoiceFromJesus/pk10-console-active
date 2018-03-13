/**
 * 系统项目名称
 * com.pk10.active.console
 * PatternsRequestCondition.java
 * 
 * 2017年6月17日-上午11:59:27
 *  2017金融街在线公司-版权所有
 *
 */
package com.pk10.active.console;

import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;

/**
 *
 * PatternsRequestCondition
 * 
 * @author rejoice 948870341@qq.com
 * @date 2017年6月17日 上午11:59:27
 * 
 * @version 1.0.0
 *
 */
public class PatternsRequestConditionTest {
	public static void main(String[] args) {
		PatternsRequestCondition condition = new PatternsRequestCondition("/**","/user/*.*","/user/add","/user/add.pdf");
		System.out.println(condition.getMatchingPatterns("/user/add.pdf"));
	}

}
