/**
 * 系统项目名称
 * com.pk10.active.console
 * CompanyServiceTest.java
 * 
 * 2017年5月23日-下午5:35:24
 *  2017金融街在线公司-版权所有
 *
 */
package com.pk10.active.console;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.github.pagehelper.PageInfo;
import com.pk10.active.console.common.util.JsonUtil;
import com.pk10.active.console.entity.Company;
import com.pk10.active.console.service.CompanyService;

/**
 *
 * CompanyServiceTest
 * 
 * @author rejoice 948870341@qq.com
 * @date 2017年5月23日 下午5:35:24
 * 
 * @version 1.0.0
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes=ActiveConsole.class)
public class CompanyServiceTest {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CompanyServiceTest.class);

	@Autowired
	CompanyService companyService;
	
	
	@Test
	public void queryPageByWhere(){
		Company company = new Company();
		company.setName("1");
		PageInfo<Company> pageInfo = companyService.queryPageByWhere(company, 1,20);
		String json = JsonUtil.toJson(pageInfo);
		System.out.println(json);
	}
}
