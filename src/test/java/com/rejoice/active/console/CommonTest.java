/**
 * 系统项目名称
 * com.pk10.active.console
 * CommonTest.java
 * 
 * 2017年6月11日-下午1:58:04
 *  2017金融街在线公司-版权所有
 *
 */
package com.pk10.active.console;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;

import com.pk10.active.console.common.util.RejoiceUtil;
/**
 *
 * CommonTest
 * 
 * @author rejoice 948870341@qq.com
 * @date 2017年6月11日 下午1:58:04
 * 
 * @version 1.0.0
 *
 */
public class CommonTest {
  
	@Test
	public void CamelTest(){
		System.out.println(RejoiceUtil.camelName("companyName"));
	}
	
	@Test
	public void subStringTest(){
		StringBuilder sb = new StringBuilder("23,");
		System.out.println(sb.substring(0, sb.length()-1));
	}
	@Test
	public void md5Test(){
		System.out.println(DigestUtils.md5Hex("admin"));
	}
}
